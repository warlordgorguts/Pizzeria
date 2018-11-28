package pizzabot;

public class ChatBot {

    private Order order;
    private Menu menu = Menu.getInstance();

    public ChatBot() {
        MilanoPizza milanoPizza = new MilanoPizza();
        TropicoPizza tropicoPizza = new TropicoPizza();
        CaesarPizza caesarPizza = new CaesarPizza();
        SpecialPizza specialPizza = new SpecialPizza();
        MargharitaPizza margharitaPizza = new MargharitaPizza();
        FourHorsemanOfCheesePizza fourHorsemanOfCheesePizza = new FourHorsemanOfCheesePizza();
        AppleJuice appleJuice = new AppleJuice();
        RedWine redWine = new RedWine();

        menu.add(appleJuice);
        menu.add(redWine);
        menu.add(fourHorsemanOfCheesePizza);
        menu.add(margharitaPizza);
        menu.add(milanoPizza);
        menu.add(tropicoPizza);
        menu.add(caesarPizza);
        menu.add(specialPizza);

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
