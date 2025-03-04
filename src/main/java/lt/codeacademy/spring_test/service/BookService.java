package lt.codeacademy.spring_test.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.spring_test.entity.Book;
import lt.codeacademy.spring_test.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<Book> getAllByGenre(Scanner scanner) {
        System.out.println("Enter genre:");
        String genre = scanner.nextLine();
        return bookRepository.findAllByGenre(genre);
    }
    public void printAllByGenre(Scanner scanner) {
        getAllByGenre(scanner).forEach(System.out::println);
    }

    public void printAllByGenreYearAndYear(Scanner scanner) throws ParseException {
        System.out.println("Enter year you're looking for:");
        int year = Integer.parseInt(scanner.nextLine());

        List<Book> filteredBooks = getAllByGenre(scanner).stream()
                .filter(book -> book.getPublishDate().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .getYear() == year)
                .toList();
        filteredBooks.forEach(System.out::println);
    }

    public void addBook(Book book){
        bookRepository.saveAndFlush(book);
    }
    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

    public Book findBookByName(Scanner scanner){
        while (true) {
        System.out.println("Enter Book name: ");
        String bookName = scanner.nextLine();
        if (!bookRepository.existsBookByTitle(bookName)){
            System.out.println("Book not found");
            continue;
        }
        Book book = bookRepository.findBookByTitle(bookName);
        if (book == null){
            System.out.println("null: Book not found");
            continue;
        }
        return book;
        }
    }

    public void changeBookLocation(Book book, Scanner scanner){
        System.out.println("Enter Book location: ");
        String bookLocation = scanner.nextLine();
        book.setLocation(bookLocation);
        bookRepository.save(book);
    }

    public Book createBook(Scanner scanner) throws ParseException {
        Book book = new Book();
        System.out.println("Enter title:");
        book.setTitle(scanner.nextLine());
        System.out.println("Enter Author:");
        book.setAuthor(scanner.nextLine());
        System.out.println("Enter genre:");
        book.setGenre(scanner.nextLine());
        System.out.println("Enter date of publishing (yyyy-MM-dd):");
        String date = scanner.nextLine();
        Date publishDate = dateFormat.parse(date);
        book.setPublishDate(publishDate);
        System.out.println("Enter location (Shelf ID):");
        book.setLocation(scanner.nextLine());
        return book;
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
            Date publishDate = dateFormat.parse(data[3]);
            book.setPublishDate(publishDate);
            book.setLocation(data[4]);

            bookRepository.saveAndFlush(book);
        }
    }

}
