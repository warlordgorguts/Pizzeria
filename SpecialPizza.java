package pizzabot;

public class SpecialPizza extends Pizza {
    @Override
    public float getPrice() {
        return 110.00f;
    }

    @Override
    public String getName() {
        return "Special Pizza";
    }

    @Override
    public String getCommandToPrepare() {
        return "Special";
    }
}
