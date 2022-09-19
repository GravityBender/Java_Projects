import java.util.ArrayList;
import java.util.List;

public class CRC {

    private final static String divisor = "100000111";

    private String crc(String dividend) {
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

    public String encodeData(String dividend) {
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

    public boolean checkValid(String incomingString) {
        String remainder = crc(incomingString);

        // System.out.println("Incoming Data: " + incomingString);

        for (int i = 0; i < remainder.length(); i++) {
            if (Character.toString(remainder.charAt(i)).equals("1")) {
                return false;
            }
        }
        return true;
    }

    public byte[] convertToByteArr(String encodedString) {
        List<Integer> list = new ArrayList<>();

        for (String str : encodedString.split("(?<=\\G.{8})")) {
            list.add(Integer.parseUnsignedInt(str, 2));
        }

        byte[] byteArr = new byte[list.size()];
        for (int i = 0; i < byteArr.length; i++) {
            byteArr[i] = (byte) list.get(i).intValue();
        }

        return byteArr;
    }
}
