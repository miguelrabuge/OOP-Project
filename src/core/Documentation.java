package core;

import java.util.Calendar;

/**
 * Represents a Documentation task.
 */
public class Documentation extends Task {

    /**
     * Creates a Documentation task with its starting date and the estimated time to complete it.
     *
     * @param inicio Calendar with the starting date of the task.
     * @param etc    Calendar with the estimated date to complete the documentation task.
     */
    public Documentation(Calendar inicio, Calendar etc) {
        super(inicio, etc);
    }

    /**
     * Gets the effort to do the documentation task.
     *
     * @return int with the effort to do the documentation task.
     */
    public double getEsforco() {
        return 0.25;
    }

    @Override
    public String toString() {
        return "[" + this.responsavel + "] Documentação\n" + " (" + this.percentage + ") " +
                inicio.get(Calendar.DAY_OF_MONTH) + "/" + (inicio.get(Calendar.MONTH) + 1) + "/" + inicio.get(Calendar.YEAR) +
                " - " + etc.get(Calendar.DAY_OF_MONTH) + "/" + (etc.get(Calendar.MONTH) + 1) + "/" + etc.get(Calendar.YEAR);
    }
}
