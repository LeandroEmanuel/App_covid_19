package com.example.app_covid_19;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.sql.Date;

public class BdTabelaTestes implements BaseColumns {

    public static final String _ID = "id";
    public static final String ID_PERFIL = "id_perfil";
    public static final Date DATA_TESTE = Date.valueOf("data_teste");
    public static final String RESULTADO_TESTE = "resultado_teste";
    public static final String NOME_TABELA = "testes";
    private SQLiteDatabase db;

    public BdTabelaTestes(SQLiteDatabase db){
        this.db = db;
    }

    public void criaTabelaTestes(){
        db.execSQL("CREATE TABLE " + NOME_TABELA + "(" +
                _ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                DATA_TESTE + " DATE NOT NULL," +
                RESULTADO_TESTE + " TEXT NOT NULL," +
                ID_PERFIL + "INTEGER NOT NULL," +
                "FOREIGN KEY(" + ID_PERFIL + ") REFERENCES " +
                BdTabelaPerfis.NOME_TABELA +"("+ BdTabelaPerfis._ID + ")" +
                ")");
    }
}
