package com.example.app_covid_19;

import java.sql.Date;

public class Teste {
    private long id = -1;
    private long idPerfil = -1;
    private String dataTeste;
    private String resultadoTeste;

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

    public String getDataTeste() {
        return dataTeste;
    }

    public void setDataTeste(String dataTeste) {
        this.dataTeste = dataTeste;
    }

    public String getResultadoTeste() {
        return resultadoTeste;
    }

    public void setResultadoTeste(String resultadoTeste) {
        this.resultadoTeste = resultadoTeste;
    }
}