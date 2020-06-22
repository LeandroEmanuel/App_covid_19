package com.example.app_covid_19;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;


public class fragment_resultado_teste extends Fragment {

    EditText nome;
    TextView textViewDataTesteResultado;
    Button buttonSelecionarDataResultadoTeste;
    private int mAno, mMes, mDia;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resultado_teste, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.buttonCancelarResultadoTeste).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelarResultadoTeste();
            }
        });
        buttonSelecionarDataResultadoTeste = (Button)view.findViewById(R.id.buttonSelecionarDataResultadoTeste);
        textViewDataTesteResultado =(TextView)view.findViewById(R.id.textViewDataTesteResultado);
        view.findViewById(R.id.buttonSelecionarDataResultadoTeste).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( v == buttonSelecionarDataResultadoTeste){
                    final Calendar calendario = Calendar.getInstance();
                    mAno = calendario.get(Calendar.YEAR);
                    mMes = calendario.get(Calendar.MONTH);
                    mDia = calendario.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            textViewDataTesteResultado.setText(dayOfMonth+ "/" +(month + 1) + "/" + year);
                        }
                    }, mAno, mMes, mDia);
                    datePickerDialog.show();
                }
            }
        });

    }

    private void cancelarResultadoTeste() {
        NavController navController = NavHostFragment.findNavController(fragment_resultado_teste.this);
        navController.navigate(R.id.to_menu_principal);
    }
}
