package lt.codeacademy.spring_test.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.spring_test.entity.Book;
import lt.codeacademy.spring_test.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_FORMATTER2 = DateTimeFormatter.ofPattern("yyyy");

    public long getCountByGenreAndYear(String genre, String year){
        int yearValue = Integer.parseInt(year);
        List<Book> filteredByGenre = bookRepository.findAllByGenre(genre);
        return bookRepository.findAllByGenre(genre).stream()
                .filter(book -> book.getPublishDate().getYear() == yearValue)
                .count();
    }

    public List<Book> getAllByGenre(String genre) {
        return bookRepository.findAllByGenre(genre);
    }
    public void printAllByGenre(String genre) {
        getAllByGenre(genre).forEach(System.out::println);
    }

    public void addBook(Book book){
        bookRepository.saveAndFlush(book);
    }
    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

    public Book getBookByTitle(String title){
        return bookRepository.getBookByTitle(title);
    }

    public Book findBookByTitle(String title){
        while (true) {
            if (!bookRepository.existsBookByTitle(title)){
                System.out.println("Book not found");
                continue;
            }
            Book book = getBookByTitle(title);
            if (book == null){
                System.out.println("null: Book not found");
                continue;
            }
            return book;
        }
    }

    public void changeBookLocation(Book book, String location){
        book.setLocation(location);
        bookRepository.save(book);
    }


    public void populateBooks() throws ParseException {
        String[][] bookData = {
                {"The Great Gatsby", "F. Scott Fitzgerald", "Fiction", "1925-04-10", "Shelf A1"},
                {"To Kill a Mockingbird", "Harper Lee", "Fiction", "1960-07-11", "Shelf B2"},
                {"1984", "George Orwell", "Dystopian", "1949-06-08", "Shelf C3"},
                {"Moby-Dick", "Herman Melville", "Adventure", "1851-10-18", "Shelf D4"},
                {"Pride and Prejudice", "Jane Austen", "Romance", "1813-01-28", "Shelf E5"},
                {"War and Peace", "Leo Tolstoy", "Historical", "1869-01-01", "Shelf F6"},
                {"The Catcher in the Rye", "J.D. Salinger", "Fiction", "1951-07-16", "Shelf G7"},
                {"The Hobbit", "J.R.R. Tolkien", "Fantasy", "1937-09-21", "Shelf H8"},
                {"Crime and Punishment", "Fyodor Dostoevsky", "Philosophical", "1866-01-01", "Shelf I9"},
                {"Brave New World", "Aldous Huxley", "Dystopian", "1932-08-01", "Shelf J10"},
                {"The Alien Code", "John Smith", "Science Fiction", "1994-05-22", "Shelf K11"},
                {"Echoes of the Past", "Jane Doe", "Historical Fiction", "1994-09-15", "Shelf L12"},
                {"Future Horizons", "Emily Clark", "Science Fiction", "2025-03-10", "Shelf M13"},
                {"The Last Utopia", "Michael Brown", "Dystopian", "2025-07-25", "Shelf N14"},
                {"Chronicles of Tomorrow", "Sarah Green", "Fantasy", "2025-11-30", "Shelf O15"}
        };

        for (String[] data : bookData) {
            Book book = new Book();
            book.setTitle(data[0]);
            book.setAuthor(data[1]);
            book.setGenre(data[2]);
            LocalDate date = LocalDate.parse(data[3], DATE_FORMATTER);
            book.setPublishDate(date);
            book.setLocation(data[4]);

            bookRepository.saveAndFlush(book);
        }
    }

}
