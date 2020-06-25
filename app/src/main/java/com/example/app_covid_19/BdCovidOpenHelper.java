package com.example.app_covid_19;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class BdCovidOpenHelper extends SQLiteOpenHelper {

    public static final String BD_NOME = "covid.db";
    public static final int VERSAO_BD = 1;
    private static boolean DESENVOLVIMENTO = true;

    private int mAno, mMes, mDia;

    public BdCovidOpenHelper(@Nullable Context context) {
        super(context,BD_NOME , null, VERSAO_BD);
    }

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
        perfil.setCardio(0);
        perfil.setDiabetes(0);
        perfil.setHiper(0);
        perfil.setOnco(0);
        perfil.setResp(1);
        long idPerfilLeandro = tabelaPerfis.insert(Converte.perfilParaContentValues(perfil));

        perfil.setNome("Manel");
        perfil.setDataNascimento("22/11/1974");
        perfil.setCardio(0);
        perfil.setDiabetes(0);
        perfil.setHiper(0);
        perfil.setOnco(0);
        perfil.setResp(0);
        long idPerfilManel = tabelaPerfis.insert(Converte.perfilParaContentValues(perfil));

       BdTabelaRegistos tabelaRegistos = new BdTabelaRegistos(db);

        final Calendar calendario = Calendar.getInstance();
        mAno = calendario.get(Calendar.YEAR);
        mMes = calendario.get(Calendar.MONTH);
        mDia = calendario.get(Calendar.DAY_OF_MONTH);
        String sysDate = mDia + "/" + (mMes + 1) + "/" + mAno;

        Registo registo = new Registo();
        registo.setDataRegisto(sysDate);
        registo.setTemperatura(35.6f);
        registo.setTosse(1);
        registo.setDifResp(0);
        registo.setIdPerfil(idPerfilLeandro);
        long idRegistoLeandro = tabelaRegistos.insert(Converte.registoParaContentValues(registo));

        registo.setDataRegisto("12/06/2020");
        registo.setTemperatura(35.6f);
        registo.setTosse(0);
        registo.setDifResp(1);
        registo.setIdPerfil(idPerfilManel);
        long idRegistoManel = tabelaRegistos.insert(Converte.registoParaContentValues(registo));

        BdTabelaTestes tabelaTestes = new BdTabelaTestes(db);

        Teste teste = new Teste();
        teste.setDataTeste("02/06/2020");
        teste.setResultadoTeste("Negativo");
        teste.setIdPerfil(idPerfilLeandro);
        long idTesteLeandro = tabelaTestes.insert(Converte.testeParaContentValues(teste));

        teste.setDataTeste("19/06/2020");
        teste.setResultadoTeste("Inconclusivo");
        teste.setIdPerfil(idPerfilLeandro);
        long idTesteLManel = tabelaTestes.insert(Converte.testeParaContentValues(teste));


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
