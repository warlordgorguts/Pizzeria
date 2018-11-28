/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzabot;

/**
 * @author Bobo
 */
public class MargharitaPizza extends Pizza {

    @Override
    public float getPrice() {
        return 80.00f;
    }

    @Override
    public String getName() {
        return "Margharita Pizza";
    }

    @Override
    public String getCommandToPrepare() {
        return "Margharita";
    }
}
