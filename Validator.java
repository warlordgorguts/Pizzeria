package pizzabot;

public class Validator {

    public void validate(ChatBot chatBot, Reader reader) {

        StringBuilder wrongCommand = new StringBuilder();

        boolean needToRead = true;

        Order order = chatBot.getOrder();
        Menu menu = chatBot.getMenu();

        for (String commandString : reader.read()) {
            Item objectByCommand = menu.getObjectByCommand(commandString);
            if (objectByCommand != null) {
                order.add(objectByCommand);
            } else {
                wrongCommand.append(commandString).append(" ");
            }
        }

        if (wrongCommand.length() > 0) {
            System.out.println("Please check spelling and repeat: " + wrongCommand.toString());
        } else if ((order.getItemsEligibleDiscountCount() + 1) % 3 == 0) {
            boolean awaitingAnswer = true;
            System.out.println("You currently ordered: " + order.getItemsEligibleDiscountCount() + " discounted items.");
            System.out.println("Do you want to buy one more to get one for free? (y/n)");
            while (awaitingAnswer) {
                String answer = reader.readString();
                if (answer.equalsIgnoreCase("n")) {
                    needToRead = false;
                    awaitingAnswer = false;
                } else if (answer.equalsIgnoreCase("y")) {
                    System.out.println("Type product command:");
                    awaitingAnswer = false;
                } else {
                    System.out.println("Type one of the following (y/n)");
                }
            }
        } else {
            needToRead = false;
        }

        if (needToRead) {
            reader = new Reader();
            validate(chatBot, reader);
        }
    }
}
