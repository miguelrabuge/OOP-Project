package core;

import java.util.Calendar;

/**
 * Represents a Estudante.
 */
public abstract class Estudante extends Bolseiro {

    protected Docente orientador;

    /**
     * Creates a Estudante with his name, email, research center, beginning of the scholarship, end of the scholarship and his advisor.
     * @param nome String with the estudante's name.
     * @param email String with the estudante's email.
     * @param researchCenter ResearchCenter object with the estudante's research center.
     * @param inicioBolsa Calendar with the starting date of the estudante's scholarship.
     * @param fimBolsa Calendar with the ending date of the estudante's scholarship.
     * @param orientador Docente object with the information of the estudante's advisor.
     */
    public Estudante(String nome, String email, ResearchCenter researchCenter,Calendar inicioBolsa, Calendar fimBolsa, Docente orientador){
        super(nome, email, researchCenter,inicioBolsa, fimBolsa);
        this.orientador = orientador;
    }

    /**
     * Gets the estudante's advisor.
     * @return Docente with the estudante's advisor.
     */
    public Docente getOrientador(){
        return this.orientador;
    }

    /**
     * Sets the estudante's advisor.
     * @param orientador Docente with the estudante's advisor.
     */
    public void setOrientador(Docente orientador){
        this.orientador = orientador;
    }

}
