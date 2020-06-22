package com.example.app_covid_19;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BdCovidContentProvider extends ContentProvider {


    public static final String AUTORIDADE = "com.example.app_covid_19";
    public static final String PERFIS = "perfis";
    public static final String REGISTOS = "registos";
    public static final String TESTES = "testes";

    private static final Uri ENDERECO_BASE = Uri.parse("content://" + AUTORIDADE);
    public static final Uri ENDERECO_PERFIS = Uri.withAppendedPath(ENDERECO_BASE, PERFIS);
    public static final Uri ENDERECO_REGISTOS = Uri.withAppendedPath(ENDERECO_BASE, REGISTOS);
    public static final Uri ENDERECO_TESTES = Uri.withAppendedPath(ENDERECO_BASE, TESTES);

    public static final int URI_PERFIS = 100;
    public static final int URI_ID_PERFIL = 101;
    public static final int URI_REGISTOS = 200;
    public static final int URI_ID_REGISTO = 201;
    public static final int URI_TESTES = 300;
    public static final int URI_ID_TESTE = 301;
    public static final String CURSOR_DIR = "vnd.android.cursor.dir/";
    public static final String CURSOR_ITEM = "vnd.android.cursor.item";
    private BdCovidOpenHelper openHelper;

    private UriMatcher getUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTORIDADE, PERFIS, URI_PERFIS); // mostrar todos os perfis
        uriMatcher.addURI(AUTORIDADE, PERFIS + "/#", URI_ID_PERFIL); // mostrar um perfil

        uriMatcher.addURI(AUTORIDADE, REGISTOS, URI_REGISTOS);
        uriMatcher.addURI(AUTORIDADE, REGISTOS + "/#", URI_ID_REGISTO);

        uriMatcher.addURI(AUTORIDADE,TESTES, URI_TESTES);
        uriMatcher.addURI(AUTORIDADE,TESTES + "/#", URI_ID_TESTE);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        openHelper = new BdCovidOpenHelper(getContext());

        return true;
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db =openHelper.getWritableDatabase();

        String id =uri.getLastPathSegment();

        switch(getUriMatcher().match(uri)){
            case URI_PERFIS:
                return new BdTabelaPerfis(db).query(projection, selection, selectionArgs, null, null, sortOrder);
            case URI_ID_PERFIL:
                return new BdTabelaPerfis(db).query(projection, BdTabelaPerfis._ID + "=?", new String[]{id},null, null, sortOrder);

            case URI_REGISTOS:
                return new BdTabelaRegistos(db).query(projection, selection, selectionArgs, null, null, sortOrder);
            case URI_ID_REGISTO:
                return new BdTabelaRegistos(db).query(projection, BdTabelaRegistos._ID + "=?", new String[]{id},null, null, sortOrder);

            case URI_TESTES:
                return new BdTabelaTestes(db).query(projection, selection, selectionArgs, null, null, sortOrder);
            case URI_ID_TESTE:
                return new BdTabelaTestes(db).query(projection, BdTabelaTestes._ID + "=?", new String[]{id},null, null, sortOrder);

            default:
                throw new UnsupportedOperationException("Endereço de query inválido (QUERY): " + uri.getPath());

        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int codigoUri = getUriMatcher().match(uri);

        switch(codigoUri){
            case URI_PERFIS:
                return CURSOR_DIR + PERFIS;
            case URI_ID_PERFIL:
                return CURSOR_ITEM + PERFIS;
            case URI_REGISTOS:
                return CURSOR_DIR + REGISTOS;
            case URI_ID_REGISTO:
                return CURSOR_ITEM + REGISTOS;
            case URI_TESTES:
                return CURSOR_DIR + TESTES;
            case URI_ID_TESTE:
                return CURSOR_ITEM + TESTES;

            default:
                return null;
        }
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = openHelper.getWritableDatabase();

        long id;

        switch (getUriMatcher().match(uri)){
            case URI_PERFIS:
                id = (new BdTabelaPerfis(db).insert(values));
                break;
            case URI_REGISTOS:
                id = (new BdTabelaRegistos(db).insert(values));
                break;
            case URI_TESTES:
                id = (new BdTabelaTestes(db).insert(values));
                break;

            default:
                throw new UnsupportedOperationException("Endereço de insert inválido (INSERT): " + uri.getPath());
        }
        if(id == -1){
            throw new SQLException("Não foi possivel inserir o perfil " + uri.getPath());
        }
        return Uri.withAppendedPath(uri, String.valueOf(id));
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = openHelper.getWritableDatabase();

        String id = uri.getLastPathSegment();

        switch (getUriMatcher().match(uri)){
            case URI_ID_PERFIL: // porque só quero apagar um item da BD de cada vez e não todos
                return new BdTabelaPerfis(db).delete(BdTabelaPerfis._ID + "=?", new String[]{id});
            case URI_ID_REGISTO:
                return new BdTabelaRegistos(db).delete(BdTabelaRegistos._ID + "=?", new String[]{id});
            case URI_ID_TESTE:
                return new BdTabelaTestes(db).delete(BdTabelaTestes._ID + "=?", new String[]{id});

            default:
                throw new UnsupportedOperationException("Endereço de delete inválido (DELETE): " + uri.getPath());
        }
    }


    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = openHelper.getWritableDatabase();

        String id = uri.getLastPathSegment();

        switch (getUriMatcher().match(uri)){
            case URI_ID_PERFIL:
                return new BdTabelaPerfis(db).update(values, BdTabelaPerfis._ID + "=?", new String[]{id});
            case URI_ID_REGISTO:
                return new BdTabelaRegistos(db).update(values, BdTabelaRegistos._ID + "=?", new String[]{id});
            case URI_ID_TESTE:
                return new BdTabelaTestes(db).update(values, BdTabelaTestes._ID + "=?", new String[]{id});

            default:
                throw new UnsupportedOperationException("Endereço de update inválido (UPDATE): " + uri.getPath());
        }
    }
}
