import java.util.Scanner;

public class Console {
    public static final String fullNameRequirement = "ФИО должно содержать только русские буквы";
    public static final String birthDateRequirement = "Дата рождения должна быть задана в формате ДД.ММ.ГГГГ";
    private final Scanner scanner;

    public Console() {
        scanner = new Scanner(System.in);
    }

    public String input(String message) {
        System.out.print(message + ": ");
        return scanner.nextLine();
    }

    public void print() {
        System.out.println();
    }

    public void print(String message) {
        System.out.println(message);
    }
}
