package com.example.app_covid_19;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.sql.Date;

public class BdTabelaRegistos implements BaseColumns {

    public static final String _ID = "id";
    public static final String ID_PERFIL = "id_perfil";
    public static final String NOME_TABELA = "registos";
    public static final Date DATA_REGISTO = Date.valueOf("data_registo");
    public static final float TEMPERATURA = Float.parseFloat("temperatura");
    public static final String SINTOMAS = "sintomas";
    private SQLiteDatabase db;

    public BdTabelaRegistos(SQLiteDatabase db){
        this.db = db;
    }

    public void criarTabelaRegistos(){
        db.execSQL("CREATE TABLE " + NOME_TABELA + "(" +
                _ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                DATA_REGISTO + " DATE NOT NULL," +
                TEMPERATURA + " REAL NOT NULL," +
                SINTOMAS + " TEXT" +
                ID_PERFIL + "INTEGER NOT NULL," +
                "FOREIGN KEY(" + ID_PERFIL + ") REFERENCES " +
                BdTabelaPerfis.NOME_TABELA +"("+ BdTabelaPerfis._ID + ")" +
                ")");
    }
}
