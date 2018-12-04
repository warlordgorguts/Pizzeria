package pizzabot;

public class FreePizzaRule implements DiscountRules {

    private String messageToClient;
    private int priority;
    private int numberOfFreePizza;
    private boolean isActive;

    public FreePizzaRule(String messageToClient, int priority, int numberOfFreePizza) {
        this.messageToClient = messageToClient;
        this.priority = priority;
        this.numberOfFreePizza = numberOfFreePizza;
    }

    public void executeRule(){

    }

}
