package classes;

import java.util.Calendar;

abstract class Estudante extends Bolseiro {

    protected Docente orientador;

    public Estudante(String nome, String email, Calendar inicioBolsa, Calendar fimBolsa, Docente orientador){
        super(nome, email, inicioBolsa, fimBolsa);
        this.orientador = orientador;
    }

    public Docente getOrientador(){
        return this.orientador;
    }

    public void setOrientador(Docente orientador){
        this.orientador = orientador;
    }

}
