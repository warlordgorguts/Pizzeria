package pizzabot;

public interface Item {

    float getPrice();

    String getName();

    String getCommandToPrepare();

    int getPriority();

    String getDiscount();

    void setDiscount(String discount);

    void print();
}
