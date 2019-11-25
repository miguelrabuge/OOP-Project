package classes;

import java.util.Calendar;

public class Licenciado extends Estudante {

    public Licenciado(String nome, String email, Calendar inicioBolsa, Calendar fimBolsa, Docente orientador){
        super(nome, email, inicioBolsa, fimBolsa, orientador);
    }

    public int getCusto(){
        return 800;
    }

}
