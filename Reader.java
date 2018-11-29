package pizzabot;

import java.util.Scanner;

public class Reader {

    private Scanner scanner;

    public Reader() {
        scanner = new Scanner(System.in);
    }

    public String[] read() {
        return scanner.nextLine().split(" ");
    }

    public String readString() {
        return scanner.next();
    }

}
