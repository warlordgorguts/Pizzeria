/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzabot;

/**
 * @author Bobo
 */
public class FourHorsemanOfCheesePizza extends Pizza {

    @Override
    public float getPrice() {
        return 120.00f;
    }

    @Override
    public String getName() {
        return "Four Horseman Of Cheese Pizza";
    }

    @Override
    public String getCommandToPrepare() {
        return "4Horseman";
    }

}
