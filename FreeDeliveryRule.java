package pizzabot;

public class FreeDeliveryRule {

    private int threshold;
    private int deliveryCost;
    private String messageToClient;
    private int priority;
    Order order;
    private boolean isActive;
    private State state;

    public FreeDeliveryRule(State state){
        this.state = state;
    }

    public void checkRule() {
        if (!state.getValue() && isActive){
            state.setValue(true);

            boolean awaitingAnswer = true;
            System.out.println("You currently ordered: " + " discounted items.");
            System.out.println("Do you want to buy something else to get free delivery? (y/n)");
            while (awaitingAnswer) {
                switch (new Reader().readString()) {
                    case "n": {
                        awaitingAnswer = false;
                        break;
                    }
                    case "y": {
                        awaitingAnswer = false;
                        System.out.println("Type product command:");
                        break;
                    }
                    default: {
                        System.out.println("Type one of the following (y/n)");
                    }
                }
            }
        }
    }

    public void executeRule() {
        if (isActive) {
            if (order.getPrice() >= threshold){

            } else{
                order.setPrice(order.getPrice() + deliveryCost);
            }
        }
    }

    public void printRule() {

    }

    public State getState(){
        return state;
    }
}
