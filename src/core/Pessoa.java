package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents a Pessoa.
 */
public abstract class Pessoa implements Serializable {
    protected String nome;
    protected String email;
    protected ArrayList<Task> tasks;

    /**
     * Creates a Pessoa with his name, email and research center.
     *
     * @param nome  String with the pessoa's name.
     * @param email String with the pessoa's email.
     */
    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Gets the pessoa's name.
     *
     * @return String with the pessoa's name.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Sets the pessoa's name.
     *
     * @param nome String with the pessoa's name.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Removes a task from the pessoa's tasks.
     *
     * @param task Task object that will be removed from pessoa's tasks.
     */
    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.setResponsavel(null);
    }

    /**
     * Adds a task to the pessoa's tasks.
     *
     * @param task Task object that will be added to pessoa's tasks.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        task.setResponsavel(this);
    }

    /**
     * Gets the pessoa's workload in a certain day.
     *
     * @param dia Calendar with the day that the workload refers to.
     * @return double with the pessoa's workload on the day passed as argument.
     */
    public double getSobrecarga(Calendar dia) {
        double sobrecarga = 0;
        for (Task task : this.tasks) {
            if (task.getPercentage() != 100) {
                //Em cada tarefa se o dia estiver entre o inicio e o fim da mesma, incrementa a sobrecarga
                if ((dia.after(task.getInicio()) && dia.before(task.getEtc())) || dia.compareTo(task.getInicio()) == 0 || dia.compareTo(task.getEtc()) == 0) {
                    sobrecarga += task.getEsforco();
                }
            }
        }
        return sobrecarga;
    }

    /**
     * Gets the cost in a month.
     *
     * @return int with the cost of the calling Bolseiro in a month.
     */
    public abstract int getCusto();

    @Override
    public String toString() {
        return nome;
    }
}
