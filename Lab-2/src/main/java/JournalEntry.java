import java.time.LocalDate;

public class JournalEntry {
    private final String surname;
    private final String name;
    private final LocalDate birthDate;
    private final String phone;
    private final String address;

    public JournalEntry(String surname, String name, LocalDate birthDate, String phone, String address) {
        this.surname = surname;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Студент: " + surname + " " + name +
                ", Дата народження: " + birthDate +
                ", Телефон: " + phone +
                ", Адреса: " + address;
    }
}
