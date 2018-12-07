package pizzabot;

import java.util.*;

public class ChatBot {

    private Order order;


    public ChatBot() {
        TextFileReader textFileReader = new TextFileReader("C:\\Users\\anton\\Downloads\\Java\\TestFiles\\Pizza.csv", ",");
        textFileReader.readFile();
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.add("Pizza");
        State discountCardState = new State();
        List<DiscountRules> discountRulesList = new ArrayList<>();
        DiscountCard discountCard = new DiscountCard(discountCardState, true, true);

        discountRulesList.add(discountCard);

        order = new Order(discountCalculator, discountRulesList);
    }

    public Order getOrder() {
        return order;
    }

    public void startChat() {
        System.out.println("Welcome to our 'Pizzeria di Stepanio!'");
        System.out.println("Chatbot will serve you, to start look at our menu:");
        System.out.println();
        Menu.getInstance().print();
    }

    public void endChat() {
        System.out.println("Your order: ");
        System.out.println();
        order.print();
    }

}
