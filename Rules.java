package pizzabot;

public interface Rules {

    boolean checkRule(Order order);

    void execute(Order order);

    void printRule();

    int getPriority();

    State getState();

}
