package frc.Auto.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeriesAction implements Actions
{
    private ArrayList<Actions> actionsToExecute;
    private int currentActionIndex;

    /**
     * Takes list of actions
     */
    public SeriesAction(List<Actions> actions)
    {
        actionsToExecute = new ArrayList<>(actions);
    }

    /**
     * Takes array of actions
     */
    public SeriesAction(Actions... actions)
    {
        actionsToExecute = new ArrayList<>(Arrays.asList(actions));
    }

    @Override
    public void start()
    {
        actionsToExecute.get(currentActionIndex).start();
    }

    @Override
    public void update()
    {
        actionsToExecute.get(currentActionIndex).update();
    }

    @Override
    public boolean isFinished()
    {
        if (actionsToExecute.get(currentActionIndex).isFinished()) {
            actionsToExecute.get(currentActionIndex).done();
            currentActionIndex++;
            //currentActionIndex will be incremented at this point
            //therefore if the current action is the last one, it will be equal to the the array list size instead of said size minus one
            if (currentActionIndex == actionsToExecute.size()) { return true; }
            actionsToExecute.get(currentActionIndex).start();
        }
        return false;
    }

    @Override
    public void done()
    {
    }
}