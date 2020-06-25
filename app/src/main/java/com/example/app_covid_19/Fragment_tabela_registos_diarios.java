package com.example.app_covid_19;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.app.LoaderManager.LoaderCallbacks;
import androidx.loader.content.Loader;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class fragment_tabela_registos_diarios extends Fragment implements LoaderCallbacks<Cursor> {

    public static final int _CURSOR_LOADER_REGISTOS = 0;
    private AdaptadorRegistos adaptadorRegistos;
    private long idPerfil;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabela_registos_diarios, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getContext();

        MainActivity activity =(MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_historico_registos);
        idPerfil = activity.getPerfil().getId();

        RecyclerView recyclerViewRegistos = (RecyclerView) view.findViewById(R.id.recyclerViewRegistos);
        adaptadorRegistos = new AdaptadorRegistos(context);

        recyclerViewRegistos.setAdapter(adaptadorRegistos);
        recyclerViewRegistos.setLayoutManager(new LinearLayoutManager(context));
        adaptadorRegistos.setCursor(null);

        LoaderManager.getInstance(this).initLoader(_CURSOR_LOADER_REGISTOS, null, this);

    }

    public void EliminarRegisto(){
        NavController navController = NavHostFragment.findNavController(fragment_tabela_registos_diarios.this);
        navController.navigate(R.id.action_fragment_tabela_registos_diarios_to_fragment_elimina_registo);
    }

    public void historico(){
        NavController navController = NavHostFragment.findNavController(fragment_tabela_registos_diarios.this);
        navController.navigate(R.id.to_historico);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new androidx.loader.content.CursorLoader(getContext(), BdCovidContentProvider.ENDERECO_REGISTOS,BdTabelaRegistos.TODOS_OS_CAMPOS, BdTabelaRegistos.CAMPO_ID_PERFIL_COMPLETO + "=?", new String[]{String.valueOf(idPerfil)}, BdTabelaRegistos.DATA_REGISTO);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adaptadorRegistos.setCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}