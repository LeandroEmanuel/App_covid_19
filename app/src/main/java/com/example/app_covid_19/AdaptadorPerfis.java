package com.example.app_covid_19;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorPerfis extends RecyclerView.Adapter<AdaptadorPerfis.ViewHolderPerfis> {
    private Context context;
    private Cursor cursor = null;

    public void setCursor(Cursor cursor){
        if(cursor != this.cursor){
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }

    public AdaptadorPerfis(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderPerfis onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemPerfil = LayoutInflater.from(context).inflate(R.layout.item_perfil, parent, false);

        return new ViewHolderPerfis(itemPerfil);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPerfis holder, int position) {
        cursor.moveToPosition(position);
        Perfil perfil = Converte.cursorParaPerfil(cursor);
        holder.setPerfil(perfil);

    }

    @Override
    public int getItemCount() {
        if(cursor == null){
            return 0;
        }
        return cursor.getCount();
    }

    private ViewHolderPerfis viewHolderPerfilSelecionado = null;


    public class ViewHolderPerfis extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Perfil perfil = null;
        private final TextView textViewNomeItemPerfil;
        private final TextView textViewDataNascimentoItemPerfil;



        public ViewHolderPerfis(@NonNull View itemView) {
            super(itemView);

            textViewNomeItemPerfil = (TextView)itemView.findViewById(R.id.textViewNomeItemPerfil);
            textViewDataNascimentoItemPerfil = (TextView)itemView.findViewById(R.id.textViewDataNascimentoItemPerfil);

            itemView.setOnClickListener(this);

        }

        public void setPerfil(Perfil perfil) {
            this.perfil = perfil;
            textViewNomeItemPerfil.setText(perfil.getNome());
            textViewDataNascimentoItemPerfil.setText(perfil.getDataNascimento());
        }

        @Override
        public void onClick(View v) {
            if(viewHolderPerfilSelecionado == this){
                return;
            }

            if(viewHolderPerfilSelecionado != null){
                viewHolderPerfilSelecionado.desSeleciona();
            }
            viewHolderPerfilSelecionado = this;
            seleciona();

            MainActivity activity = (MainActivity) AdaptadorPerfis.this.context;
            activity.perfilAlterado(perfil);

        }

        private void seleciona() {
            itemView.setBackgroundResource(R.color.colorPrimary);
        }

        private void desSeleciona() {
        itemView.setBackgroundResource(android.R.color.white);
        }
    }
}
