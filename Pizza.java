package pizzabot;

public abstract class Pizza implements Item {

    //private int quantity = 0;
    //private int discountedQuantity = 0;
    private String discount = "Pizza";

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public String getDiscount() {
        return discount;
    }

    @Override
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    @Override
    public void printMenu() {
        System.out.println("------------------");
        System.out.println(getName() + ", " + getPrice());
        System.out.println("To buy type: " + getCommandToPrepare());
        System.out.println();
    }

    /*
    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int getDiscountedQuantity() {
        return discountedQuantity;
    }

    @Override
    public void setDiscountedQuantity(int discountedQuantity) {
        this.discountedQuantity = discountedQuantity;
    }


    @Override
    public void printOrder() {
        System.out.println(getQuantity() + ", " + getName() + " x " + getPrice());
    }
    */
}
