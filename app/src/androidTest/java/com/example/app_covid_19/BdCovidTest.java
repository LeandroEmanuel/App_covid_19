package com.example.app_covid_19;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class BdCovidTest {
    @Before
    @After
    public void apagaBD(){
        getTargetContext().deleteDatabase(BdCovidOpenHelper.BD_NOME);
    }
    @Test
    public void consegueAbrirBD() {
        // Context of the app under test.
        Context appContext = getTargetContext();

        BdCovidOpenHelper openHelper = new BdCovidOpenHelper(appContext);
        SQLiteDatabase bdCovid = openHelper.getReadableDatabase();
        assertTrue(bdCovid.isOpen());
        bdCovid.close();
    }

    private Context getTargetContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    private long inserePerfil(BdTabelaPerfis tabelaPerfis, Perfil perfil){
        long idPerfil = tabelaPerfis.insert(Converte.perfilParaContentValues(perfil));
        assertNotEquals(-1, idPerfil);
        return idPerfil;
    }
    private long inserePerfil(BdTabelaPerfis tabelaPerfis, String nome, String dataNascimento){
        Perfil perfil = new Perfil();
        perfil.setNome(nome);
        perfil.setDataNascimento(dataNascimento);

        return inserePerfil(tabelaPerfis, perfil);
    }

    private long insereRegisto(SQLiteDatabase dbCovid, String dataRegisto, float temperatura, String sintomas, String nomePerfil, String data_nascimento){
        BdTabelaPerfis tabelaPerfis = new BdTabelaPerfis(dbCovid);

        long idPerfil = inserePerfil(tabelaPerfis,nomePerfil,data_nascimento);

        BdTabelaRegistos tabelaRegistos = new BdTabelaRegistos(dbCovid);

        Registo registo = new Registo();
        registo.setDataRegisto(dataRegisto);
        registo.setTemperatura(35.2f);
        registo.setTosse(1);
        registo.setDifResp(1);
        registo.setIdPerfil(idPerfil);

        long idRegisto = tabelaRegistos.insert(Converte.registoParaContentValues(registo));
        assertNotEquals(-1, idRegisto);

        return idRegisto;
    }
    private long insereTeste(SQLiteDatabase dbCovid, String dataTeste, String resultadoTeste, String nomePerfil, String dataNascimentoPerfil){
        BdTabelaPerfis tabelaPerfis = new BdTabelaPerfis(dbCovid);

        long idPerfil = inserePerfil(tabelaPerfis, nomePerfil, dataNascimentoPerfil);

        BdTabelaTestes tabelaTestes = new BdTabelaTestes(dbCovid);

        Teste teste = new Teste();
        teste.setDataTeste(dataTeste);
        teste.setResultadoTeste(resultadoTeste);
        teste.setIdPerfil(idPerfil);

        long idTeste = tabelaTestes.insert(Converte.testeParaContentValues(teste));
        assertNotEquals(-1, idTeste);
        return idTeste;
    }
    @Test
    public void consegueInserirPerfil(){
        Context appContext = getTargetContext();

        BdCovidOpenHelper openHelper = new BdCovidOpenHelper(appContext);
        SQLiteDatabase dbCovid = openHelper.getWritableDatabase();

        BdTabelaPerfis tabelaPerfis = new BdTabelaPerfis(dbCovid);

        inserePerfil(tabelaPerfis, "Leandro", "24/12/1994");

        dbCovid.close();
    }
    @Test
    public void consegueLerPerfis(){
        Context appContext = getTargetContext();

        BdCovidOpenHelper openHelper = new BdCovidOpenHelper(appContext);
        SQLiteDatabase dbCovid = openHelper.getWritableDatabase();

        BdTabelaPerfis tabelaPerfis = new BdTabelaPerfis(dbCovid);
        Cursor cursor = tabelaPerfis.query(BdTabelaPerfis.TODOS_OS_CAMPOS, null, null, null, null, null);
        int registos = cursor.getCount();
        cursor.close();
        inserePerfil(tabelaPerfis,"Manel", "13/03/1995");

        cursor = tabelaPerfis.query(BdTabelaPerfis.TODOS_OS_CAMPOS, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();

        dbCovid.close();
    }
    @Test
    public void consegueAlterarPerfis(){
        Context appContext = getTargetContext();

        BdCovidOpenHelper openHelper = new BdCovidOpenHelper(appContext);
        SQLiteDatabase dbCovid = openHelper.getWritableDatabase();

        BdTabelaPerfis tabelaPerfis = new BdTabelaPerfis(dbCovid);

        Perfil perfil = new Perfil();
        perfil.setNome("Anacle");
        perfil.setDataNascimento("12/10/1992");
        long id = inserePerfil(tabelaPerfis, perfil);

        perfil.setNome("Anacleto");
        perfil.setDataNascimento("11/11/1990");
        int registosAlterados = tabelaPerfis.update(Converte.perfilParaContentValues(perfil),BdTabelaPerfis._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosAlterados);

        dbCovid.close();
    }
    @Test
    public void consegueApagarPerfis(){
        Context appContext = getTargetContext();
        BdCovidOpenHelper openHelper = new BdCovidOpenHelper(appContext);

        SQLiteDatabase dbCovid = openHelper.getWritableDatabase();

        BdTabelaPerfis tabelaPerfis = new BdTabelaPerfis(dbCovid);

        long id = inserePerfil(tabelaPerfis,"André", "01/02/2000");

        int registosApagados = tabelaPerfis.delete(BdTabelaPerfis._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosApagados);

        dbCovid.close();
    }
    @Test
    public void consegueInserirRegistos(){
        Context appContext = getTargetContext();
        BdCovidOpenHelper openHelper = new BdCovidOpenHelper(appContext);

        SQLiteDatabase dbCovid = openHelper.getWritableDatabase();

        insereRegisto(dbCovid,"10/05/2020", 35.5f,"Tosse","Leandro", "24/12/1994");
        dbCovid.close();
    }
    @Test
    public void consegueLerRegistos(){
        Context appContext = getTargetContext();
        BdCovidOpenHelper openHelper = new BdCovidOpenHelper(appContext);

        SQLiteDatabase dbCovid = openHelper.getWritableDatabase();

        BdTabelaRegistos tabelaRegistos = new BdTabelaRegistos(dbCovid);
        Cursor cursor = tabelaRegistos.query(BdTabelaRegistos.TODOS_OS_CAMPOS, null,null,null,null,null);
        int registos = cursor.getCount();
        cursor.close();

        insereRegisto(dbCovid,"20/05/2020",35.2f,"Tosse", "Anacleto", "25/02/1999");

        cursor = tabelaRegistos.query(BdTabelaRegistos.TODOS_OS_CAMPOS,null,null,null,null,null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();

        dbCovid.close();
    }
    @Test
    public void consegueAlterarRegistos(){
        Context appContext = getTargetContext();

        BdCovidOpenHelper openHelper = new BdCovidOpenHelper(appContext);
        SQLiteDatabase dbCovid = openHelper.getWritableDatabase();

        long idRegisto = insereRegisto(dbCovid,"20/05/2020",36.5f, "Tosse","Miquelina","12/03/1982");

        BdTabelaRegistos tabelaRegistos = new BdTabelaRegistos(dbCovid);

        Cursor cursor = tabelaRegistos.query(BdTabelaRegistos.TODOS_OS_CAMPOS,
                BdTabelaRegistos.CAMPO_ID_COMPLETO + "=?",
                new String[]{String.valueOf(idRegisto)},
                null,
                null,
                null);
        assertEquals(1, cursor.getCount());

        assertTrue(cursor.moveToNext());
        Registo registo = Converte.cursorParaRegisto(cursor);
        cursor.close();
        assertEquals("20/05/2020",registo.getDataRegisto());

        registo.setDataRegisto("22/06/2020");
        int registosAlterados = tabelaRegistos.update(Converte.registoParaContentValues(registo),BdTabelaRegistos.CAMPO_ID_COMPLETO + "=?", new String[]{String.valueOf(registo.getId())});

        dbCovid.close();
    }
    @Test
    public void consegueEliminarRegistos(){
        Context appContext = getTargetContext();

        BdCovidOpenHelper openHelper = new BdCovidOpenHelper(appContext);
        SQLiteDatabase dbCovid = openHelper.getWritableDatabase();

        long id = insereRegisto(dbCovid,"20/05/2020",35.5f,"Tosse","Lumbomir","20/09/1978");

        BdTabelaRegistos tabelaRegistos = new BdTabelaRegistos(dbCovid);
        int registosEliminados = tabelaRegistos.delete(BdTabelaRegistos._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosEliminados);

        dbCovid.close();
    }
    @Test
    public void consegueInserirTestes(){
        Context appContext = getTargetContext();

        BdCovidOpenHelper openHelper = new BdCovidOpenHelper(appContext);
        SQLiteDatabase dbCovid = openHelper.getWritableDatabase();

        insereTeste(dbCovid,"02/06/2020","Negativo", "Almerinda","25/02/1985");
        dbCovid.close();
    }
    @Test
    public void consegueLerTestes(){
        Context appContext = getTargetContext();

        BdCovidOpenHelper openHelper = new BdCovidOpenHelper(appContext);
        SQLiteDatabase dbCovid = openHelper.getWritableDatabase();

        BdTabelaTestes tabelaTestes = new BdTabelaTestes(dbCovid);

        Cursor cursor = tabelaTestes.query(BdTabelaTestes.TODOS_OS_CAMPOS,null,null,null,null, null);
        int registos = cursor.getCount();
        cursor.close();

        insereTeste(dbCovid,"02/06/20/2020","Negativo","Felisbela","26/04/2003");

        cursor = tabelaTestes.query(BdTabelaTestes.TODOS_OS_CAMPOS,null,null,null,null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();

        dbCovid.close();
    }
    @Test
    public void consegueAlterarTestes(){
        Context appContext = getTargetContext();

        BdCovidOpenHelper openHelper = new BdCovidOpenHelper(appContext);
        SQLiteDatabase dbCovid = openHelper.getWritableDatabase();

        long idTeste = insereTeste(dbCovid,"01/05/2020","Negativo","Gustavo","05/05/2004");

        BdTabelaTestes tabelaTestes = new BdTabelaTestes(dbCovid);
        Cursor cursor = tabelaTestes.query(BdTabelaTestes.TODOS_OS_CAMPOS, BdTabelaTestes.CAMPO_ID_COMPLETO +"=?", new String[]{String.valueOf(idTeste)},null,null,null);
        assertEquals(1, cursor.getCount());

        assertTrue(cursor.moveToNext());
        Teste teste = Converte.cursorParaTeste(cursor);
        cursor.close();

        assertEquals("01/05/2020", teste.getDataTeste());
        teste.setResultadoTeste("01/04/2020");
        int registosAlterados = tabelaTestes.update(Converte.testeParaContentValues(teste), BdTabelaTestes.CAMPO_ID_COMPLETO + "=?", new String[]{String.valueOf(teste.getId())});

        dbCovid.close();
    }
    @Test
    public void consegueEliminarTestes(){
        Context appContext = getTargetContext();

        BdCovidOpenHelper openHelper = new BdCovidOpenHelper(appContext);
        SQLiteDatabase dbCovid = openHelper.getWritableDatabase();

        BdTabelaTestes tabelaTestes = new BdTabelaTestes(dbCovid);
        long id = insereTeste(dbCovid,"28/05/2020","Inconcludivo","Paulina","13/01/2001");
        int registosEliminados = tabelaTestes.delete(BdTabelaTestes._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosEliminados);

        dbCovid.close();
    }


}
