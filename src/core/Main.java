package core;

import UI.IntroUI;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<ResearchCenter> researchCenters = new ArrayList<ResearchCenter>();
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        ArrayList<Project> projetos = new ArrayList<>();
        Docente d;
        researchCenters.add(new ResearchCenter("CISUC"));
        for (int i = 0; i < 50; i++) {
            d = new Docente("Miguel","miguelrabuge12@gmail.com",null,"AI");
            pessoas.add(d);
            pessoas.add(new Doutorado("João","joao@",null,null));
            projetos.add(new Project("DENSER","DSR",null, null,d));
        }

        researchCenters.get(0).setPessoas(pessoas);
        researchCenters.get(0).setProjects(projetos);
        researchCenters.add(new ResearchCenter("CISUP"));
        new IntroUI(researchCenters);
    }
}
