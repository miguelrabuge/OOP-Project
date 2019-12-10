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
     * @param inicioBolsa Calendar with the starting date of the licenciado's scholarship.
     * @param fimBolsa Calendar with the ending date of the licenciado's scholarship.
     */
    public Licenciado(String nome, String email, Calendar inicioBolsa, Calendar fimBolsa) {
        super(nome, email, inicioBolsa, fimBolsa);
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
