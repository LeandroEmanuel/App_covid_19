package com.example.app_covid_19;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaPerfis implements BaseColumns {
    public static final String NOME_TABELA = "perfis";
    public static final String NOME = "nome";
    public static final String DATA_NASCIMENTO = "data_nascimento";
    public static final String CARDIO = "cardio";
    public static final String DIABETES = "diabetes";
    public static final String HIPER = "hiper";
    public static final String ONCO = "onco";
    public static final String RESP = "resp";
    private SQLiteDatabase db;

    public static final String CAMPO_ID_COMPLETO = NOME_TABELA + "." + _ID;
    public static final String CAMPO_NOME_COMPLETO = NOME_TABELA + "." + NOME;
    public static final String CAMPO_DATANASCIMENTO_COMPLETO = NOME_TABELA + "." + DATA_NASCIMENTO;
    public static final String CAMPO_CARDIO_COMPLETO = NOME_TABELA + "." + CARDIO;
    public static final String CAMPO_DIABETES_COMPLETO = NOME_TABELA + "." + DIABETES;
    public static final String CAMPO_HIPER_COMPLETO = NOME_TABELA + "." + HIPER;
    public static final String CAMPO_ONCO_COMPLETO = NOME_TABELA + "." + ONCO;
    public static final String CAMPO_RESP_COMPLETO = NOME_TABELA + "." + RESP;
    public static final String[] TODOS_OS_CAMPOS = new String[]{CAMPO_ID_COMPLETO, CAMPO_NOME_COMPLETO, CAMPO_DATANASCIMENTO_COMPLETO,
            CAMPO_CARDIO_COMPLETO, CAMPO_DIABETES_COMPLETO, CAMPO_HIPER_COMPLETO, CAMPO_ONCO_COMPLETO, CAMPO_RESP_COMPLETO};

    public BdTabelaPerfis(SQLiteDatabase db){
        this.db = db;
    }

    public void criaTabelaPerfis(){
        db.execSQL(("CREATE TABLE " + NOME_TABELA + " (" +
        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                " " + NOME + " TEXT NOT NULL," +
                DATA_NASCIMENTO + " TEXT NOT NULL, " +
                CARDIO + " INTEGER, " +
                DIABETES + " INTEGER, " +
                HIPER + " INTEGER, " +
                ONCO + " INTEGER, " +
                RESP + " INTEGER " +
                ")"));
    }

    public long insert(ContentValues values) {
        return db.insert(NOME_TABELA, null, values);
    }
    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy){
        return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
    public int update(ContentValues values, String whereClause, String[] whereArgs){
        return db.update(NOME_TABELA,values,whereClause,whereArgs);
    }
    public int delete(String whereClause, String[] whereArgs){
        return db.delete(NOME_TABELA,whereClause,whereArgs);
    }
}
