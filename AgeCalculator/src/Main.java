import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static int byear;
    private static String bmonth;
    private static int bday;

    public static void main(String[] args) {
        printMenu();
        calculateAge();
    }

    private static void printMenu() {
        System.out.println("Enter your birth year");
        byear = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter your month of birth");
        bmonth = scanner.nextLine();
        System.out.println("Enter your day of birth");
        bday = Integer.parseInt(scanner.nextLine());
    }

    private static void calculateAge() {

        LocalDate ageDate = LocalDate.of(byear, Month.valueOf(bmonth.toUpperCase()), bday);

        Period lengthPeriod = Period.between(ageDate, LocalDate.now());
        System.out.println("You are: " + lengthPeriod.getYears() + " years " + lengthPeriod.getMonths() + " months "
                + lengthPeriod.getDays() + " days old");
    }
}