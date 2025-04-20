package nachren.models;

import javax.validation.constraints.*;

public class Person {

    private int person_id;

    @NotEmpty(message = "not empty")
    @Size(min = 3, max = 100, message = "size from 3 to 100 chars")
    private String fio;
    @Min(value = 0, message = "Age should be greater than 3")
    private int year;

//    Страна, Город, индекс (6 цифр)
//    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Your address should be in this format: Country, City, Post Code(6 digits)")
//    private String address;

    public Person(int person_id, String fio, int year) {
        this.person_id = person_id;
        this.fio = fio;
        this.year = year;
    }

    public Person() {

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

}
