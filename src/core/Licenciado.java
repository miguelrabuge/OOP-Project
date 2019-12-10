package core;

import java.util.Calendar;

/**
 * Represents a Licenciado.
 */
public class Licenciado extends Estudante {

    /**
     * Creates a Licenciado with his name, email, research center, beginning of the scholarship, end of the scholarship and his advisor.
     * @param nome String with the licenciado's name.
     * @param email String with the licenciado's email.
     * @param researchCenter ResearchCenter object with the licenciado's research center.
     * @param inicioBolsa Calendar with the starting date of the licenciado's scholarship.
     * @param fimBolsa Calendar with the ending date of the licenciado's scholarship.
     * @param orientador Docente object with the licenciado's advisor.
     */
    public Licenciado(String nome, String email, ResearchCenter researchCenter, Calendar inicioBolsa, Calendar fimBolsa, Docente orientador) {
        super(nome, email, researchCenter, inicioBolsa, fimBolsa, orientador);
    }

    /**
     * Gets the licenciado's cost in a month.
     * @return int with the licenciado's cost in a month.
     */
    @Override
    public int getCusto(){
        return 800;
    }

}
