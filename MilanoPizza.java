/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzabot;

/**
 * @author Bobo
 */
public class MilanoPizza extends Pizza {

    @Override
    public float getPrice() {
        return 100.00f;
    }

    @Override
    public String getName() {
        return "Milano Pizza";
    }

    @Override
    public String getCommandToPrepare() {
        return "Milano";
    }
}
