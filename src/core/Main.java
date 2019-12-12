package core;

import UI.IntroUI;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        ArrayList<ResearchCenter> researchCenters = new ArrayList<>();
        ResearchCenter researchCenterFileBooter;
        String backupBooter = "/Users/gabriel/Documents/UC/2 ano/1 semestre/POO/Teste.txt";

        if (/*read from .obj == true*/ false) {
            //TODO: read from .obj
            System.out.println("Sucesso: Carregados Dados do Ficheiro.obj");
        } else if ((researchCenterFileBooter = loadFile(backupBooter)) != null) {
            System.out.println("Falhou: Carregar Dados do Ficheiro.obj");
            System.out.println("Sucesso: Carregado Centro do Ficheiro.txt");
            researchCenters.add(researchCenterFileBooter);
        } else {
            System.out.println("Falhou: Carregar Dados do Ficheiro.obj");
            System.out.println("Falhou: Carregar Centro do Ficheiro.txt");
        }
        new IntroUI(researchCenters);
    }

    private static ResearchCenter loadFile(String pathname) {
        ResearchCenter researchCenter = null;
        Pessoa pessoa;
        Docente docente;
        Estudante estudante;
        Project project;
        Task task;

        Calendar dataStart;
        Calendar dataEtc;
        Calendar dataEnd = new GregorianCalendar();

        File f = new File(pathname);

        String line;
        String[] tokens;
        String del = "/";
        String sep = "-";
        try {
            if (f.exists() && f.isFile()) {

                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                if ((line = br.readLine()) != null) {
                    tokens = line.split(del);

                    if (tokens[0].equals("CENTRO") && tokens.length == 2) {//se ler um centro do ficheiro faz isto
                        //System.out.printf("CENTRO\nNome do centro : %s.\n\n", tokens[1]);
                        researchCenter = new ResearchCenter(tokens[1]);
                        //continuar a ler para retirar as informacoes do centro

                        line = br.readLine();
                        tokens = line.split(del);

                        //le todas as pessoas pretencentes ao centro
                        while (!tokens[0].equals("PROJETO")) {
                            switch (tokens[0]) {
                                case "DOCENTE":
                                    //System.out.printf("DOCENTE\nNome: %s\nEmail: %s\nMecanografico: %s\nArea de investigacao: %s\n\n", tokens[1], tokens[2], tokens[3], tokens[4]);
                                    pessoa = new Docente(tokens[1], tokens[2], tokens[3], tokens[4]);
                                    researchCenter.addPessoa(pessoa);
                                    break;
                                case "DOUTORADO":
                                    //System.out.printf("DOUTORADO\nNome: %s\nEmail: %s\nInicio de bolsa: %s\nFim de bolsa: %s\n\n", tokens[1], tokens[2], tokens[3], tokens[4]);
                                    dataStart = readDay(tokens[3], sep);
                                    dataEtc = readDay(tokens[4], sep);

                                    pessoa = new Doutorado(tokens[1], tokens[2], dataStart, dataEtc);
                                    researchCenter.addPessoa(pessoa);
                                    break;
                                case "MESTRE":
                                    //System.out.printf("MESTRE\nNome: %s\nEmail: %s\nInicio de bolsa: %s\nFim de bolsa: %s\n\n", tokens[1], tokens[2], tokens[3], tokens[4]);
                                    dataStart = readDay(tokens[3], sep);
                                    dataEtc = readDay(tokens[4], sep);

                                    pessoa = new Mestre(tokens[1], tokens[2], dataStart, dataEtc);
                                    researchCenter.addPessoa(pessoa);
                                    break;
                                case "LICENCIADO":
                                    //System.out.printf("LICENCIADO\nNome: %s\nEmail: %s\nInicio de bolsa: %s\nFim de bolsa: %s\n\n", tokens[1], tokens[2], tokens[3], tokens[4]);
                                    dataStart = readDay(tokens[3], sep);
                                    dataEtc = readDay(tokens[4], sep);

                                    pessoa = new Licenciado(tokens[1], tokens[2], dataStart, dataEtc);
                                    researchCenter.addPessoa(pessoa);

                                    break;
                                default:
                                    System.out.println("Erro a ler as pessoas do Centro.\n");
                                    break;
                            }
                            line = br.readLine();
                            tokens = line.split(del);
                        }
                        //Lê todos os projetos
                        while (tokens[0].equals("PROJETO")) {
                            //System.out.printf("PROJETO\nNome: %s\nAcronimo: %s\nData de inicio: %s\nData estimada para conclusao: %s\n\n", tokens[1], tokens[2], tokens[3], tokens[4]);
                            dataStart = readDay(tokens[3], sep);
                            dataEtc = readDay(tokens[4], sep);

                            project = new Project(tokens[1], tokens[2], dataStart, dataEtc, (Docente) researchCenter.getPessoaByName(tokens[5]));
                            project.addDocente((Docente) researchCenter.getPessoaByName(tokens[5]));
                            project.setDataFim(dataEnd);

                            if (tokens.length == 7) { //Projeto concluído
                                dataEnd = readDay(tokens[6], sep);
                                project.setDataFim(dataEnd);
                                project.setAcabado(true);
                            }
                            researchCenter.addProject(project);

                            //Lê todas as pessoas pertencentes ao projeto
                            line = br.readLine();
                            tokens = line.split(del);

                            while (!tokens[0].equals("DESIGN") && !tokens[0].equals("DOCUMENTATION") && !tokens[0].equals("DEVELOPMENT")) {

                                switch (tokens[0]) {
                                    case "DOCENTE":
                                        //System.out.printf("---> DOCENTE\nNome: %s\n\n", tokens[1]);
                                        project.addDocente((Docente) researchCenter.getPessoaByName(tokens[1]));
                                        break;
                                    case "BOLSEIRO":
                                        if (tokens.length == 2) {
                                            //System.out.printf("---> BOLSEIRO\nNome: %s\n\n", tokens[1]);
                                            project.addBolseiro((Bolseiro) researchCenter.getPessoaByName(tokens[1]));
                                        } else {
                                            //System.out.printf("---> BOLSEIRO\nNome: %s\nOrientador: %s\n\n", tokens[1], tokens[2]);
                                            estudante = (Estudante) researchCenter.getPessoaByName(tokens[1]);
                                            docente = (Docente) researchCenter.getPessoaByName(tokens[2]);
                                            estudante.setOrientador(docente);
                                            docente.addOrientado(estudante);
                                            project.addBolseiro(estudante);
                                        }
                                        break;
                                    default:
                                        System.out.println("Erro a ler as pessoas do Projeto.\n");
                                        break;
                                }
                                line = br.readLine();
                                tokens = line.split(del);
                            }

                            //le todas as tasks do projeto
                            while ((tokens[0].equals("DESIGN") || tokens[0].equals("DOCUMENTATION") || tokens[0].equals("DEVELOPMENT")) && line != null) {
                                dataStart = readDay(tokens[1], sep);
                                dataEtc = readDay(tokens[2], sep);
                                dataEnd = (Calendar) dataEtc.clone();
                                dataEnd.add(Calendar.DAY_OF_MONTH, Integer.parseInt(tokens[4]));
                                if (dataEnd.before(dataStart)) {//Se a data de fim for antes da data de início a tarefa é considerada por acabar
                                    dataEnd = null;
                                }
                                switch (tokens[0]) {
                                    case "DESIGN":
                                        task = new Design(dataStart, dataEtc);
                                        if (dataEnd == null) {//Se a data for inválida
                                            if (Integer.parseInt(tokens[3]) == 100) {//E a taxa de conclusão for 100, considero erro, coloco a taxa a 0
                                                task.setPercentage(0);
                                            } else {//e a taxa de conclusão for != 100, significa que a task nao foi concluida e aceito a taxa de conclusão
                                                task.setPercentage(Integer.parseInt(tokens[3]));
                                            }
                                        } else {
                                            task.setPercentage(100);
                                        }
                                        task.setFim(dataEnd);
                                        if (tokens.length == 6) {
                                            //System.out.printf(":::::> DESIGN\nData de inicio: %s\nData estimada para concluir: %s\n\n", tokens[1], tokens[2]);
                                            pessoa = researchCenter.getPessoaByName(tokens[5]);
                                            pessoa.addTask(task);//adiciona a task as tasks da pessoa e da set do responsavel da tarefa

                                        }
                                        project.addTask(task);
                                        break;
                                    case "DOCUMENTATION":
                                        task = new Documentation(dataStart, dataEtc);
                                        if (dataEnd == null) {
                                            task.setPercentage(0);
                                        } else {
                                            task.setPercentage(Integer.parseInt(tokens[3]));
                                        }
                                        task.setFim(dataEnd);
                                        if (tokens.length == 6) {
                                            //System.out.printf(":::::> DOCUMENTATION\nData de inicio: %s\nData estimada para concluir: %s\n\n", tokens[1], tokens[2]);
                                            pessoa = researchCenter.getPessoaByName(tokens[5]);
                                            pessoa.addTask(task);
                                        }
                                        project.addTask(task);
                                        break;
                                    case "DEVELOPMENT":
                                        task = new Development(dataStart, dataEtc);
                                        if (dataEnd == null) {
                                            task.setPercentage(0);
                                        } else {
                                            task.setPercentage(Integer.parseInt(tokens[3]));
                                        }
                                        task.setFim(dataEnd);
                                        if (tokens.length == 6) {
                                            //System.out.printf(":::::> DEVELOPMENT\nData de inicio: %s\nData estimada para concluir: %s\n\n", tokens[1], tokens[2]);
                                            pessoa = researchCenter.getPessoaByName(tokens[5]);
                                            pessoa.addTask(task);
                                        }
                                        project.addTask(task);
                                        break;
                                    default:
                                        System.out.println("Erro a ler as tasks do projeto.\n");
                                        break;
                                }
                                if ((line = br.readLine()) != null) {
                                    tokens = line.split(del);
                                }
                            }
                        }
                    }
                }
            } else {
                System.out.println("Ficheiro não encontrado ou Não Tem permissão para o abrir");
            }
            return researchCenter;
        } catch (FileNotFoundException e) {
            System.out.println("O ficheiro nao foi encontrado.");
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro.");
        } catch (ClassCastException e) {
            System.out.println("Erro a dar cast de um objeto para outra classe");
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception.");
        }
        return null;
    }


    private static Calendar readDay(String token, String sep) {
        Calendar day = new GregorianCalendar();
        String[] subtokens;
        try {
            subtokens = token.split(sep);
            day.set(Calendar.YEAR, Integer.parseInt(subtokens[0]));
            day.set(Calendar.MONTH, Integer.parseInt(subtokens[1]) - 1);
            day.set(Calendar.DAY_OF_MONTH, Integer.parseInt(subtokens[2]));
            return day;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
