package pizzabot;

public class DiscountCard {

    private String cardNumber;
    private int cardDiscount = 5;

    private DiscountCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber(){
        return cardNumber;
    }

    public int getCardDiscount(){
        return cardDiscount;
    }

    public void setCardDiscount(int cardDiscount){
        this.cardDiscount = cardDiscount;
    }

}