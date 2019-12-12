package core;

import java.util.Calendar;

/**
 * Represents a Bolseiro.
 */
public abstract class Bolseiro extends Pessoa {

    protected Calendar inicioBolsa;
    protected Calendar fimBolsa;
    protected Project projeto;

    /**
     * Creates a Bolseiro with his name, email, research center, beginning of the scholarship and end of the scholarship.
     * @param nome String with the bolseiro's name.
     * @param email String with the bolseiro's email.
     * @param inicioBolsa Calendat with the starting date of the bolseiro's scholarship.
     * @param fimBolsa Calendar with the ending date of the bolseiro's scholarship.
     */
    public Bolseiro(String nome, String email,Calendar inicioBolsa, Calendar fimBolsa){
        super(nome, email);
        this.inicioBolsa = inicioBolsa;
        this.fimBolsa = fimBolsa;
        this.projeto = null;
    }

    /**
     * Gets the starting date of the bolseiro's scholarship.
     * @return Calendar with the starting date of the bolseiro's scholarship.
     */
    public Calendar getInicioBolsa() {
        return this.inicioBolsa;
    }

    /**
     * Sets the starting date of the bolseiro's scholarship.
     * @param inicioBolsa Calendar with the starting date of the bolseiro's scholarship.
     */
    public void setInicioBolsa(Calendar inicioBolsa) {
        this.inicioBolsa = inicioBolsa;
    }

    /**
     * Gets the ending date of the bolseiro's scholarship.
     * @return Calendar with the ending date of the bolseiro's scholarship.
     */
    public Calendar getFimBolsa() {
        return this.fimBolsa;
    }

    /**
     * Sets the ending date of the bolseiro's scholarship.
     * @param fimBolsa Calendar with the ending date of the bolseiro's scholarship.
     */
    public void setFimBolsa(Calendar fimBolsa) {
        this.fimBolsa = fimBolsa;
    }

    /**
     * Gets the bolseiro's project.
     * @return Project with the bolseiro's project.
     */
    public Project getProjeto() {
        return this.projeto;
    }

    /**
     * Sets the bolseiro's project.
     * @param projetos Project with the bolseiro's project.
     */
    public void setProjeto(Project projetos) {
        this.projeto = projetos;
    }

    @Override
    public String toString() {
        return  nome + " " +
                inicioBolsa.get(Calendar.DAY_OF_MONTH) + "/" + (inicioBolsa.get(Calendar.MONTH) + 1)+ "/" + inicioBolsa.get(Calendar.YEAR) +
                " - " + fimBolsa.get(Calendar.DAY_OF_MONTH) + "/" + (fimBolsa.get(Calendar.MONTH) + 1)+ "/" + fimBolsa.get(Calendar.YEAR);
    }
}
