package pizzabot;

public class CaesarPizza extends Pizza {

    @Override
    public float getPrice() {
        return 100.00f;
    }

    @Override
    public String getName() {
        return "Caesar Pizza";
    }

    @Override
    public String getCommandToPrepare() {
        return "Caesar";
    }
}
