package com.example.app_covid_19;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaRegistos implements BaseColumns {

    public static final String _ID = "id";
    public static final String NOME_TABELA = "registos";
    public static final String DATA_REGISTO = "data_registo";
    public static final String TEMPERATURA = "temperatura";
    public static final String SINTOMAS = "sintomas";
    public static final String CAMPO_ID_PERFIL = "id_perfil";


    public static final String CAMPO_ID_COMPLETO = NOME_TABELA +"."+ _ID;
    public static final String CAMPO_DATA_REGISTO_COMPLETO = NOME_TABELA + "." + DATA_REGISTO;
    public static final String CAMPO_TEMPERATURA_COMPLETO = NOME_TABELA + "." + TEMPERATURA;
    public static final String CAMPO_SINTOMAS_COMPLETO = NOME_TABELA + "." + SINTOMAS;
    public static final String CAMPO_ID_PERFIL_COMPLETO = NOME_TABELA + "." + CAMPO_ID_PERFIL;

    public static final String CAMPO_PERFIL = "perfil";

    public static final String CAMPO_PERFIL_COMPLETO = BdTabelaPerfis.NOME_TABELA + "." + BdTabelaPerfis.NOME + " AS " + CAMPO_ID_PERFIL_COMPLETO;

    public static final String[] TODOS_OS_CAMPOS = new String[]{CAMPO_ID_COMPLETO,CAMPO_DATA_REGISTO_COMPLETO,
            CAMPO_TEMPERATURA_COMPLETO, CAMPO_SINTOMAS_COMPLETO, CAMPO_ID_PERFIL_COMPLETO/*,CAMPO_PERFIL_COMPLETO*/};
    private SQLiteDatabase db;

    public BdTabelaRegistos(SQLiteDatabase db){
        this.db = db;
    }

    public void criarTabelaRegistos(){
        db.execSQL("CREATE TABLE " + NOME_TABELA + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DATA_REGISTO + " TEXT NOT NULL, " +
                TEMPERATURA + " REAL NOT NULL, " +
                SINTOMAS + " TEXT," +
                CAMPO_ID_PERFIL + " INTEGER NOT NULL," +
                " FOREIGN KEY(" + CAMPO_ID_PERFIL + ") REFERENCES " +
                BdTabelaPerfis.NOME_TABELA +"("+ BdTabelaPerfis._ID + ")" +
                ")");
    }

    public long insert(ContentValues values) {
        return db.insert(NOME_TABELA, null, values);
    }

    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public int update(ContentValues values, String whereClause, String[] whereArgs){
        return db.update(NOME_TABELA,values,whereClause,whereArgs);
    }
    public int delete(String whereClause, String[] whereArgs){
        return db.delete(NOME_TABELA,whereClause,whereArgs);
    }
}
