package pizzabot;

import java.util.Optional;

public class Validator {

    private static int countOfOperations;

    public void validate(Order order) {
        StringBuilder wrongCommand = new StringBuilder();

        for (String commandString : new Reader().read()) {
            Optional<Item> itemByCommand = Menu.getInstance().get(commandString);
            if (itemByCommand.isPresent()) {
                order.add(itemByCommand.get());
            } else {
                wrongCommand.append(commandString).append(" ");
            }
        }

        if (wrongCommand.length() > 0) {
            System.out.println("Please check spelling and repeat: " + wrongCommand.toString());
            validate(order);
            return;
        }

        for (DiscountRules discountRule : order.getDiscountRules()) {
            discountRule.checkRule(order, this);
        }

        for (DiscountRules discountRule : order.getDiscountRules()) {
            discountRule.executeRule(order);
            discountRule.printRule();
        }

        /*
        if (order.getItemsEligibleDiscountCount() % 3 == 2 && countOfOperations == 0) {
            boolean awaitingAnswer = true;
            System.out.println("You currently ordered: " + order.getItemsEligibleDiscountCount() + " discounted items.");
            System.out.println("Do you want to buy one more to get one for free? (y/n)");
            while (awaitingAnswer) {
                switch (new Reader().readString()) {
                    case "n": {
                        awaitingAnswer = false;
                        break;
                    }
                    case "y": {
                        awaitingAnswer = false;
                        System.out.println("Type product command:");
                        countOfOperations++;
                        validate(order);
                        break;
                    }
                    default: {
                        System.out.println("Type one of the following (y/n)");
                    }
                }
            }
        }
        */

    }

}
