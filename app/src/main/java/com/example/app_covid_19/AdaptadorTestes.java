package com.example.app_covid_19;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorTestes extends RecyclerView.Adapter<AdaptadorTestes.ViewHolderTeste> {
    private Context context;

    private Cursor cursor = null;

    public void setCursor(Cursor cursor){
        if(cursor != this.cursor){
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }

    public AdaptadorTestes(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderTeste onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemTeste = LayoutInflater.from(context).inflate(R.layout.item_teste, parent, false);

        return new ViewHolderTeste(itemTeste);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTeste holder, int position) {
        cursor.moveToPosition(position);
        Teste teste = Converte.cursorParaTeste(cursor);
        holder.setTeste(teste);
    }

    @Override
    public int getItemCount() {
        if(cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }

    private ViewHolderTeste viewHolderTesteSelecionado = null;

    public class ViewHolderTeste extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Teste teste = null;

        private final TextView textViewNomeItemTeste;
        private final TextView textViewDataItemTeste;
        private final TextView textViewResultadoItemTeste;

        public ViewHolderTeste(@NonNull View itemView) {
            super(itemView);

            textViewNomeItemTeste = (TextView) itemView.findViewById(R.id.textViewNomeItemTeste);
            textViewDataItemTeste = (TextView) itemView.findViewById(R.id.textViewDataResultadoItemTeste);
            textViewResultadoItemTeste = (TextView) itemView.findViewById(R.id.textViewResultadoItemTeste);

            itemView.setOnClickListener(this);
        }

        public void setTeste(Teste teste) {
            this.teste = teste;
            textViewNomeItemTeste.setText(teste.getPerfil());
            textViewDataItemTeste.setText(teste.getDataTeste());
            textViewResultadoItemTeste.setText(teste.getResultadoTeste());
        }

        @Override
        public void onClick(View v) {
            if (viewHolderTesteSelecionado == this) {
                return;
            }

            if (viewHolderTesteSelecionado != null) {
                viewHolderTesteSelecionado.desSeleciona();
            }
            viewHolderTesteSelecionado = this;
            seleciona();

            MainActivity activity = (MainActivity) AdaptadorTestes.this.context;
            activity.testeAlterado(teste);

        }

        private void seleciona() {
            itemView.setBackgroundResource(R.color.colorSelected);
        }

        private void desSeleciona() {
            itemView.setBackgroundResource(android.R.color.white);
        }
    }
}
