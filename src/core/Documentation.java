package core;

import java.util.Calendar;

/**
 * Represents a Documentation task.
 */
public class Documentation extends Task {

    /**
     * Creates a Documentation task with its starting date and the estimated time to complete it.
     * @param inicio Calendar with the starting date of the task.
     * @param eta Calendar with the estimated date to complete the documentation task.
     */
    public Documentation(Calendar inicio, Calendar eta){
        super(inicio, eta);
    }

    /**
     * Gets the effort to do the documentation task.
     * @return int with the effort to do the documentation task.
     */
    public double getEsforco(){
        return 0.25;
    }
}
