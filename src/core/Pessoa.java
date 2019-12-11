package core;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents a Pessoa.
 */
public abstract class Pessoa {

    protected ArrayList<Task> tasks;
    protected String nome;
    protected String email;

    /**
     * Creates a Pessoa with his name, email and research center.
     * @param nome String with the pessoa's name.
     * @param email String with the pessoa's email.
     */
    public Pessoa(String nome, String email){
        this.nome = nome;
        this.email = email;
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Gets the pessoa's tasks.
     * @return ArrayList with the pessoa's tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Sets the pessoa's tasks.
     * @param tasks ArrayList with the pessoa's tasks.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the pessoa's name.
     * @return String with the pessoa's name.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Sets the pessoa's name.
     * @param nome String with the pessoa's name.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the pessoa's email.
     * @return String with the pessoa's email.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the pessoa's email.
     * @param email String with the pessoa's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Removes a task from the pessoa's tasks.
     * @param task Task object that will be removed from pessoa's tasks.
     */
    public void removeTask(Task task){//remove uma task das tasks da pessoa
        this.tasks.remove(task);
        task.setResponsavel(null);//volta a colocar a task como livre, ou seja nao tem um responsavel
    }

    /**
     * Adds a task to the pessoa's tasks.
     * @param task Task object that will be added to pessoa's tasks.
     */
    public void addTask(Task task){//adiciona uma task as tasks da pessoa
        this.tasks.add(task);
        task.setResponsavel(this);//penso que isto funcione
    }

    /**
     * Gets the pessoa's workload in a certain day.
     * @param dia Calendar with the day that the workload refers to.
     * @return double with the pessoa's workload on the day passed as argument.
     */
    public double getSobrecarga(Calendar dia){//retorna a sobrecarga da pessoa
        double sobrecarga = 0;
        for(Task task : this.tasks){
            if( (dia.after(task.getInicio()) && dia.before(task.getEtc())) || dia.compareTo(task.getInicio()) == 0 || dia.compareTo(task.getEtc()) == 0 ){
                sobrecarga += task.getEsforco();
            }
        }
        return sobrecarga;
    }

    /**
     *Gets the cost in a month.
     * @return int with the cost of the calling Bolseiro in a month.
     */
    public abstract int getCusto();

    @Override
    public String toString() {
        return nome;
    }
}
