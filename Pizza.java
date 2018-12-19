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
    public void print() {
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
}
