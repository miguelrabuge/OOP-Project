package core;
import java.util.Calendar;

/**
 * Represents a Development task.
 */
public class Development extends Task {

    /**
     * Creates a Development task with its starting date and the estimated time to complete it.
     * @param inicio Calendar with the starting date of the task.
     * @param etc Calendar with the estimated date to complete the task.
     */
    public Development(Calendar inicio, Calendar etc){
        super(inicio, etc);
    }

    /**
     * Gets the effort to do the Development task.
     * @return int with the effort to do the Development task.
     */
    public double getEsforco(){
        return 1;
    }

    @Override
    public String toString() {
        return  "Desenvolvimento (" + this.percentage + ") " +
                inicio.get(Calendar.DAY_OF_MONTH) + "/" + (inicio.get(Calendar.MONTH) + 1)+ "/" + inicio.get(Calendar.YEAR) +
                " - " + etc.get(Calendar.DAY_OF_MONTH) + "/" + (etc.get(Calendar.MONTH) + 1)+ "/" + etc.get(Calendar.YEAR);
    }

}
