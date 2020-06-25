package com.example.app_covid_19;

import android.app.AlertDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class fragment_eliminar_testes extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private TextView textViewEliminarDataTeste;
    private TextView textViewEliminaResultadoTeste;
    private Teste teste;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eliminar_testes, container, false);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanState){
        super.onViewCreated(view, savedInstanState);
        Context context = getContext();

        MainActivity activity =(MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_eliminar_registo);
        textViewEliminarDataTeste = (TextView) view.findViewById(R.id.textViewEliminarDataTeste);
        textViewEliminaResultadoTeste = (TextView) view.findViewById(R.id.textViewEliminaResultadoTeste);
        Registo registo = activity.getRegisto();

        textViewEliminarDataTeste.setText(registo.getDataRegisto());
        textViewEliminaResultadoTeste.setText(String.valueOf(registo.getTemperatura()));//alterei
    }
    public void cancelarEliminarTeste(){
        NavController navController = NavHostFragment.findNavController(fragment_eliminar_testes.this);
        navController.navigate(R.id.action_fragment_elimina_registo_to_fragment_selecionar_perfil2);
    }
    public void eliminarTeste(){
        //long idPerfilSelecionado = ((MainActivity) getActivity()).getPerfil().getId();

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle(R.string.eliminar_teste_dialog);
        builder.setMessage(getString(R.string.certeza_eliminar_teste) + teste.getPerfil() + "'");
        builder.setIcon(R.drawable.ic_round_delete_forever_24);
        builder.setPositiveButton(R.string.sim,  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmarEliminarTeste();
            }
        });
        builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {    cancelarEliminarTeste();
            }
        });
        builder.show();
    }

    public void confirmarEliminarTeste() {
        try{
            Uri enderecoPerfil = Uri.withAppendedPath(BdCovidContentProvider.ENDERECO_TESTES, String.valueOf(teste.getId()));
            int registosApagados = getActivity().getContentResolver().delete(enderecoPerfil, null, null);

            if(registosApagados == 1){
                Toast.makeText(getContext(), R.string.teste_eliminado_sucesso, Toast.LENGTH_SHORT).show();
                NavController navController = NavHostFragment.findNavController(fragment_eliminar_testes.this);
                navController.navigate(R.id.action_fragment_elimina_registo_to_fragment_selecionar_perfil2);
                return;
            }
        }catch (Exception e){
        }
        Snackbar.make(textViewEliminarDataTeste,"Erro: Não foi possivél eliminar o teste", Snackbar.LENGTH_INDEFINITE).show();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new androidx.loader.content.CursorLoader(getContext(), BdCovidContentProvider.ENDERECO_REGISTOS, BdTabelaRegistos.TODOS_OS_CAMPOS, null, null, BdTabelaPerfis.NOME);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}