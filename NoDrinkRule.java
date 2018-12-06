package pizzabot;

public class NoDrinkRule implements DiscountRules{

    private String messageToClient;
    private int priority;
    private boolean isActive;

    public NoDrinkRule(String messageToClient, int priority) {
        this.messageToClient = messageToClient;
        this.priority = priority;
    }

    @Override
    public void checkRule(Order order, Validator validator) {

    }

    @Override
    public void executeRule(Order order) {

    }

    @Override
    public void printRule() {

    }

}
