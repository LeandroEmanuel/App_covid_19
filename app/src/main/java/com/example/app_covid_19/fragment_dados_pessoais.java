package com.example.app_covid_19;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.ConversationAction;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;


public class fragment_dados_pessoais extends Fragment {

    EditText nome;
    TextView textViewDataNascimento;
    Button buttonSelecionarDataNascimento;
    private int mAno, mMes, mDia;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dados_pessoais, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nome = view.findViewById(R.id.editTextNome);
        textViewDataNascimento = view.findViewById(R.id.textViewDataNascimento);
        view.findViewById(R.id.buttonCancelarDAdosPessoais).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               cancelarDadosPessoais();

           }
       });
        buttonSelecionarDataNascimento = (Button)view.findViewById(R.id.buttonSelecionarDataNascimento);
        textViewDataNascimento =(TextView)view.findViewById(R.id.textViewDataNascimento);
        view.findViewById(R.id.buttonSelecionarDataNascimento).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( v == buttonSelecionarDataNascimento){
                    final Calendar calendario = Calendar.getInstance();
                    mAno = calendario.get(Calendar.YEAR);
                    mMes = calendario.get(Calendar.MONTH);
                    mDia = calendario.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            textViewDataNascimento.setText(dayOfMonth+ "/" +(month + 1) + "/" + year);
                        }
                    }, mAno, mMes, mDia);
                    datePickerDialog.show();
                }
            }
        });
    }
    private void cancelarDadosPessoais() {
        NavController navController = NavHostFragment.findNavController(fragment_dados_pessoais.this);
        navController.navigate(R.id.to_menu_principal);
        nome.setText("");
        textViewDataNascimento.setText("");

    }

}