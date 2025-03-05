package lt.codeacademy.spring_test.Enums;
import lombok.Getter;

@Getter
public enum LibCommand {
    ADD_BOOK(1,"1 - add new book"),
    DEL_BOOK(2,"2 - delete book"),
    CHANGE_LOC(3,"3 - change book's location"),
    PRINT_BOOKS_BY_GENRE(4,"4 - print books by genre"),
    PRINT_BOOKS_BY_GENRE_YEAR(5,"5 - print book count by genre and year"),
    EXIT(6,"6 - exit");


    private int key;
    private String description;

    LibCommand(int key, String description) {
        this.key = key;
        this.description = description;
    }

    public static void printCommands(){
        for (LibCommand c: LibCommand.values()){
            System.out.printf("| %-40s|%n",c.getDescription());
        }
    }

    public static LibCommand ofKey(int key){
        for (LibCommand c: LibCommand.values()){
            if (c.getKey() == key){
                return c;
            }
        }
        return null;
    }
}
