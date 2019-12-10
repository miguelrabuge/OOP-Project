package core;
import java.util.Calendar;

/**
 * Represents a Development task.
 */
public class Development extends Task {

    /**
     * Creates a Development task with its starting date and the estimated time to complete it.
     * @param inicio Calendar with the starting date of the task.
     * @param eta Calendar with the estimated date to complete the task.
     */
    public Development(Calendar inicio, Calendar eta){
        super(inicio, eta);
    }

    /**
     * Gets the effort to do the Development task.
     * @return int with the effort to do the Development task.
     */
    public double getEsforco(){
        return 1;
    }

}
