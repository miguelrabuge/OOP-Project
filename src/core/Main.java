package core;

import UI.IntroUI;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
        researchCenters.add(loadFile());
        new IntroUI(researchCenters);
    }


    public static ResearchCenter loadFile(){
        Pessoa pessoa;
        Project project;
        ResearchCenter researchCenter = null;
        Estudante estudante;
        Docente docente;
        Task task;
        Calendar dataStart = new GregorianCalendar();
        Calendar dataEtc = new GregorianCalendar();
        Calendar dataEnd = new GregorianCalendar();

        File f = new File("/Users/gabriel/Documents/UC/2 ano/1 semestre/POO/Teste.txt");


        String line;
        String [] tokens;
        String del = "/";
        String sep = "-";

        if(f.exists() && f.isFile()){
            try{
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                if((line = br.readLine()) != null){
                    tokens = line.split(del);

                    if(tokens[0].equals("CENTRO") && tokens.length == 2){//se ler um centro do ficheiro faz isto
                        //System.out.printf("CENTRO\nNome do centro : %s.\n\n", tokens[1]);
                        researchCenter = new ResearchCenter(tokens[1]);
                        //continuar a ler para retirar as informacoes do centro

                        line = br.readLine();
                        tokens = line.split(del);

                        //le todas as pessoas pretencentes ao centro
                        while(!tokens[0].equals("PROJETO")) {


                            if (tokens[0].equals("DOCENTE")) {
                                //System.out.printf("DOCENTE\nNome: %s\nEmail: %s\nMecanografico: %s\nArea de investigacao: %s\n\n", tokens[1], tokens[2], tokens[3], tokens[4]);
                                pessoa = new Docente(tokens[1], tokens[2], tokens[3], tokens[4]);
                                researchCenter.addPessoa(pessoa);
                            } else if (tokens[0].equals("DOUTORADO")) {
                                //System.out.printf("DOUTORADO\nNome: %s\nEmail: %s\nInicio de bolsa: %s\nFim de bolsa: %s\n\n", tokens[1], tokens[2], tokens[3], tokens[4]);
                                dataStart = readDay(tokens[3], sep);
                                dataEtc = readDay(tokens[4], sep);

                                pessoa = new Doutorado(tokens[1], tokens[2], dataStart, dataEtc);
                                researchCenter.addPessoa(pessoa);
                            } else if (tokens[0].equals("MESTRE")) {
                                //System.out.printf("MESTRE\nNome: %s\nEmail: %s\nInicio de bolsa: %s\nFim de bolsa: %s\n\n", tokens[1], tokens[2], tokens[3], tokens[4]);
                                dataStart = readDay(tokens[3], sep);
                                dataEtc = readDay(tokens[4], sep);

                                pessoa = new Mestre(tokens[1], tokens[2], dataStart, dataEtc);
                                researchCenter.addPessoa(pessoa);
                            } else if (tokens[0].equals("LICENCIADO")) {
                                //System.out.printf("LICENCIADO\nNome: %s\nEmail: %s\nInicio de bolsa: %s\nFim de bolsa: %s\n\n", tokens[1], tokens[2], tokens[3], tokens[4]);
                                dataStart = readDay(tokens[3], sep);
                                dataEtc = readDay(tokens[4], sep);

                                pessoa = new Licenciado(tokens[1], tokens[2], dataStart, dataEtc);
                                researchCenter.addPessoa(pessoa);

                            } else {
                                System.out.printf("Erro a ler as pessoas do Centro.\n");
                            }
                            line = br.readLine();
                            tokens = line.split(del);
                        }
                        //le todos os projetos
                        while(tokens[0].equals("PROJETO")){
                            //System.out.printf("PROJETO\nNome: %s\nAcronimo: %s\nData de inicio: %s\nData estimada para conclusao: %s\n\n", tokens[1], tokens[2], tokens[3], tokens[4]);
                            dataStart = readDay(tokens[3], sep);
                            dataEtc = readDay(tokens[4], sep);


                            project = new Project(tokens[1], tokens[2], dataStart, dataEtc, (Docente)researchCenter.getPessoaByName(tokens[5]));
                            project.setDataFim(dataEnd);

                            if(tokens.length == 7){
                                dataEnd = readDay(tokens[6], sep);
                                project.setDataFim(dataEnd);
                                project.setAcabado(true);
                            }
                            researchCenter.addProject(project);

                            //le todas as pessoas pertencentes ao projeto
                            line = br.readLine();
                            tokens = line.split(del);

                            while(!tokens[0].equals("DESIGN") && !tokens[0].equals("DOCUMENTATION") && !tokens[0].equals("DEVELOPMENT")) {

                                if (tokens[0].equals("DOCENTE")) {
                                    //System.out.printf("---> DOCENTE\nNome: %s\n\n", tokens[1]);
                                    project.addDocente((Docente)researchCenter.getPessoaByName(tokens[1]));
                                }
                                else if(tokens[0].equals("PRINCIPAL")){
                                    //System.out.printf("PRINCIPAL\nNome: %s\n\n", tokens[1]);
                                    project.setPrincipal((Docente) researchCenter.getPessoaByName(tokens[1]));
                                    project.addDocente((Docente) researchCenter.getPessoaByName(tokens[1]));
                                }
                                else if (tokens[0].equals("BOLSEIRO")) {
                                    if(tokens.length == 2) {
                                        //System.out.printf("---> BOLSEIRO\nNome: %s\n\n", tokens[1]);
                                        project.addBolseiro((Bolseiro) researchCenter.getPessoaByName(tokens[1]));
                                    }
                                    else {
                                        //System.out.printf("---> BOLSEIRO\nNome: %s\nOrientador: %s\n\n", tokens[1], tokens[2]);
                                        estudante = (Estudante) researchCenter.getPessoaByName(tokens[1]);
                                        docente = (Docente) researchCenter.getPessoaByName(tokens[2]);
                                        estudante.setOrientador(docente);
                                        docente.addOrientado(estudante);
                                        project.addBolseiro(estudante);
                                    }
                                }
                                else {
                                    System.out.printf("Erro a ler as pessoas do Projeto.\n");
                                }
                                line = br.readLine();
                                tokens = line.split(del);
                            }

                            //le todas as tasks do projeto
                            while((tokens[0].equals("DESIGN") || tokens[0].equals("DOCUMENTATION") || tokens[0].equals("DEVELOPMENT")) && line != null){

                                dataStart = readDay(tokens[1], sep);
                                dataEtc = readDay(tokens[2], sep);
                                dataEnd = (Calendar) dataEtc.clone();
                                dataEnd.add(Calendar.DAY_OF_MONTH, Integer.parseInt(tokens[4]));

                                if(dataEnd.before(dataStart)){//se a data de fim for antes da de inicio a tarefa e considerada como por acabar
                                    dataEnd = null;
                                }

                                if(tokens[0].equals("DESIGN")){

                                    task = new Design(dataStart, dataEtc);

                                    if(dataEnd == null){//se a data for invalida
                                        if(Integer.parseInt(tokens[3]) == 100) {//e a taxa de conclusao for 100, considero erro, coloca a taxa a a 0
                                            task.setPercentage(0);
                                        }
                                        else{//e a taxa de conclusão for != 100, significa que a task nao foi concluida e aceito a taxa de conclusao
                                            task.setPercentage(Integer.parseInt(tokens[3]));
                                        }
                                    }
                                    else{
                                        task.setPercentage(100);
                                    }
                                    task.setFim(dataEnd);
                                    if(tokens.length == 6) {
                                        //System.out.printf(":::::> DESIGN\nData de inicio: %s\nData estimada para concluir: %s\n\n", tokens[1], tokens[2]);
                                        pessoa = researchCenter.getPessoaByName(tokens[5]);
                                        pessoa.addTask(task);//adiciona a task as tasks da pessoa e da set do responsavel da tarefa

                                    }
                                    project.addTask(task);

                                }
                                else if(tokens[0].equals("DOCUMENTATION")){

                                    task = new Documentation(dataStart, dataEtc);
                                    if(dataEnd == null){
                                        task.setPercentage(0);
                                    }
                                    else {
                                        task.setPercentage(Integer.parseInt(tokens[3]));
                                    }
                                    task.setFim(dataEnd);
                                    if(tokens.length == 6) {
                                        //System.out.printf(":::::> DOCUMENTATION\nData de inicio: %s\nData estimada para concluir: %s\n\n", tokens[1], tokens[2]);
                                        pessoa = researchCenter.getPessoaByName(tokens[5]);
                                        pessoa.addTask(task);
                                    }
                                    project.addTask(task);
                                }
                                else if(tokens[0].equals("DEVELOPMENT")){

                                    task = new Development(dataStart, dataEtc);
                                    if(dataEnd == null){
                                        task.setPercentage(0);
                                    }
                                    else {
                                        task.setPercentage(Integer.parseInt(tokens[3]));
                                    }
                                    task.setFim(dataEnd);
                                    if(tokens.length == 6) {
                                        //System.out.printf(":::::> DEVELOPMENT\nData de inicio: %s\nData estimada para concluir: %s\n\n", tokens[1], tokens[2]);
                                        pessoa = researchCenter.getPessoaByName(tokens[5]);
                                        pessoa.addTask(task);
                                    }
                                    project.addTask(task);

                                }
                                else{
                                    System.out.printf("Erro a ler as tasks do projeto.\n");
                                }

                                if((line = br.readLine()) != null)
                                    tokens = line.split(del);
                            }

                        }
                    }

                }

            }
            catch (FileNotFoundException e){
                System.out.printf("O ficheiro nao foi encontrado.\n");
            }
            catch (IOException e){
                System.out.printf("Erro ao ler o ficheiro.\n");
            }
            catch (ClassCastException e){
                System.out.printf("Erro a dar cast de um objeto para outra classe.\n");
            }
            catch (NullPointerException e){
                System.out.printf("Null pointer exception.\n");
            }



        }

        return researchCenter;

    }



    public static Calendar readDay(String token, String sep){
        Calendar d1 = new GregorianCalendar();
        String [] subtokens;

        try {
            subtokens = token.split(sep);
            d1.set(Calendar.YEAR, Integer.parseInt(subtokens[0]));
            d1.set(Calendar.MONTH, Integer.parseInt(subtokens[1]) - 1);
            d1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(subtokens[2]));
            return d1;
        }
        catch(NumberFormatException e){
            return null;
        }

    }


}
