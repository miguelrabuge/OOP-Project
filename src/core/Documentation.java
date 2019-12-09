package core;

import java.util.Calendar;

public class Documentation extends Task {

    public Documentation(Calendar inicio, Calendar fim, Calendar eta, Pessoa responsavel){
        super(inicio, fim, eta, responsavel);
    }

    public double getEsforco(){
        return 0.25;
    }
}
