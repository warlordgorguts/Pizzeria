package pizzabot;

public interface Item {

    float getPrice();

    String getName();

    String getCommandToPrepare();

    int getPriority();

    String getDiscount();

    void setDiscount(String discount);

    void print();

    //int getDiscountedQuantity();

    //void setDiscountedQuantity(int discountedQuantity);

    //int getQuantity();

    //void setQuantity(int i);

    //void printOrder();

}
