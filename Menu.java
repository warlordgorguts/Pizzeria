package pizzabot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class Menu extends ArrayList<Item> {

    private static Menu instance;

    private Menu() {
        TextFileReader textFileReader = new TextFileReader("C:\\Users\\anton\\Downloads\\Java\\TestFiles\\Pizza.csv", ",");
        addAll(textFileReader.read());
        add(new Drink("Apple Juice", 40, "AppleJuice"));
        add(new Drink("Red Wine", 40, "RedWine"));
        add(new Drink("Tomato Juice", 30, "TomatoJuice"));
        add(new Drink("Sweet Lemonade", 35, "Lemonade"));
//          add(new Pizza("Caesar", 100, "Caesar"));
//          add(new Pizza("Milano", 90, "Milano"));
//          add(new Pizza("Tropico", 90, "Tropico"));
//          add(new Pizza("Special", 110, "Special"));
//          add(new Pizza("Margharita", 80, "Margharita"));
//          add(new Pizza("Four Horseman Of Cheese", 140, "4Horseman"));
    }

    public static synchronized Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void print() {
        sort(Comparator.comparingInt(Item::getPriority).thenComparing(Item::getName));
        forEach(Item::print);
    }

    public Optional<Item> get(String commandString) {
        return stream().filter(i -> i.getCommandToPrepare().equalsIgnoreCase(commandString)).findAny();
    }

}
