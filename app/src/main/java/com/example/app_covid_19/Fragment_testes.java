package com.example.app_covid_19;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;


public class fragment_testes extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_testes, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.buttonNovoTeste).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novoTeste();
            }
        });
        view.findViewById(R.id.buttonHistoricoTestes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultadoTeste();
            }
        });

    }

    private void novoTeste() {
        NavController navController = NavHostFragment.findNavController(fragment_testes.this);
        navController.navigate(R.id.to_resultado_teste);
    }
    private void resultadoTeste() {
        NavController navController = NavHostFragment.findNavController(fragment_testes.this);
        navController.navigate(R.id.to_tabela_resultado_testes);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
