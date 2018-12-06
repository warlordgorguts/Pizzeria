package pizzabot;

public interface DiscountRules {

    void checkRule(Order order, Validator validator);
    void executeRule(Order order);
    void printRule();

}
