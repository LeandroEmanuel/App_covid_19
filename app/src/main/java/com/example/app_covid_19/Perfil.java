package com.example.app_covid_19;


import java.io.Serializable;

public class Perfil implements Serializable {
    private long id;

    private String nome;
    private String dataNascimento;
    private int cardio;
    private int diabetes;
    private int hiper;
    private int onco;
    private int resp;

    public long getId() {

        return id ;//= -1; // inicializado a -1 dá erro quando tento gravar
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getCardio() {
        return cardio;
    }

    public void setCardio(int cardio) {
        this.cardio = cardio;
    }

    public int getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(int diabetes) {
        this.diabetes = diabetes;
    }

    public int getHiper() {
        return hiper;
    }

    public void setHiper(int hiper) {
        this.hiper = hiper;
    }

    public int getOnco() {
        return onco;
    }

    public void setOnco(int onco) {
        this.onco = onco;
    }

    public int getResp() {
        return resp;
    }

    public void setResp(int resp) {
        this.resp = resp;
    }
}
