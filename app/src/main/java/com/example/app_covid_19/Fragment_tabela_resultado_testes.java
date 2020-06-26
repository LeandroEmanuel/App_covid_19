package com.example.app_covid_19;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;


public class fragment_tabela_resultado_testes extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int _CURSOR_LOADER_TESTES = 0;
    private AdaptadorTestes adaptadorTestes;
    private long idPerfil;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabela_resultado_testes, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getContext();

        MainActivity activity =(MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_historico_registos);
        idPerfil = activity.getPerfil().getId();

        RecyclerView recyclerViewTestes = (RecyclerView) view.findViewById(R.id.recyclerViewTestes);
        adaptadorTestes = new AdaptadorTestes(context);

        recyclerViewTestes.setAdapter(adaptadorTestes);
        recyclerViewTestes.setLayoutManager(new LinearLayoutManager(context));
        adaptadorTestes.setCursor(null);

        LoaderManager.getInstance(this).initLoader(_CURSOR_LOADER_TESTES, null, this);
    }

    public void EliminarTeste(){
        NavController navController = NavHostFragment.findNavController(fragment_tabela_resultado_testes.this);
        navController.navigate(R.id.action_fragment_tabela_resultado_testes_to_fragment_eliminar_testes);
    }

    public void historicoTestes(){
        NavController navController = NavHostFragment.findNavController(fragment_tabela_resultado_testes.this);
        navController.navigate(R.id.to_testes);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(getContext(), BdCovidContentProvider.ENDERECO_TESTES, BdTabelaTestes.TODOS_OS_CAMPOS,  BdTabelaTestes.CAMPO_ID_PERFIL_COMPLETO + "=?", new String[]{String.valueOf(idPerfil)}, BdTabelaTestes.DATA_TESTE);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adaptadorTestes.setCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
