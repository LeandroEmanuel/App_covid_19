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


public class fragment_eliminar_perfil extends Fragment {

    private TextView textViewDataNascimentoPerfilEliminar;
    private TextView editTextNomePerfilEliminar;
    private TextView textViewEliminaDoencasCardioVasculares;
    private TextView textViewEliminaDiabetes;
    private TextView textViewEliminaHipertensao;
    private TextView textViewEliminaOncologica;
    private TextView textViewEliminaRespiratoria;

    private Perfil perfil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eliminar_perfil, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();

        MainActivity activity = (MainActivity) getActivity();
        activity.setFragmentActual(this);

        activity.setMenuActual(R.menu.menu_eliminar_perfil);

        editTextNomePerfilEliminar =(TextView) view.findViewById(R.id.editTextNomePerfilEliminar);
        textViewDataNascimentoPerfilEliminar = (TextView) view.findViewById(R.id.textViewDataNascimentoPerfilEliminar);
        textViewEliminaDoencasCardioVasculares = (TextView) view.findViewById(R.id.textViewEliminaDoencasCardioVasculares);
        textViewEliminaDiabetes = (TextView) view.findViewById(R.id.textViewEliminaDiabetes);
        textViewEliminaHipertensao = (TextView) view.findViewById(R.id.textViewEliminaHipertensao);
        textViewEliminaOncologica = (TextView) view.findViewById(R.id.textViewEliminaOncologica);
        textViewEliminaRespiratoria = (TextView) view.findViewById(R.id.textViewEliminaRespiratoria);

        perfil = activity.getPerfil();

        editTextNomePerfilEliminar.setText(perfil.getNome());
        textViewDataNascimentoPerfilEliminar.setText(perfil.getDataNascimento());
        textViewEliminaDoencasCardioVasculares.setText("" + perfil.getCardio());
        textViewEliminaDiabetes.setText("" + perfil.getDiabetes());
        textViewEliminaHipertensao.setText("" + perfil.getHiper());
        textViewEliminaOncologica.setText("" + perfil.getOnco());
        textViewEliminaRespiratoria.setText("" + perfil.getResp());

        //eliminar registos do perfil

        //eliminar testes do perfil

    }

    public void cancelar(){
        NavController navController = NavHostFragment.findNavController(fragment_eliminar_perfil.this);
        navController.navigate(R.id.cancelar_eliminar_perfil);
    }

    public void eliminar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Eliminar Perfil");
        builder.setMessage("Tem a certeza que pretende eliminar o perfil'" + perfil.getNome() + "'");
        builder.setIcon(R.drawable.ic_round_delete_forever_24);
        builder.setPositiveButton("Sim",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmarEliminar();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelar();
            }
        });
        builder.show();
    }

    private void confirmarEliminar() {
        try{
            Uri enderecoPerfil = Uri.withAppendedPath(BdCovidContentProvider.ENDERECO_PERFIS, String.valueOf(perfil.getId()));
            int registosApagados = getActivity().getContentResolver().delete(enderecoPerfil, null, null);

            if(registosApagados == 1){
                Toast.makeText(getContext(),"Perfil eliminado com sucesso", Toast.LENGTH_SHORT).show();
                NavController navController = NavHostFragment.findNavController(fragment_eliminar_perfil.this);
                navController.navigate(R.id.cancelar_eliminar_perfil);
                return;
            }
        }catch (Exception e){
        }
        Snackbar.make(editTextNomePerfilEliminar,"Erro: Não foi possivél eliminar o perfil", Snackbar.LENGTH_INDEFINITE).show();
    }
}