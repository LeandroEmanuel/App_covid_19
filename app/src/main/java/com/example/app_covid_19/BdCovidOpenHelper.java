package com.example.app_covid_19;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BdCovidOpenHelper extends SQLiteOpenHelper {

    public static final String BD_NOME = "covid.db";
    public static final int VERSAO_BD = 1;
    private static boolean DESENVOLVIMENTO = true;

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database
     */
    public BdCovidOpenHelper(@Nullable Context context) {
        super(context,BD_NOME , null, VERSAO_BD);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        BdTabelaPerfis tabelaPerfis = new BdTabelaPerfis(db);
        tabelaPerfis.criaTabelaPerfis();

        BdTabelaRegistos tabelaRegistos = new BdTabelaRegistos(db);
        tabelaRegistos.criarTabelaRegistos();

        BdTabelaTestes tabelaTestes = new BdTabelaTestes(db);
        tabelaTestes.criaTabelaTestes();

        if(DESENVOLVIMENTO){
            geraDados(db);
        }

    }

    private void geraDados(SQLiteDatabase db) {
        BdTabelaPerfis tabelaPerfis = new BdTabelaPerfis(db);

        Perfil perfil = new Perfil();
        perfil.setNome("Leandro");
        perfil.setDataNascimento("24/12/1994");
        long idPerfilLeandro = tabelaPerfis.insert(Converte.perfilParaContentValues(perfil));

       BdTabelaRegistos tabelaRegistos = new BdTabelaRegistos(db);

        Registo registo = new Registo();
        registo.setDataRegisto("20/05/2020");
        registo.setTemperatura(35.6f);
        registo.setSintomas("Tosse");
        registo.setIdPerfil(idPerfilLeandro);
        long idRegistoLeandro = tabelaRegistos.insert(Converte.registoParaContentValues(registo));

        BdTabelaTestes tabelaTestes = new BdTabelaTestes(db);

        Teste teste = new Teste();
        teste.setDataTeste("02/06/2020");
        teste.setResultadoTeste("Negativo");
        teste.setIdPerfil(idPerfilLeandro);
        long idTesteLeandro = tabelaTestes.insert(Converte.testeParaContentValues(teste));


    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
