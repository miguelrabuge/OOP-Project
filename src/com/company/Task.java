package com.company;

import java.util.Calendar;

abstract class Task {
    protected Calendar inicio;
    protected Calendar fim;
    protected Calendar etc;
    protected int percentage;
    protected Project projeto;
    protected Pessoa responsavel;

    public Task(Calendar inicio, Calendar fim, Calendar etc, Pessoa responsavel){
        this.inicio = inicio;
        this.fim = fim;
        this.etc = etc;
        this.percentage = 0;
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

    public Calendar getEtc() {
        return this.etc;
    }

    public void setEtc(Calendar etc) {
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
