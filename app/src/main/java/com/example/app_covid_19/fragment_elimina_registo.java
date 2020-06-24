package com.example.app_covid_19;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class fragment_elimina_registo extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private TextView textViewEliminaDataRegisto;
    private TextView textViewEliminaTemperatura;
    private TextView textViewEliminaTosseRegisto;
;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_elimina_registo, container, false);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanState){
        super.onViewCreated(view, savedInstanState);
        Context context = getContext();

        MainActivity activity =(MainActivity) getActivity();
        activity.setFragmentActual(this);
        //activity.setMenuActual(R.menu.);
        textViewEliminaDataRegisto = (TextView) view.findViewById(R.id.textViewEliminaDataRegisto);
        textViewEliminaTemperatura = (EditText) view.findViewById(R.id.editTextTemperatura);
        Registo registo = activity.getRegisto();

        //textViewEliminaDataRegisto.setText(registo.getDataRegisto();
        //textViewEliminaTemperatura.setText(registo.getTemperatura());


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