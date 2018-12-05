package pizzabot;

public class ChatBot {

    private Order order;


    public ChatBot() {
        TextFileReader textFileReader = new TextFileReader("C:\\Users\\anton\\Downloads\\Java\\TestFiles\\Pizza.csv", ",");
        textFileReader.readFile();
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.add("Pizza");
        State freeDelieveryRuleState = new State();
        FreeDeliveryRule freeDelieveryRule1 = new FreeDeliveryRule(freeDelieveryRuleState);
        FreeDeliveryRule freeDelieveryRule2 = new FreeDeliveryRule(freeDelieveryRuleState);
        FreeDeliveryRule freeDelieveryRule3 = new FreeDeliveryRule(freeDelieveryRuleState);
        FreeDeliveryRule freeDelieveryRule4 = new FreeDeliveryRule(freeDelieveryRuleState);
        System.out.println(freeDelieveryRule1.getState().getValue().toString());
        System.out.println(freeDelieveryRule2.getState().getValue().toString());
        System.out.println(freeDelieveryRule3.getState().getValue().toString());
        System.out.println(freeDelieveryRule4.getState().getValue().toString());
        freeDelieveryRule1.executeRule();
        System.out.println(freeDelieveryRule1.getState().getValue().toString());
        System.out.println(freeDelieveryRule2.getState().getValue().toString());
        System.out.println(freeDelieveryRule3.getState().getValue().toString());
        System.out.println(freeDelieveryRule4.getState().getValue().toString());
        order = new Order(discountCalculator);
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
