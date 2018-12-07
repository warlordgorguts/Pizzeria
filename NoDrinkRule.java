package pizzabot;

public class NoDrinkRule implements DiscountRules{

    private String messageToClient;
    private int priority;
    private String discount = "Drink";
    private boolean isActive;
    private boolean isReusable;
    private boolean needToReuse;
    private State state;

    public NoDrinkRule(State state, boolean isActive, boolean isReusable, String messageToClient, int priority) {
        this.messageToClient = messageToClient;
        this.priority = priority;
        this.state = state;
        this.isActive = isActive;
        this.isReusable = isReusable;
    }

    @Override
    public void checkRule(Order order, Validator validator) {
        if ((!state.getValue() && isActive) || (state.getValue() && isActive && needToReuse)) {

            state.setValue(true);

            if (isReusable){
                needToReuse = true;
            }

        }
    }

    @Override
    public void executeRule(Order order) {

    }

    @Override
    public void printRule() {

    }

}
