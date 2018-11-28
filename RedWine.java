/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzabot;

/**
 * @author Bobo
 */
public class RedWine extends Drink {
    @Override
    public float getPrice() {
        return 40.00f;
    }

    @Override
    public String getName() {
        return "Red Wine";
    }

    @Override
    public String getCommandToPrepare() {
        return "RedWine";
    }
}
