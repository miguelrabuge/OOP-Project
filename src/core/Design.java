package core;

import java.util.Calendar;

/**
 * Represents a Design task.
 */
public class Design extends Task {

    /**
     * Creates a Design task with the beginning date and the estimated time to complete it.
     * @param inicio Calendar with the starting date of the design task.
     * @param eta Calendar with the estimated time to complete the task.
     */
    public Design(Calendar inicio, Calendar eta){
        super(inicio, eta);
    }

    /**
     * Gets the effort to do the design task.
     * @return int with the effort to do the design task.
     */
    public double getEsforco(){
        return 0.5;
    }

}
