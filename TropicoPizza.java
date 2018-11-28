package pizzabot;

public class TropicoPizza extends Pizza {
    @Override
    public float getPrice() {
        return 90.00f;
    }

    @Override
    public String getName() {
        return "Tropico Pizza";
    }

    @Override
    public String getCommandToPrepare() {
        return "Tropico";
    }
}
