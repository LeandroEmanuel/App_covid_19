package com.example.app_covid_19;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaTestes implements BaseColumns {

    public static final String _ID = "id";
    public static final String CAMPO_ID_PERFIL = "id_perfil";
    public static final String DATA_TESTE = "data_teste";
    public static final String RESULTADO_TESTE = "resultado_teste";
    public static final String NOME_TABELA = "testes";
    private SQLiteDatabase db;

    public BdTabelaTestes(SQLiteDatabase db){
        this.db = db;
    }

    public void criaTabelaTestes(){
        db.execSQL("CREATE TABLE " + NOME_TABELA + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DATA_TESTE + " TEXT NOT NULL," +
                RESULTADO_TESTE + " TEXT NOT NULL," +
                CAMPO_ID_PERFIL + " INTEGER NOT NULL," +
                " FOREIGN KEY(" + CAMPO_ID_PERFIL + ") REFERENCES " +
                BdTabelaPerfis.NOME_TABELA +"("+ BdTabelaPerfis._ID + ")" +
                ")");
    }

    public long insert(ContentValues values) {
        return db.insert(NOME_TABELA,null, values);
    }
}
