package pizzabot;

abstract class DiscountRule implements Rules {

    boolean isActive;
    boolean needToExecute;
    boolean isReusable;
    boolean needToReuse;
    private int priority;
    State state;

    @Override
    public boolean checkRule(Order order){ return false;}

    @Override
    public void execute(Order order){}

    @Override
    public void printRule(){}

    @Override
    public int getPriority(){return priority;}

    @Override
    public State getState(){return state;}

    public boolean isApplicable() {
        if ((!state.getValue() && isActive) || (state.getValue() && isActive && needToReuse)) {

            state.setValue(true);

            needToExecute = true;

            needToReuse = isReusable;

            return true;
        }
        return false;
    }

}
