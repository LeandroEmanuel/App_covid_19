package com.example.app_covid_19;

import java.sql.Date;

public class Registo {
    private long id;
    private long idPerfil = -1;
    private String dataRegisto;
    private float Temperatura = 0.0f;
    private int tosse;
    private int difResp;

    public String Perfil;

    public String getPerfil() {
        return Perfil;
    }

    public void setPerfil(String perfil) {
        Perfil = perfil;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getDataRegisto() {
        return dataRegisto;
    }

    public void setDataRegisto(String dataRegisto) {
        this.dataRegisto = dataRegisto;
    }

    public float getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(float temperatura) {
        Temperatura = temperatura;
    }

    public int getTosse() {
        return tosse;
    }

    public void setTosse(int tosse) {
        this.tosse = tosse;
    }

    public int getDifResp() {
        return difResp;
    }

    public void setDifResp(int difResp) {
        this.difResp = difResp;
    }
}
