package core;

import java.util.Calendar;

/**
 * Represents a Doutorado.
 */
public class Doutorado extends Bolseiro {

    /**
     * Creates a Doutorado with his name, email, research center, beginning o the scholarship and the end of the scholarship.
     * @param nome String with the doutorado's name.
     * @param email String with the doutorado's name.
     * @param researchCenter ResearchCenter object with the doutorado's research center.
     * @param inicioBolsa Calendar with the starting date of the doutorado's scholarship.
     * @param fimBolsa Calendar with the ending date of the doutorado's scholarship.
     */
    public Doutorado(String nome, String email, ResearchCenter researchCenter,Calendar inicioBolsa, Calendar fimBolsa){
        super(nome, email, researchCenter,inicioBolsa, fimBolsa);
    }

    /**
     * Gets the doutorado's cost in a month.
     * @return int with the doutorado's cost in a month.
     */
    public int getCusto(){
        return 1200;
    }

}