package com.example.app_covid_19;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Estatisticas {
    private BdCovidOpenHelper openHelper;
    private SQLiteDatabase db;
    float mediaTemperatura;
    int totalPerfis;
    int totalTestes;
    int totalTestesPositivos;
    int totalTestesNegativos;
    int totalTestesInconclusivos;

    public Estatisticas(Context context){
        openHelper = new BdCovidOpenHelper(context);
        db = openHelper.getReadableDatabase();

        totalPerfis();
        totalTestes();
        totalTestesPositivo();
        totalTestesNegativo();
        totalTestesInconclusivo();
        mediaTemperaturas();
    }

    private void mediaTemperaturas(){
        Cursor cursor = db.rawQuery( "SELECT AVG(" + BdTabelaRegistos.TEMPERATURA + ") " +
                " FROM " + BdTabelaRegistos.NOME_TABELA + ", " + BdTabelaTestes.NOME_TABELA, null);

        cursor.moveToNext();
        mediaTemperatura = cursor.getInt(0);
    }

    private void totalPerfis(){
        Cursor cursor = db.rawQuery("SELECT COUNT(*) " +
                " FROM " + BdTabelaPerfis.NOME_TABELA,null);

        cursor.moveToNext();
        totalPerfis = cursor.getInt(0);
    }

    private void totalTestes(){
        Cursor cursor = db.rawQuery("SELECT COUNT(*) " +
                " FROM " + BdTabelaTestes.NOME_TABELA,null);

        cursor.moveToNext();
        totalTestes = cursor.getInt(0);
    }

    private void totalTestesPositivo(){
        Cursor cursor = db.rawQuery( "SELECT COUNT(*) " +
                " FROM " + BdTabelaTestes.NOME_TABELA + ", " + BdTabelaPerfis.NOME_TABELA +
                " WHERE " + BdTabelaTestes.CAMPO_ID_PERFIL_COMPLETO + " = " + BdTabelaPerfis.CAMPO_ID_COMPLETO +
                " AND " + BdTabelaTestes.RESULTADO_TESTE + " = 'Positivo'",null);

        cursor.moveToNext();
        totalTestesPositivos = cursor.getInt(0);
    }

    private void totalTestesNegativo(){
        Cursor cursor = db.rawQuery( "SELECT COUNT(*) " +
                " FROM " + BdTabelaTestes.NOME_TABELA + ", " + BdTabelaPerfis.NOME_TABELA +
                " WHERE " + BdTabelaTestes.CAMPO_ID_PERFIL_COMPLETO + " = " + BdTabelaPerfis.CAMPO_ID_COMPLETO +
                " AND " + BdTabelaTestes.RESULTADO_TESTE + " = 'Negativo'",null);

        cursor.moveToNext();
        totalTestesNegativos = cursor.getInt(0);
    }

    private void totalTestesInconclusivo(){
        Cursor cursor = db.rawQuery( "SELECT COUNT(*) " +
                " FROM " + BdTabelaTestes.NOME_TABELA + ", " + BdTabelaPerfis.NOME_TABELA +
                " WHERE " + BdTabelaTestes.CAMPO_ID_PERFIL_COMPLETO + " = " + BdTabelaPerfis.CAMPO_ID_COMPLETO +
                " AND " + BdTabelaTestes.RESULTADO_TESTE + " = 'Inconclusivo'",null);

        cursor.moveToNext();
        totalTestesInconclusivos = cursor.getInt(0);
    }

    public float getMediaTemperatura() {
        return mediaTemperatura;
    }

    public int getTotalPessoas() {
        return totalPerfis;
    }

    public int getTotalTestes() {
        return totalTestes;
    }

    public int getTotalTestesPositivos() {
        return totalTestesPositivos;
    }

    public int getTotalTestesNegativos() {
        return totalTestesNegativos;
    }

    public int getTotalTestesInconclusivos() {
        return totalTestesInconclusivos;
    }
}
