package core;

import java.util.Calendar;

/**
 * Represents a Task.
 */
public abstract class Task {
    protected Calendar inicio;
    protected Calendar fim;
    protected Calendar etc;
    protected int percentage;
    protected Project projeto;
    protected Pessoa responsavel;


    /**
     * Creates a Task with its starting date and estimated time for completion.
     *
     * @param inicio Calendar with the task's starting date.
     * @param etc    Calendar with the task's estimated time for completion.
     */
    public Task(Calendar inicio, Calendar etc) {
        this.inicio = inicio;
        this.fim = null;
        this.etc = etc;
        this.percentage = 0;
        this.responsavel = null;
    }

    /**
     * Gets the task's starting date.
     *
     * @return Calendar with the task's starting date.
     */
    public Calendar getInicio() {
        return this.inicio;
    }

    /**
     * Sets the task's starting date.
     *
     * @param inicio Calendat with the task's starting date.
     */
    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }

    /**
     * Gets the task's ending date.
     *
     * @return Calendar with the task's ending date.
     */
    public Calendar getFim() {
        return this.fim;
    }

    /**
     * Sets the task's ending date.
     *
     * @param fim Calendar with the task's ending date.
     */
    public void setFim(Calendar fim) {
        this.fim = fim;
    }

    /**
     * Gets the task's estimated time for completion.
     *
     * @return Calendar with the task's estimated time for completion.
     */
    public Calendar getEtc() {
        return this.etc;
    }

    /**
     * Sets the task's estimated time for completion.
     *
     * @param etc Calendar with the task's estimated for completion.
     */
    public void setEtc(Calendar etc) {
        this.etc = etc;
    }

    /**
     * Gets the task's percentage of completion.
     *
     * @return int with the task's percentage of completion.
     */
    public int getPercentage() {
        return this.percentage;
    }

    /**
     * Sets the task's percentage of completion.
     *
     * @param percentage int with the task's percentage of completion.
     */
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    /**
     * Gets the project that the task is associated to.
     *
     * @return Project object that the task belongs to.
     */
    public Project getProjeto() {
        return this.projeto;
    }

    /**
     * Sets the task's project.
     *
     * @param projeto Project object that will be set as task's project.
     */
    public void setProjeto(Project projeto) {
        this.projeto = projeto;
    }

    /**
     * Gets the task's responsible.
     *
     * @return Pessoa object that is responsible for the task.
     */
    public Pessoa getResponsavel() {
        return this.responsavel;
    }

    /**
     * Sets the task's responsible.
     *
     * @param responsavel Pessoa object thar will be designated as responsible for the task.
     */
    public void setResponsavel(Pessoa responsavel) {
        this.responsavel = responsavel;
    }

    /**
     * Gets the task's effort.
     *
     * @return double with the effort that has to be done by the task responsible in order to complete it.
     */
    public abstract double getEsforco();

    /**
     * Checks if a task can be assigned to responsavel.
     *
     * @param responsavel Pessoa object that we want to check the availability.
     * @return
     */
    public boolean checkAvailability(Pessoa responsavel) {
        Calendar dia = (Calendar) this.getInicio().clone();

        while (dia.compareTo(this.getEtc()) < 0) {//enquanto nao chegar ao dia estimado para concluir a tarefa verificamos se a pessoa pode executar a task
            if ((this.getEsforco() + responsavel.getSobrecarga(dia)) > 1) {
                return false;
            }
            dia.add(Calendar.DAY_OF_MONTH, 1);
        }
        return true;
    }
}
