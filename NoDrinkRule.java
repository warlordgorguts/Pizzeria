package pizzabot;

public class NoDrinkRule {

    private String messageToClient;
    private int priority;
    private boolean isActive;

    public NoDrinkRule(String messageToClient, int priority) {
        this.messageToClient = messageToClient;
        this.priority = priority;
    }

}
