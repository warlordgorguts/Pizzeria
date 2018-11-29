package pizzabot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFileReader {

    private String textFilePath;
    private String textSplitter;

    public TextFileReader(String textFilePath, String textSplitter) {
        this.textFilePath = textFilePath;
        this.textSplitter = textSplitter;
    }

    public void readFile() {

        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(textFilePath))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] pizza = line.split(textSplitter);
                Menu.getInstance().add(new Pizza(pizza[0], Float.valueOf(pizza[1]), pizza[2]));

                //System.out.println("Pizza [name= " + pizza[0] + " , price=" + pizza[1] + "]");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
