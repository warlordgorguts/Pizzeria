package pizzabot;

public class ChatBot {

    private Order order;
    private Menu menu = Menu.getInstance();

    public ChatBot() {
        AppleJuice appleJuice = new AppleJuice();
        RedWine redWine = new RedWine();

        menu.add(appleJuice);
        menu.add(redWine);
        menu.add(new Pizza("Caesar", 100, "Caesar"));
        menu.add(new Pizza("Milano", 90, "Milano"));
        menu.add(new Pizza("Tropico", 90, "Tropico"));
        menu.add(new Pizza("Special", 110, "Special"));
        menu.add(new Pizza("Margharita", 80, "Margharita"));
        menu.add(new Pizza("Four Horseman Of Cheese", 120, "4Horseman"));


        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.enableDiscountForProduct("Pizza");
        order = new Order(discountCalculator);
    }

    public Menu getMenu() {
        return menu;
    }

    public Order getOrder() {
        return order;
    }

    public void startChat() {
        System.out.println("Welcome to our 'Pizzeria di Stepanio!'");
        System.out.println("Chatbot will serve you, to start look at our menu:");
        System.out.println();

        menu.sort();
        menu.print();
    }

    public void endChat() {
        System.out.println("Your order: ");
        System.out.println();

        order.print();
    }
}
