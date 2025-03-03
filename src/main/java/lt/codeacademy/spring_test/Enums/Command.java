package lt.codeacademy.spring_test.Enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Command {
    NEXT_PAGE(1,"1 - Next Page"),
    PREVIOUS_PAGE(2,"2 - Previous Page"),
    EXIT(3,"3 - Exit");

    private int key;
    private String description;

    Command(int key, String description) {
        this.key = key;
        this.description = description;
    }

    public static void printCommands(){
        for (Command c: Command.values()){
            System.out.printf("| %-40s|%n",c.getDescription());
        }
    }

    public static Command ofKey(int key){
        for (Command c: Command.values()){
            if (c.getKey() == key){
                return c;
            }
        }
        return null;
    }
}
