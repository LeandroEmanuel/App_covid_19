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


public class fragment_registo_diario extends Fragment {
    EditText TextInputLayoutTemperatura;
    TextView textViewDataRegistoDiario;
    Button buttonSelecionarDataRegisto;
    private int mAno, mMes, mDia;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registo_diario, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputLayoutTemperatura = view.findViewById(R.id.TextInputLayoutTemperatura);
        view.findViewById(R.id.buttonCancelarRegistoDiario).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelarRegistoDiario();
            }
        });
        buttonSelecionarDataRegisto = (Button)view.findViewById(R.id.buttonSelecionarDataRegisto);
        textViewDataRegistoDiario =(TextView)view.findViewById(R.id.textViewDataRegistoDiario);
        view.findViewById(R.id.buttonSelecionarDataRegisto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( v == buttonSelecionarDataRegisto){
                    final Calendar calendario = Calendar.getInstance();
                    mAno = calendario.get(Calendar.YEAR);
                    mMes = calendario.get(Calendar.MONTH);
                    mDia = calendario.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            textViewDataRegistoDiario.setText(dayOfMonth+ "/" +(month + 1) + "/" + year);
                        }
                    }, mAno, mMes, mDia);
                    datePickerDialog.show();
                }
            }
        });

    }

    private void cancelarRegistoDiario() {
        NavController navController = NavHostFragment.findNavController(fragment_registo_diario.this);
        navController.navigate(R.id.to_menu_principal);
        TextInputLayoutTemperatura.setText("");
    }
}
