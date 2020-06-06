package com.example.app_covid_19;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Date;

public class Converte {

    public static ContentValues perfilParaContentValues(Perfil perfil){
        ContentValues valores = new ContentValues();
        valores.put(BdTabelaPerfis.NOME, perfil.getNome());
        return valores;
    }
    public static Perfil contentValuesParaPerfil(ContentValues valores){
        Perfil perfil = new Perfil();

        perfil.setId(valores.getAsLong(BdTabelaPerfis._ID));
        perfil.setNome(valores.getAsString(BdTabelaPerfis.NOME));
        perfil.setDataNascimento(valores.getAsString(String.valueOf(
                BdTabelaPerfis.DATA_NASCIMENTO)));
        return perfil;
    }
    public static ContentValues registoParaContentValues(Registo registo){
        ContentValues valores = new ContentValues();
        valores.put(String.valueOf(BdTabelaRegistos.DATA_REGISTO), registo.getDataRegisto());
        valores.put(String.valueOf(BdTabelaRegistos.TEMPERATURA), registo.getTemperatura());
        valores.put(BdTabelaRegistos.SINTOMAS, registo.getSintomas());
        return valores;
    }
    public static Registo contentValuesParaRegisto(ContentValues valores){
        Registo registo = new Registo();
        registo.setId(valores.getAsLong(BdTabelaRegistos._ID));
        registo.setIdPerfil(valores.getAsLong(BdTabelaRegistos.ID_PERFIL));
        registo.setDataRegisto(valores.getAsString(String.valueOf(BdTabelaRegistos.DATA_REGISTO)));
        registo.setTemperatura(valores.getAsFloat(String.valueOf(BdTabelaRegistos.TEMPERATURA)));
        registo.setSintomas(valores.getAsString(BdTabelaRegistos.SINTOMAS));
        return registo;
    }
    public static Registo cursorParaRegisto(Cursor cursor){
        Registo registo = new Registo();
        registo.setId(cursor.getLong(cursor.getColumnIndex(BdTabelaRegistos._ID)));
        registo.setIdPerfil(cursor.getLong(cursor.getColumnIndex(BdTabelaRegistos.ID_PERFIL)));
        registo.setDataRegisto(cursor.getString(cursor.getColumnIndex(String.valueOf(
                BdTabelaRegistos.DATA_REGISTO))));
        registo.setTemperatura(cursor.getFloat(cursor.getColumnIndex(String.valueOf(
                BdTabelaRegistos.TEMPERATURA))));
        registo.setSintomas(cursor.getString(cursor.getColumnIndex(BdTabelaRegistos.SINTOMAS)));
        return registo;
    }

    public static ContentValues testeParaContentValues(Teste teste){
        ContentValues valores = new ContentValues();
        valores.put(String.valueOf(BdTabelaTestes.DATA_TESTE), teste.getDataTeste());
        valores.put(BdTabelaTestes.RESULTADO_TESTE, teste.getResultadoTeste());
        return valores;
    }
    public static Teste contentValuesParaTeste(ContentValues valores){
        Teste teste = new Teste();
        teste.setId(valores.getAsLong(BdTabelaTestes._ID));
        teste.setIdPerfil(valores.getAsLong(BdTabelaTestes.ID_PERFIL));
        teste.setDataTeste(valores.getAsString(String.valueOf(BdTabelaTestes.DATA_TESTE)));
        teste.setResultadoTeste(valores.getAsString(BdTabelaTestes.RESULTADO_TESTE));
        return teste;
    }

    public static Teste cursorParaTeste(Cursor cursor){
        Teste teste = new Teste();
        teste.setId(cursor.getLong(cursor.getColumnIndex(BdTabelaTestes._ID)));
        teste.setIdPerfil(cursor.getLong(cursor.getColumnIndex(BdTabelaTestes.ID_PERFIL)));
        teste.setDataTeste(cursor.getString(cursor.getColumnIndex(String.valueOf(
                BdTabelaTestes.DATA_TESTE))));
        teste.setResultadoTeste(String.valueOf(cursor.getColumnIndex(
                BdTabelaTestes.RESULTADO_TESTE)));
        return teste;
    }

}