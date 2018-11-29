package pizzabot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class Menu extends ArrayList<Item> {

    private static Menu instance;

    private Menu() {
    }

    public static synchronized Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
            instance.add(new Drink("Apple Juice", 40, "AppleJuice"));
            instance.add(new Drink("Red Wine", 40, "RedWine"));
            instance.add(new Drink("Tomato Juice", 30, "TomatoJuice"));
            instance.add(new Drink("Sweet Lemonade", 35, "Lemonade"));
//          menu.add(new Pizza("Caesar", 100, "Caesar"));
//          menu.add(new Pizza("Milano", 90, "Milano"));
//          menu.add(new Pizza("Tropico", 90, "Tropico"));
//          menu.add(new Pizza("Special", 110, "Special"));
//          menu.add(new Pizza("Margharita", 80, "Margharita"));
//          menu.add(new Pizza("Four Horseman Of Cheese", 140, "4Horseman"));
        }
        return instance;
    }

    public void print() {
        sort(Comparator.comparingInt(Item::getPriority).thenComparing(Item::getName));
        forEach(Item::print);
    }

    public Optional<Item> get(String commandString) {
        return stream().filter(i -> i.getCommandToPrepare().equals(commandString)).findAny();
    }

}
