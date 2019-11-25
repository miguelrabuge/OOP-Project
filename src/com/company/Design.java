package classes;

import java.util.Calendar;

public class Design extends Task {

    public Design(Calendar inicio, Calendar fim, Calendar eta, Pessoa responsavel){//nao inclui o percentage, pois esta e comecada a 0 e depois e alterada usando setPercentage()
        super(inicio, fim, eta, responsavel);
    }

    public double getEsforco(){
        return 0.5;
    }

}
