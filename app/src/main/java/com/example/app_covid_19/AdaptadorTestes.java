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
    private final Context context;

    private Cursor cursor;

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
    public AdaptadorTestes.ViewHolderTeste onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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

    public class ViewHolderTeste extends RecyclerView.ViewHolder {

        private Teste teste = null;
        private final TextView textViewNomeItemTeste;
        private final TextView textViewDataItemTeste;
        private final TextView textViewResultadoItemTeste;

        public ViewHolderTeste(@NonNull View itemView) {
            super(itemView);

            textViewNomeItemTeste = (TextView)itemView.findViewById(R.id.textViewNomeItemTeste);
            textViewDataItemTeste = (TextView)itemView.findViewById(R.id.textViewDataResultadoItemTeste);
            textViewResultadoItemTeste = (TextView)itemView.findViewById(R.id.textViewResultadoItemTeste);
        }

        public void setTeste(Teste teste) {
            this.teste = teste;
            textViewNomeItemTeste.setText(teste.getPerfil());
            textViewDataItemTeste.setText(teste.getDataTeste());
            textViewResultadoItemTeste.setText(teste.getResultadoTeste());
        }
    }
}
