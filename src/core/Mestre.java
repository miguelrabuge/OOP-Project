package core;

import java.util.Calendar;

/**
 * Represent a Mestre.
 */
public class Mestre extends Estudante {

    /**
     * Creates a Mestre with his name, email, research center, beginning of the scholarship, end of the scholarship and his advisor.
     * @param nome String with the mestre's name.
     * @param email String with the mestre's name.
     * @param researchCenter ResearchCenter object with the mestre's research center.
     * @param inicioBolsa Calendar with the starting date of the mestre's scholarship.
     * @param fimBolsa Calendar with the ending date of the mestre's scholarship.
     * @param orientador Docente object with the mestre's advisor.
     */
    public Mestre(String nome, String email, ResearchCenter researchCenter,Calendar inicioBolsa, Calendar fimBolsa, Docente orientador){
        super(nome, email, researchCenter,inicioBolsa, fimBolsa, orientador);
    }

    /**
     * Gets the mestre's cost in a month.
     * @return int with the mestre's cost in a month.
     */
    @Override
    public int getCusto(){
        return 1000;
    }

}
