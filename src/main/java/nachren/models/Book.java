package nachren.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private Integer person_id;
    private int book_id;
    @NotEmpty(message = "not empty")
    @Size(min = 3, max = 100, message = "size from 3 to 100 chars")
    private String title;
    @NotEmpty(message = "not empty")
    @Size(min = 3, max = 100, message = "size from 3 to 100 chars")
    private String author;
    private int year;

//    Страна, Город, индекс (6 цифр)
//    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Your address should be in this format: Country, City, Post Code(6 digits)")
//    private String address;

    public Book(Integer person_id, int book_id, String title, String author, int year) {
        this.person_id = person_id;
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {

    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
