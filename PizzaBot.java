package pizzabot;

public class PizzaBot {

    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot();
        JDBCReader testJDBCReader = new JDBCReader();
        testJDBCReader.Select();
        chatBot.startChat();
        new Validator().validate(chatBot.getOrder());
        chatBot.endChat();
    }
}
