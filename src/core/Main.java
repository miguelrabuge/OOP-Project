package core;

import UI.IntroUI;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<ResearchCenter> researchCenters = new ArrayList<ResearchCenter>();
        researchCenters.add(new ResearchCenter("CISUC"));
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        for (int i = 0; i < 50; i++) {
            pessoas.add(new Docente("Miguel","miguelrabuge12@gmail.com",null,"LOL","AI"));
        }
        pessoas.add(new Doutorado("João","joao@",null,null,null));
        researchCenters.get(0).setPessoas(pessoas);
        researchCenters.add(new ResearchCenter("CISUP"));
        new IntroUI(researchCenters);
    }
}
