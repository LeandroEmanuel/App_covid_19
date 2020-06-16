package com.example.app_covid_19;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.text.TextUtils;

import java.util.Arrays;

public class BdTabelaTestes implements BaseColumns {

    public static final String _ID = "id";
    public static final String CAMPO_ID_PERFIL = "id_perfil";
    public static final String DATA_TESTE = "data_teste";
    public static final String RESULTADO_TESTE = "resultado_teste";
    public static final String NOME_TABELA = "testes";

    public static final String CAMPO_ID_COMPLETO = NOME_TABELA + "." + _ID;
    public static final String CAMPO_ID_PERFIL_COMPLETO = NOME_TABELA + "." + CAMPO_ID_PERFIL;
    public static final String CAMPO_DATA_TESTE_COMPLETO = NOME_TABELA + "." +  DATA_TESTE;
    public static final String CAMPO_RESULTADO_TESTE_COMPLETO = NOME_TABELA + "." +  RESULTADO_TESTE;
    public static final String CAMPO_PERFIL = "perfil";

    public static final String CAMPO_PERFIL_COMPLETO = BdTabelaPerfis.NOME_TABELA + "." + BdTabelaPerfis.NOME + " AS " + CAMPO_PERFIL;

    public static final String[] TODOS_OS_CAMPOS = new String[]{CAMPO_ID_COMPLETO, CAMPO_ID_PERFIL_COMPLETO, CAMPO_DATA_TESTE_COMPLETO, CAMPO_RESULTADO_TESTE_COMPLETO, CAMPO_PERFIL_COMPLETO};

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
    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        if (!Arrays.asList(columns).contains(CAMPO_PERFIL_COMPLETO)){
        return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

        String campos = TextUtils.join(",", columns);

        String sql = "SELECT " + campos;
        sql += " FROM " + NOME_TABELA + " INNER JOIN " + BdTabelaPerfis.NOME_TABELA;
        sql += " ON " + CAMPO_ID_PERFIL_COMPLETO + "=" + BdTabelaPerfis.CAMPO_ID_COMPLETO;

        if(selection != null){
            sql += " WHERE " + selection;
        }
        if (groupBy != null){
            sql += " GROUP BY " + groupBy;
        }
        if (having != null){
            sql += " HAVING " +having;
        }
        if (orderBy != null){
            sql += " ORDER BY " + orderBy;
        }
        return db.rawQuery(sql, selectionArgs);

    }

    public int update(ContentValues values, String whereClause, String[] whereArgs){
        return db.update(NOME_TABELA,values,whereClause,whereArgs);
    }
    public int delete(String whereClause, String[] whereArgs){
        return db.delete(NOME_TABELA,whereClause,whereArgs);
    }
}
