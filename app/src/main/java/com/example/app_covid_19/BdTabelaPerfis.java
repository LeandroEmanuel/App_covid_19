package com.example.app_covid_19;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaPerfis implements BaseColumns {
    public static final String NOME_TABELA = "perfis";
    public static final String NOME = "nome";
    public static final String DATA_NASCIMENTO = "data_nascimento";
    private SQLiteDatabase db;

    public BdTabelaPerfis(SQLiteDatabase db){
        this.db = db;
    }

    public void criaTabelaPerfis(){
        db.execSQL(("CREATE TABLE " + NOME_TABELA + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                " " + NOME + " TEXT NOT NULL," +
                DATA_NASCIMENTO + " DATE NOT NULL" +
                ")"));
    }
}
