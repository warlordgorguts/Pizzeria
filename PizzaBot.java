package pizzabot;

public class PizzaBot {

    public static void main(String[] args) {
        System.out.println("Welcome to our 'Pizzeria di Stepanio!'");
        System.out.println("Chatbot will serve you, to start look at our menu:");
        System.out.println();

        Order order = new Order();
        InputValidator inputValidator = new InputValidator();
        Menu.getInstance().print();
        inputValidator.validateCommands(order);

        OrderProcessor orderProcessor = new OrderProcessor();
        orderProcessor.applyRules(order);
        orderProcessor.applyPostExecuteRules(order);
        orderProcessor.printOrder(order);
    }

}
