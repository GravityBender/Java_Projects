import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printMenu();
    }

    private static void printMenu() {
        System.out.println("Choose among the following: ");
        System.out.println("1. Shift Cipher\n"
                + "2.Cipher\n");
        int pchoice = Integer.parseInt(scanner.nextLine());
        int schoice;
        switch (pchoice) {
            case 1:
                System.out.println("1.Encrypt a message from a file and save it into other file.\n"
                        + "2.Encrypt a message from the console and save it into file\n"
                        + "3.Decrypt a message from an exisiting file\n");

                schoice = Integer.parseInt(scanner.nextLine());
                choices(schoice);
                break;

            case 2:
                System.out.println("1.Encrypt a message from a file and save it into other file.\n"
                        + "2.Encrypt a message from the console and save it into file\n"
                        + "3.Decrypt a message from an exisiting file\n");
                schoice = Integer.parseInt(scanner.nextLine());
                choices(schoice);
                break;

            default:
                System.out.println("Wrong choice entered.");
        }
    }

    private static void choices(int schoice) {
        switch (schoice) {
            case 1:
                // System.out.println("Enter the absolute path of the file.");
                // ReadEncryptFile file = new ReadEncryptFile();
                // file.getFilePath();
                // file.readFile();
                ShiftCipher file;
                System.out.println("Do you want to enter a custom filename(y/n)?(default is Demo)");
                String fchoice = scanner.nextLine();
                if (fchoice.equalsIgnoreCase("y")) {
                    System.out.println("Enter the file name");
                    String fname = scanner.nextLine();
                    file = new ShiftCipher(fname);
                } else {
                    file = new ShiftCipher();
                }
                file.startEncryption();

                break;
            case 2:
                System.out.println("Start entering the message below: (Enter two empty lines to stop reading)");
                ConsoleMessage cMessage = new ConsoleMessage();
                cMessage.readFromConsole();
                file = new ShiftCipher("MessageToEncrypt");
                file.startEncryption();
                System.out.println(
                        "Encrypted message has been saved to file: ShiftCipher.txt\nOriginal Text has been saved to MessageToEncrypt.txt");
                break;
            case 3:
                System.out.println("Enter the file name");
                String fname = scanner.nextLine();
                DecryptShiftCipher dfile = new DecryptShiftCipher(fname);
                dfile.startDecryption();
                break;
            default:
                break;
        }
    }
}
