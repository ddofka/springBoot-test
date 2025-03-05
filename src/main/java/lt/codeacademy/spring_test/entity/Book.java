package lt.codeacademy.spring_test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

@Getter
@Setter
@ToString
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private LocalDate publishDate;
    private String genre;
    private String location;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Book() {
    }

    public Book(Scanner scanner) throws ParseException {
        System.out.println("Enter title:");
        this.title = scanner.nextLine();
        System.out.println("Enter Author:");
        this.author = scanner.nextLine();
        System.out.println("Enter genre:");
        this.genre = scanner.nextLine();
        System.out.println("Enter date of publishing (yyyy-MM-dd):");
        String date = scanner.nextLine();
        this.publishDate = LocalDate.parse(date, DATE_FORMATTER);
        System.out.println("Enter location (Shelf ID):");
        this.location = scanner.nextLine();
    }
}
