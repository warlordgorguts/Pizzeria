package pizzabot;

public class PizzaBot {

    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot();
        chatBot.startChat();
        new Validator().validate(chatBot.getOrder());
        chatBot.endChat();
    }
}
