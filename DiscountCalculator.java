package pizzabot;

import java.util.ArrayList;
import java.util.List;

public class DiscountCalculator {

    private List<String> listOfDiscount = new ArrayList<>();

    public void enableDiscountForProduct(String productDiscountCode) {
        if (!listOfDiscount.contains(productDiscountCode)) {
            listOfDiscount.add(productDiscountCode);
        }
    }

    public void disableDiscountForProduct(String productDiscountCode) {
        listOfDiscount.remove(productDiscountCode);
    }

    public boolean checkDiscountForProduct(String productDiscountCode) {
        return listOfDiscount.contains(productDiscountCode);
    }
}
