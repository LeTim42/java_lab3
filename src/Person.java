import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;

public class Person {
    private String surname, name, patronymic;
    private LocalDate birthDate;

    public Person(String surname, String name, String patronymic, String birthDate) {
        setBirthDate(birthDate);
        setPatronymic(patronymic);
        setName(name);
        setSurname(surname);
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setSurname(String surname) {
        checkName(surname);
        this.surname = surname;
    }

    public void setName(String name) {
        checkName(name);
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        checkName(patronymic);
        this.patronymic = patronymic;
    }

    public void setBirthDate(String birthDate) {
        try {
            this.birthDate = LocalDate.parse(birthDate, DateTimeFormatter
                    .ofPattern("dd.MM.uuuu")
                    .withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            throw new InvalidParameterException(
                    e.getMessage().contains("could not be parsed:") ?
                            "Дата рождения должна быть существующей датой" :
                            Console.birthDateRequirement);
        }
        if (this.birthDate.isAfter(LocalDate.now())) {
            throw new InvalidParameterException("Дата рождения должна быть раньше текущей даты");
        }
    }

    public String getInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append(surname).append(' ').append(name.charAt(0)).append('.');
        if (!patronymic.equals("-"))
            builder.append(patronymic.charAt(0)).append('.');
        builder.append(", ").append(getSex()).append(", ").append(getAge());
        return builder.toString();
    }

    public String getSex() {
        if (patronymic.endsWith("а"))
            return "Ж.";
        if (patronymic.endsWith("ч"))
            return "М.";
        if (surname.endsWith("а"))
            return "Ж.";
        return "М.";
    }

    public String getAge() {
        long age = ChronoUnit.YEARS.between(birthDate, LocalDate.now());
        String years;
        if ((age / 10) % 10 == 1) years = "лет";
        else if (age % 10 == 1) years = "год";
        else if (2 <= age % 10 && age % 10 <= 4) years = "года";
        else years = "лет";
        return age + " " + years;
    }

    private void checkName(String string) {
        if (string.isEmpty()) {
            throw new InvalidParameterException("ФИО не должно быть пустым");
        } else if (!string.matches("[а-яА-Я\\- ]+")) {
            throw new InvalidParameterException(Console.fullNameRequirement);
        }
    }
}
