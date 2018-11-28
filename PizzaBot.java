package pizzabot;

public class PizzaBot {

    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot();
        Validator validator = new Validator();
        Reader reader = new Reader();
        chatBot.startChat();
        validator.validate(chatBot, reader);
        chatBot.endChat();
    }
}
