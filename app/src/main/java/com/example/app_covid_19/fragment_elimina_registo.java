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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;


public class fragment_elimina_registo extends Fragment {
    private TextView textViewEliminaDataRegisto;
    private TextView textViewEliminaTemperatura;
    private TextView textViewNomePerfilEliminar;
    private Registo registo;

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
        activity.setMenuActual(R.menu.menu_eliminar_registo);

        textViewNomePerfilEliminar = (TextView) view.findViewById(R.id.textViewNomePerfilEliminar);
        textViewEliminaDataRegisto = (TextView) view.findViewById(R.id.textViewEliminaDataRegisto);
        textViewEliminaTemperatura = (TextView) view.findViewById(R.id.textViewEliminaTemperatura);

        registo = activity.getRegisto();
        textViewNomePerfilEliminar.setText(registo.getPerfil());
        textViewEliminaDataRegisto.setText(registo.getDataRegisto());
        textViewEliminaTemperatura.setText(String.valueOf(registo.getTemperatura()));

    }
    public void cancelarEliminarRegisto(){
        NavController navController = NavHostFragment.findNavController(fragment_elimina_registo.this);
        navController.navigate(R.id.action_fragment_elimina_registo_to_fragment_selecionar_perfil2);
    }

    public void eliminarRegisto(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle(R.string.eliminar_registo_dialog);
        builder.setMessage(getString(R.string.certeza_eliminar_registo) + registo.getPerfil() + "'");
        builder.setIcon(R.drawable.ic_round_delete_forever_24);
        builder.setPositiveButton(R.string.sim,  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmarEliminarRegisto();
            }
        });
        builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelarEliminarRegisto();
            }
        });
        builder.show();

    }

    public void confirmarEliminarRegisto() {
        try{
            Uri enderecoPerfil = Uri.withAppendedPath(BdCovidContentProvider.ENDERECO_REGISTOS, String.valueOf(registo.getId()));
            int registosApagados = getActivity().getContentResolver().delete(enderecoPerfil, null, null);

            if(registosApagados == 1){
                Toast.makeText(getContext(), R.string.registo_eliminado_sucesso, Toast.LENGTH_SHORT).show();
                NavController navController = NavHostFragment.findNavController(fragment_elimina_registo.this);
                navController.navigate(R.id.action_fragment_elimina_registo_to_fragment_tabela_registos_diarios);
                return;
            }
        }catch (Exception e){
        }
        Snackbar.make(textViewEliminaDataRegisto, R.string.erro_eliminar_registo, Snackbar.LENGTH_INDEFINITE).show();
    }
}