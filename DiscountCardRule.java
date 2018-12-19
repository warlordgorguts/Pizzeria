package pizzabot;

public class DiscountCardRule extends DiscountRule {

    private float discountCost;
    private int discountAmount;

    public DiscountCardRule(State state, boolean isActive, boolean isReusable) {
        super.state = state;
        super.isActive = isActive;
        super.isReusable = isReusable;
    }


    public int getCardDiscount(int cardNumber) {
        return new JDBCReader().SelectDiscount(cardNumber);
    }

    @Override
    public boolean checkRule(Order order) {
        if (isApplicable()){
            System.out.println("Your discount card number?");
            int cardNumber = new InputValidator().validateCardNumber();
            discountAmount = getCardDiscount(cardNumber);
        }
        return false;
    }

    @Override
    public void execute(Order order) {
        if (isActive && needToExecute) {
            float priceChange;
            priceChange = (order.getPrice() + discountCost) * discountAmount / 100;
            discountCost = priceChange;
            order.setPrice(order.getPrice() - discountCost);
        }
    }

    @Override
    public void printRule() {
        if (isActive && needToExecute) {
            System.out.println("Discount: " + discountCost);
        }
    }

}
