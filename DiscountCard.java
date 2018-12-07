package pizzabot;

public class DiscountCard implements DiscountRules {

    private float discountCost;
    private int discountAmount;
    private boolean isActive;
    private boolean isReusable;
    private boolean needToReuse;
    private State state;

    public DiscountCard(State state, boolean isActive, boolean isReusable) {
        this.state = state;
        this.isActive = isActive;
        this.isReusable = isReusable;
    }

    public int getCardDiscount(String cardNumber) {
        return new JDBCReader().SelectDiscount(cardNumber);
    }

    @Override
    public void checkRule(Order order, Validator validator) {
        if ((!state.getValue() && isActive) || (state.getValue() && isActive && needToReuse)) {
            state.setValue(true);
            
            if (isReusable){
                needToReuse = true;
            }

            System.out.println("Your discount card number?");
            String cardNumber = new Reader().readString();
            discountAmount = getCardDiscount(cardNumber);
        }
    }

    @Override
    public void executeRule(Order order) {
        if (isActive) {
            discountCost = order.getPrice() * (discountAmount / 100);
            order.setPrice(order.getPrice() - discountCost);
        }
    }

    @Override
    public void printRule() {
        System.out.println("Discount: " + discountCost);
    }
}
