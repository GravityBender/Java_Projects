import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static int rbufferSize = 8;
    private static InputStream iStream;
    private static OutputStream oStream;
    private static String divisor = "100000111";

    public static void init(String filePath1, String filePath2) {
        try {
            iStream = new FileInputStream(new File(filePath1));
            oStream = new FileOutputStream(new File(filePath2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        init("ServerData.txt", "ClientData.txt");

        while (true) {
            byte[] temp = fileRead();
            if (temp == null) {
                break;
            } else {
                // System.out.println(new String(temp));
                // for (int i = 0; i < temp.length; i++) {
                // System.out.print(Integer.toBinaryString(Byte.toUnsignedInt(temp[i])) + " ");
                // }

                StringBuilder sBuilder = new StringBuilder("");
                // for (int i = 0; i < temp.length; i++) {
                // sBuilder.append(Integer.toBinaryString(Byte.toUnsignedInt(temp[i])));
                // }

                for (Byte b : temp) {
                    sBuilder.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(" ", "0"));
                }

                System.out.println(checkValid(encodeData(sBuilder.toString())));
                String xy = encodeData(sBuilder.toString());
                System.out.println(xy);
                if (checkValid(xy)) {
                    fileWrite(xy);
                }
            }
        }

    }

    private static byte[] fileRead() {

        try {
            if (iStream.available() != 0) {
                byte[] rbuffer = iStream.readNBytes(rbufferSize);

                return rbuffer;
            } else {
                return null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String encodeData(String dividend) {
        int lkey = divisor.length();

        StringBuilder sb = new StringBuilder(dividend);
        for (int i = 0; i < lkey - 1; i++) {
            sb.append("0");
        }
        String remainder = crc(sb.toString());
        String codeString = dividend + remainder;
        // System.out.println("Original: " + dividend);
        // System.out.println("Remainder: " + remainder);
        // System.out.println("Encoded data: " + codeString);

        return codeString;
    }

    private static String crc(String dividend) {
        int pick = divisor.length();

        StringBuilder tmp = new StringBuilder(dividend.substring(0, pick));

        while (pick < dividend.length()) {
            if (Character.toString(tmp.charAt(0)).equals("1")) {
                tmp = xor(divisor, tmp.toString()).append(dividend.charAt(pick));
            } else {
                StringBuilder sTemp = new StringBuilder("");
                for (int i = 0; i < pick; i++) {
                    sTemp.append("0");
                }

                tmp = xor(sTemp.toString(), tmp.toString()).append(dividend.charAt(pick));
            }
            pick++;
        }

        if (Character.toString(tmp.charAt(0)).equals("1")) {
            tmp = xor(divisor, tmp.toString());
        } else {
            StringBuilder sTemp = new StringBuilder("");
            for (int i = 0; i < pick; i++) {
                sTemp.append("0");
            }

            tmp = xor(sTemp.toString(), tmp.toString());
        }

        return tmp.toString();
    }

    private static StringBuilder xor(String a, String b) {
        StringBuilder sBuilder = new StringBuilder("");

        for (int i = 1; i < b.length(); i++) {
            if (Character.toString(a.charAt(i)).equals(Character.toString(b.charAt(i)))) {
                sBuilder.append("0");
            } else {
                sBuilder.append("1");
            }
        }
        return sBuilder;
    }

    private static boolean checkValid(String incomingString) {
        String remainder = crc(incomingString);

        // System.out.println("Incoming Data: " + incomingString);

        for (int i = 0; i < remainder.length(); i++) {
            if (Character.toString(remainder.charAt(i)).equals("1")) {
                return false;
            }
        }
        return true;
    }

    private static void fileWrite(String incomingString) {
        List<Integer> list = new ArrayList<>();

        for (String str : incomingString.split("(?<=\\G.{8})")) {
            list.add(Integer.parseUnsignedInt(str, 2));
        }

        System.out.println(list);

        byte[] byteArr = new byte[list.size()];
        for (int i = 0; i < byteArr.length; i++) {
            byteArr[i] = (byte) list.get(i).intValue();
        }
        System.out.println(new String(byteArr, 0, byteArr.length - 1));
        try {
            oStream.write(byteArr, 0, byteArr.length - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
