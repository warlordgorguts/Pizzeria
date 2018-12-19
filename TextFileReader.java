package pizzabot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileReader {

    private String textFilePath;
    private String textSplitter;

    public TextFileReader(String textFilePath, String textSplitter) {
        this.textFilePath = textFilePath;
        this.textSplitter = textSplitter;
    }

    public List<Pizza> read() {
        List<Pizza> pizzas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(textFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] pizza = line.split(textSplitter);
                pizzas.add(new Pizza(pizza[0], Float.valueOf(pizza[1]), pizza[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pizzas;
    }

}
