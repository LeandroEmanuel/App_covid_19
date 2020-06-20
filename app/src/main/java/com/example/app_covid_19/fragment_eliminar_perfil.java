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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class fragment_eliminar_perfil extends Fragment {

    private EditText nome;
    private TextView textViewDataNascimento;
    private EditText editTextNome;
    private Perfil perfil;

    public fragment_eliminar_perfil() {
        // Required empty public constructor
    }

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

        nome = view.findViewById(R.id.editTextAlteraNome);
        textViewDataNascimento = view.findViewById(R.id.textViewAlteraDataNascimento);

        perfil = activity.getPerfil();

        editTextNome.setText(perfil.getNome());
        textViewDataNascimento.setText(perfil.getDataNascimento());
    }

    public void cancelar(){
        NavController navController = NavHostFragment.findNavController(fragment_eliminar_perfil.this);
        navController.navigate(R.id.action_canselar_perfil;
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
                navController.navigate(R.id.action_canselar_perfil);
                return;
            }
        }catch (Exception e){
        }
        Snackbar.make(editTextNome,"Erro: Não foi possivél eliminar o perfil", Snackbar.LENGTH_INDEFINITE).show();
    }
}