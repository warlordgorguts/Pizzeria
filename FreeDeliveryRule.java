package pizzabot;

public class FreeDeliveryRule implements DiscountRules{

    private int threshold;
    private float deliveryCost;
    private String messageToClient;
    private int priority;
    private boolean isActive;
    private boolean isReusable;
    private boolean needToReuse;
    private State state;

    public FreeDeliveryRule(State state, boolean isActive, boolean isReusable) {
        this.state = state;
        this.isActive = isActive;
        this.isReusable = isReusable;
    }

    public void checkRule(Order order, Validator validator) {
        if ((!state.getValue() && isActive) || (state.getValue() && isActive && needToReuse)) {
            state.setValue(true);
            boolean awaitingAnswer = true;

            if (isReusable){
                needToReuse = true;
            }

            if ((threshold - order.getPrice()) <= deliveryCost) {

                while (awaitingAnswer) {
                    switch (new Reader().readString()) {
                        case "n": {
                            awaitingAnswer = false;
                            break;
                        }
                        case "y": {
                            awaitingAnswer = false;
                            System.out.println("Type product command:");
                            state.setValue(false);
                            validator.validate(order);
                            break;
                        }
                        default: {
                            System.out.println("Type one of the following (y/n)");
                        }
                    }
                }
            } else {
                order.setPrice(order.getPrice() + deliveryCost);
            }
        }
    }

    @Override
    public void executeRule(Order order) {
        if (isActive) {
            if (order.getPrice() >= threshold) {
                deliveryCost = 0;
            } else {
                order.setPrice(order.getPrice() + deliveryCost);
            }
        }
    }

    @Override
    public void printRule() {
        System.out.println("Delivery: " + deliveryCost);
    }

    public State getState() {
        return state;
    }
}
