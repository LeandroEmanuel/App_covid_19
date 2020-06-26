package com.example.app_covid_19;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Estatisticas {
    private BdCovidOpenHelper openHelper;
    private SQLiteDatabase db;
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
                " AND " + BdTabelaTestes.RESULTADO_TESTE + " = 1",null);

        cursor.moveToNext();
        totalTestesPositivos = cursor.getInt(0);
    }

    private void totalTestesNegativo(){
        Cursor cursor = db.rawQuery( "SELECT COUNT(*) " +
                " FROM " + BdTabelaTestes.NOME_TABELA + ", " + BdTabelaPerfis.NOME_TABELA +
                " WHERE " + BdTabelaTestes.CAMPO_ID_PERFIL_COMPLETO + " = " + BdTabelaPerfis.CAMPO_ID_COMPLETO +
                " AND " + BdTabelaTestes.RESULTADO_TESTE + " = 2",null);

        cursor.moveToNext();
        totalTestesNegativos = cursor.getInt(0);
    }

    private void totalTestesInconclusivo(){
        Cursor cursor = db.rawQuery( "SELECT * " +
                " FROM " + BdTabelaTestes.NOME_TABELA + ", " + BdTabelaPerfis.NOME_TABELA +
                " WHERE " + BdTabelaTestes.CAMPO_ID_PERFIL_COMPLETO + " = " + BdTabelaPerfis.CAMPO_ID_COMPLETO +
                " AND " + BdTabelaTestes.CAMPO_RESULTADO_TESTE_COMPLETO + " = 3",null);

        cursor.moveToNext();
        totalTestesInconclusivos = cursor.getInt(0);
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
