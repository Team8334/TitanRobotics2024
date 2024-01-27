package frc.Auto.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelAction implements Actions
{
    private ArrayList<Actions> actionsToExecute;

    /**
     * Takes list of actions
     */
    public ParallelAction(List<Actions> actions)
    {
        actionsToExecute = new ArrayList<>(actions);
    }

    /**
     * Takes array of actions
     */
    public ParallelAction(Actions... actions)
    {
        actionsToExecute = new ArrayList<>(Arrays.asList(actions));
    }

    @Override
    public void start()
    {
        actionsToExecute.forEach(Actions::start);
    }

    @Override
    public void update()
    {
        actionsToExecute.forEach(Actions::update);
    }

    @Override
    public boolean isFinished()
    {
        for (Actions action : actionsToExecute) {
            if (!action.isFinished()) { return false; }
        }
        return true;
    }

    @Override
    public void done()
    {
        actionsToExecute.forEach(Actions::done);
    }
}