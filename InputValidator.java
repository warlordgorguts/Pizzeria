package pizzabot;

import java.util.Optional;
import java.util.Scanner;

public class InputValidator {

    public void validateCommands(Order order) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder wrongCommand = new StringBuilder();
        String[] commands = scanner.nextLine().split(" ");

        for (String commandString : commands) {
            Optional<Item> item = Menu.getInstance().get(commandString);
            if (item.isPresent()) {
                order.add(item.get());
            } else {
                wrongCommand.append(commandString).append(" ");
            }
        }

        if (wrongCommand.length() > 0) {
            System.out.println("Please check spelling and repeat: " + wrongCommand.toString());
            validateCommands(order);
        }
    }

    public boolean validateRuleQuestion() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            switch (scanner.next()) {
                case "n": {
                    return false;
                }
                case "y": {
                    return true;
                }
                default: {
                    System.out.println("Type one of the following (y/n)");
                }
            }
        }
    }

    public int validateCardNumber() {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            return 0;
        }

        return scanner.nextInt();
    }
}
