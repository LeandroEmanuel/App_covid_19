package com.example.app_covid_19;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class fragment_selecionar_perfil extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int _CURSOR_LOADER_PERFIS = 0;
    private AdaptadorPerfis adaptadorPerfis;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selecionar_perfil, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getContext();

        MainActivity activity =(MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_selecionar_perfil);

        RecyclerView recyclerViewPerfis = (RecyclerView) view.findViewById(R.id.recyclerViewPerfis);
        adaptadorPerfis = new AdaptadorPerfis(context);

        recyclerViewPerfis.setAdapter(adaptadorPerfis);
        recyclerViewPerfis.setLayoutManager(new LinearLayoutManager(context));
        adaptadorPerfis.setCursor(null);

        view.findViewById(R.id.buttonMaisInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maisInformacao();
            }
        });

        LoaderManager.getInstance(this).initLoader(_CURSOR_LOADER_PERFIS, null, this);
    }
    private void maisInformacao() {
        NavController navController = NavHostFragment.findNavController(fragment_selecionar_perfil.this);
        navController.navigate(R.id.fragment_editar_perfis);
    }

    public void novoPerfil() {
        NavController navController = NavHostFragment.findNavController(fragment_selecionar_perfil.this);
        navController.navigate(R.id.action_novoPerfil);
    }
    public void editarPerfil(){
        NavController navController = NavHostFragment.findNavController(fragment_selecionar_perfil.this);
        navController.navigate(R.id.action_editar_perfil);
    }
    public void eliminarPerfil(){
        NavController navController = NavHostFragment.findNavController(fragment_selecionar_perfil.this);
        navController.navigate(R.id.action_eliminar_perfil);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new androidx.loader.content.CursorLoader(getContext(),BdCovidContentProvider.ENDERECO_PERFIS, BdTabelaPerfis.TODOS_OS_CAMPOS, null, null, BdTabelaPerfis.NOME);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adaptadorPerfis.setCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adaptadorPerfis.setCursor(null);

    }
}
