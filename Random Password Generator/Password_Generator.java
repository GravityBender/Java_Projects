import java.util.*;
/**
 * Password_Generator
 */
public class Password_Generator {

        public String arr="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()-=_+|";

        public void generatePassword(int plength){
            Random random = new Random();

            char[] password = new char[plength];

            for(int i = 0; i < plength; i++){
                password[i]=arr.charAt(random.nextInt(0, arr.length()));
            }

            System.out.println(password);
        }

        public void generatePassword(){
            Random random = new Random();

            char[] password = new char[8];

            for(int i = 0; i < 8; i++){
                password[i]=arr.charAt(random.nextInt(0, arr.length()));
            }

            System.out.println(password);
        }

        public static void main(String[] args) {
            Password_Generator pass=new Password_Generator();
            Scanner scanner = new Scanner(System.in);

            System.out.println("The default length of password is 8.\n"
            + "Do you want to change it?(y/n)");

            char choice=scanner.next().charAt(0);

            int length;

            if(choice=='y'||choice=='Y'){
                System.out.println("Enter the password length");
                length = scanner.nextInt();
                pass.generatePassword(length);
            }
            else{
                pass.generatePassword();
            }
            scanner.close();
        }
}