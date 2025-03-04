package lt.codeacademy.spring_test;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.spring_test.Enums.Command;
import lt.codeacademy.spring_test.Enums.LibCommand;
import lt.codeacademy.spring_test.service.BookService;
import lt.codeacademy.spring_test.service.GroceryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.Scanner;

@RequiredArgsConstructor
@SpringBootApplication
public class SpringTestApplication {

	private final GroceryService groceryService;
	private final BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(SpringTestApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void library() throws ParseException {
		Scanner scanner = new Scanner(System.in);
		boolean isActive = true;
		bookService.populateBooks();
		while (isActive) {
			System.out.println("-".repeat(43));
			LibCommand.printCommands();
			System.out.println("-".repeat(43));
			System.out.println("Input command:");
			switch (LibCommand.ofKey(Integer.parseInt(scanner.nextLine()))) {
				case null -> System.out.println("ERROR: uknown command");
                case ADD_BOOK -> bookService.addBook(bookService.createBook(scanner));
                case DEL_BOOK -> bookService.deleteBook(bookService.findBookByName(scanner));
                case CHANGE_LOC -> bookService.changeBookLocation(bookService.findBookByName(scanner), scanner);
                case PRINT_BOOKS_BY_GENRE -> bookService.printAllByGenre(scanner);
                case PRINT_BOOKS_BY_GENRE_YEAR -> bookService.printAllByGenreYearAndYear(scanner);
                case EXIT -> {
					System.out.println("Application exiting...");
					isActive = false;
					System.exit(0);
                }
            }
		}
		scanner.close();
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void test() {
//		groceryService.addTestGrocery();
//		Scanner sc = new Scanner(System.in);
//		boolean isActive = true;
//		int pageNumber = 0;
//		int pageSize = 5;
//		int totalPages = groceryService.returnRepSize()/pageSize;
//
//		while (isActive) {
//			Pageable pageable =  PageRequest.of(pageNumber, pageSize);
//			groceryService.findAllPageable(pageable).forEach(System.out::println);
//			System.out.println("-".repeat(43));
//			Command.printCommands();
//			System.out.println("-".repeat(43));
//			System.out.println("input command:");
//			switch (Command.ofKey(Integer.parseInt(sc.nextLine()))) {
//				case NEXT_PAGE -> {
//					if (pageNumber < totalPages--){
//						pageNumber++;
//					}
//					else System.out.println("can't get higher page");
//				}
//				case PREVIOUS_PAGE -> {
//					if (pageNumber >= 1) {
//						pageNumber--;
//					} else System.out.println("can't get lower page");
//				}
//				case EXIT -> {
//					isActive = false;
//                    System.out.println("Application exiting...");
//                    System.exit(0);
//				}
//				case null -> System.out.println("ERROR: uknown command");
//			}
//		}
//		sc.close();
//	}
}
