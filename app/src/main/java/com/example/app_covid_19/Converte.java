package com.example.app_covid_19;

import android.content.ContentValues;
import android.database.Cursor;

public class Converte {

    public static ContentValues perfilParaContentValues(Perfil perfil){
        ContentValues values = new ContentValues();
        values.put(BdTabelaPerfis.NOME, perfil.getNome());
        values.put(BdTabelaPerfis.DATA_NASCIMENTO, perfil.getDataNascimento());
        return values;
    }
    public static Perfil contentValuesParaPerfil(ContentValues values){
        Perfil perfil = new Perfil();

        perfil.setId(values.getAsLong(BdTabelaPerfis._ID));
        perfil.setNome(values.getAsString(BdTabelaPerfis.NOME));
        perfil.setDataNascimento(values.getAsString(String.valueOf(
                BdTabelaPerfis.DATA_NASCIMENTO)));
        return perfil;
    }
    public static Perfil cursorParaPerfil(Cursor cursor){
        Perfil perfil = new Perfil();

        perfil.setId(cursor.getLong(cursor.getColumnIndex(BdTabelaPerfis._ID)));
        perfil.setNome(cursor.getString(cursor.getColumnIndex(BdTabelaPerfis.NOME)));
        perfil.setDataNascimento(cursor.getString(cursor.getColumnIndex(BdTabelaPerfis.DATA_NASCIMENTO)));

        return perfil;
    }

    public static ContentValues registoParaContentValues(Registo registo){
        ContentValues values = new ContentValues();
        values.put(BdTabelaRegistos.DATA_REGISTO, registo.getDataRegisto());
        values.put(BdTabelaRegistos.TEMPERATURA, registo.getTemperatura());
        values.put(BdTabelaRegistos.SINTOMAS, registo.getSintomas());
        values.put(BdTabelaRegistos.CAMPO_ID_PERFIL, registo.getIdPerfil());
        return values;
    }
    public static Registo contentValuesParaRegisto(ContentValues values){
        Registo registo = new Registo();

        registo.setId(values.getAsLong(BdTabelaRegistos._ID));
        registo.setDataRegisto(values.getAsString(BdTabelaRegistos.DATA_REGISTO));
        registo.setTemperatura(values.getAsFloat(BdTabelaRegistos.TEMPERATURA));
        registo.setSintomas(values.getAsString(BdTabelaRegistos.SINTOMAS));
        registo.setIdPerfil(values.getAsLong(BdTabelaRegistos.CAMPO_ID_PERFIL));


        return registo;
    }
    public static Registo cursorParaRegisto(Cursor cursor){
        Registo registo = new Registo();
        registo.setId(cursor.getLong(cursor.getColumnIndex(BdTabelaRegistos._ID)));
        registo.setIdPerfil(cursor.getLong(cursor.getColumnIndex(BdTabelaRegistos.CAMPO_ID_PERFIL)));
        registo.setDataRegisto(cursor.getString(cursor.getColumnIndex(String.valueOf(
                BdTabelaRegistos.DATA_REGISTO))));
        registo.setTemperatura(cursor.getFloat(cursor.getColumnIndex(BdTabelaRegistos.TEMPERATURA)));
        registo.setSintomas(cursor.getString(cursor.getColumnIndex(BdTabelaRegistos.SINTOMAS)));
        registo.setIdPerfil(cursor.getLong(cursor.getColumnIndex(BdTabelaRegistos.CAMPO_ID_PERFIL)));
        registo.setPerfil(cursor.getString(cursor.getColumnIndex(BdTabelaRegistos.CAMPO_PERFIL)));

        return registo;
    }

    public static ContentValues testeParaContentValues(Teste teste){
        ContentValues values = new ContentValues();

        values.put(BdTabelaTestes.DATA_TESTE, teste.getDataTeste());
        values.put(BdTabelaTestes.RESULTADO_TESTE, teste.getResultadoTeste());
        values.put(BdTabelaTestes.CAMPO_ID_PERFIL, teste.getIdPerfil());

        return values;
    }
    public static Teste contentValuesParaTeste(ContentValues values){
        Teste teste = new Teste();
        teste.setId(values.getAsLong(BdTabelaTestes._ID));
        teste.setDataTeste(values.getAsString(BdTabelaTestes.DATA_TESTE));
        teste.setResultadoTeste(values.getAsString(BdTabelaTestes.RESULTADO_TESTE));
        teste.setIdPerfil(values.getAsLong(BdTabelaTestes.CAMPO_ID_PERFIL));
        return teste;
    }

    public static Teste cursorParaTeste(Cursor cursor){
        Teste teste = new Teste();
        teste.setId(cursor.getLong(cursor.getColumnIndex(BdTabelaTestes._ID)));
        teste.setIdPerfil(cursor.getLong(cursor.getColumnIndex(BdTabelaTestes.CAMPO_ID_PERFIL)));
        teste.setDataTeste(cursor.getString(cursor.getColumnIndex(String.valueOf(
                BdTabelaTestes.DATA_TESTE))));
        teste.setResultadoTeste(cursor.getString(cursor.getColumnIndex(BdTabelaTestes.RESULTADO_TESTE)));
        teste.setPerfil(cursor.getString(cursor.getColumnIndex(BdTabelaTestes.CAMPO_PERFIL)));
        return teste;
    }

}