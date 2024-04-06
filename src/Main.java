import java.security.InvalidParameterException;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        console.print(Console.fullNameRequirement);
        console.print("Если отчество отсутствует, введите прочерк (-)");
        console.print(Console.birthDateRequirement);
        console.print();
        String surname = console.input("Фамилия");
        String name = console.input("Имя");
        String patronymic = console.input("Отчество");
        String birthDate = console.input("Дата рождения");
        console.print();
        Person person = null;
        try {
            person = new Person(surname, name, patronymic, birthDate);
        } catch (InvalidParameterException e) {
            console.print(e.getMessage());
            System.exit(1);
        }
        console.print(person.getInfo());
    }
}
