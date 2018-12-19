package pizzabot;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {

    private List<DiscountRule> discountRulesCheckList = new ArrayList<>();
    private List<DiscountRule> discountRulesExecuteList = new ArrayList<>();
    private List<DiscountRule> discountRulesPostExecuteList = new ArrayList<>();
    private List<DiscountRule> discountRulesPrintList;

    public OrderProcessor() {

        State discountCardState = new State();
        State freeDeliveryRuleState = new State();
        State freePizzaRuleState = new State();
        State noDrinkRuleState = new State();

        DiscountRule discountCard = new DiscountCardRule(discountCardState, true, false);
        DiscountRule freeDeliveryRule = new FreeDeliveryRule(freeDeliveryRuleState, true, false,
                300, 30);
        DiscountRule freePizzaRule = new FreeItemRule(freePizzaRuleState, true, true,
                3, "Pizza", "pizzas");
        DiscountRule noLemonadeRule = new NoItemRule(noDrinkRuleState, true, false,
                "Drink", "Maybe you want to order lemonade?");
        DiscountRule noDrinkRule = new NoItemRule(noDrinkRuleState, true, false,
                "Drink", "Maybe you want to order some drink?");

        discountRulesCheckList.add(noLemonadeRule);
        discountRulesCheckList.add(noDrinkRule);
        discountRulesCheckList.add(freePizzaRule);
        discountRulesCheckList.add(discountCard);
        discountRulesCheckList.add(freeDeliveryRule);

        discountRulesExecuteList.add(freePizzaRule);
        discountRulesExecuteList.add(discountCard);

        discountRulesPostExecuteList.add(discountCard);
        discountRulesPostExecuteList.add(freeDeliveryRule);

        discountRulesPrintList = discountRulesCheckList;
    }

    public void applyRules(Order order) {
        InputValidator inputValidator = new InputValidator();

        for (DiscountRule rule : discountRulesCheckList) {
            boolean awaitingAnswer = rule.checkRule(order);
            if (awaitingAnswer && inputValidator.validateRuleQuestion()) {
                System.out.println("Type product command:");
                inputValidator.validateCommands(order);
                applyRules(order);
                break;
            }

            if (discountRulesExecuteList.contains(rule)) {
                rule.execute(order);
            }
        }
    }

    public void applyPostExecuteRules(Order order) {
        for (DiscountRule rule : discountRulesPostExecuteList) {
            rule.execute(order);
        }
    }

    public void printOrder(Order order) {
        System.out.println("Your order: ");
        System.out.println();
        order.getItemsQuantityMap().forEach((item, q) -> System.out.println(q + ", " + item.getName() + " x " + item.getPrice()));

        for (Rules discountRule : discountRulesPrintList) {
            discountRule.printRule();
        }

        System.out.println("Total price is: " + String.format("%.02f", order.getPrice()));
    }
}
