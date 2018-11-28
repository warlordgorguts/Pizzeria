package pizzabot;

import java.util.HashMap;

public class Menu extends Composite {

    private static Menu _instance = null;
    private HashMap<String, Item> commandsHash = new HashMap<>();

    private Menu(){}

    public static synchronized Menu getInstance() {
        if (_instance == null)
            _instance = new Menu();
        return _instance;
    }

    @Override
    public void add(Item item) {

        if (!getList().contains(item)) {
            getList().add(item);
            commandsHash.put(item.getCommandToPrepare(), item);
        }
    }

    public Item getObjectByCommand(String commandToPrepare) {

        return commandsHash.get(commandToPrepare);
    }

    @Override
    public void print() {
        getList().forEach(Item::printMenu);
    }
}
