package core;
import java.util.Calendar;

public class Development extends Task {

    public Development(Calendar inicio, Calendar fim, Calendar eta, Pessoa responsavel){
        super(inicio, fim, eta, responsavel);
    }

    public double getEsforco(){
        return 1;
    }

}
