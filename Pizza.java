package pizzabot;

public class Pizza implements Item {

    private String name;
    private float price;
    private String commandToPrepare;
    private String discount = "Pizza";

    public Pizza(String name, float price, String commandToPrepare) {
        this.name = name;
        this.price = price;
        this.commandToPrepare = commandToPrepare;
    }

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

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCommandToPrepare() {
        return commandToPrepare;
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
