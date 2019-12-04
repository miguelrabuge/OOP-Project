package com.company;

import java.util.Calendar;

abstract class Task {
    protected Calendar inicio;
    protected Calendar fim;
    protected int etc;
    protected int percentage = 0; //achei por bem inicializa-la a 0
    protected Project projeto;
    protected Pessoa responsavel;

    public Task(Calendar inicio, Calendar fim, int etc, Pessoa responsavel){
        this.inicio = inicio;
        this.fim = fim;
        this.etc = etc;
        this.responsavel = responsavel;
    }

    public Calendar getInicio() {
        return this.inicio;
    }

    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }

    public Calendar getFim() {
        return this.fim;
    }

    public void setFim(Calendar fim) {
        this.fim = fim;
    }

    public int getEtc() {
        return this.etc;
    }

    public void setEtc(int etc) {
        this.etc = etc;
    }

    public int getPercentage() {
        return this.percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Project getProjeto() {
        return this.projeto;
    }

    public void setProjeto(Project projeto) {
        this.projeto = projeto;
    }

    public Pessoa getResponsavel() {
        return this.responsavel;
    }

    public void setResponsavel(Pessoa responsavel) {
        this.responsavel = responsavel;
    }

    public abstract double  getEsforco();
}
