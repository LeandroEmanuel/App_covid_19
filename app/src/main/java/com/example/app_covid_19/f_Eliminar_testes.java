package com.example.app_covid_19;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class f_Eliminar_testes extends Fragment {

    private TextView textViewEliminarDataTeste;
    private TextView textViewNomeTeste;
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
        activity.setMenuActual(R.menu.menu_eliminar_teste);

        textViewNomeTeste = (TextView) view.findViewById(R.id.textViewNomeTeste);
        textViewEliminarDataTeste = (TextView) view.findViewById(R.id.textViewEliminarDataTeste);

        teste = activity.getTest();

        textViewNomeTeste.setText(teste.getPerfil());
        textViewEliminarDataTeste.setText(""+teste.getDataTeste());
    }
    public void cancelarEliminarTeste(){
        NavController navController = NavHostFragment.findNavController(f_Eliminar_testes.this);
        navController.navigate(R.id.action_fragment_eliminar_testes_to_fragment_tabela_resultado_testes);
    }
    public void eliminarTeste(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle(R.string.eliminar_teste_dialog);
        builder.setMessage(getString(R.string.certeza_eliminar_teste) + "\n'"+ teste.getPerfil() + "'"+ "\n "+ "'"+ teste.getDataTeste() + "'");
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
                NavController navController = NavHostFragment.findNavController(f_Eliminar_testes.this);
                navController.navigate(R.id.action_fragment_eliminar_testes_to_fragment_tabela_resultado_testes);
                Toast.makeText(getContext(), R.string.teste_eliminado_sucesso, Toast.LENGTH_SHORT).show();
                return;
            }
        }catch (Exception e){
        }
        Snackbar.make(textViewEliminarDataTeste, R.string.erro_eliminar_teste, Snackbar.LENGTH_INDEFINITE).show();
    }
}