package pizzabot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Composite {

    private List<Item> items = new ArrayList<>();

    public List<Item> getList() {

        return items;
    }

    public void sort() {

        items.sort(Comparator.comparingInt(Item::getPriority).thenComparing(Item::getName));
    }

    abstract void add(Item item);

    abstract void print();
}
