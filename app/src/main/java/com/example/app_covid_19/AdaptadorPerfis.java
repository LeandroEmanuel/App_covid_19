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
        private final TextView textViewDoencasCardioVasculares;
        private final TextView textViewDiabetes;
        private final TextView textViewHipertensao;
        private final TextView textViewOncologica;
        private final TextView textViewRespiratoria;


        public ViewHolderPerfis(@NonNull View itemView) {
            super(itemView);

            textViewNomeItemPerfil = (TextView)itemView.findViewById(R.id.textViewNomeItemPerfil);
            textViewDataNascimentoItemPerfil = (TextView)itemView.findViewById(R.id.textViewDataNascimentoItemPerfil);
            textViewDoencasCardioVasculares = (TextView) itemView.findViewById(R.id.textViewDoencasCardioVasculares);
            textViewDiabetes = (TextView) itemView.findViewById(R.id.textViewDiabetes);
            textViewHipertensao = (TextView) itemView.findViewById(R.id.textViewHipertensao);
            textViewOncologica = (TextView) itemView.findViewById(R.id.textViewOncologica);
            textViewRespiratoria =(TextView) itemView.findViewById(R.id.textViewRespiratoria);
            itemView.setOnClickListener(this);

        }

        public void setPerfil(Perfil perfil) {
            this.perfil = perfil;
            textViewNomeItemPerfil.setText(perfil.getNome());
            textViewDataNascimentoItemPerfil.setText(perfil.getDataNascimento());
            if(perfil.getCardio() == 1) {
                textViewDoencasCardioVasculares.setText(R.string.doenca_cardiovascular);
                textViewDoencasCardioVasculares.setVisibility(View.VISIBLE);
            } else{
                textViewDoencasCardioVasculares.setVisibility(View.GONE);
            }
            if(perfil.getDiabetes() == 1){
                textViewDiabetes.setText(R.string.diabetes);
                textViewDiabetes.setVisibility(View.VISIBLE);
            }else{
                textViewDiabetes.setVisibility(View.GONE);
            }
            if(perfil.getHiper() == 1){
                textViewHipertensao.setText(R.string.hipertensao);
                textViewHipertensao.setVisibility(View.VISIBLE);
            }else{
                textViewHipertensao.setVisibility(View.GONE);
            }
            if(perfil.getOnco() == 1){
                textViewOncologica.setText(R.string.doenca_oncologica);
                textViewOncologica.setVisibility(View.VISIBLE);
            }else{
                textViewOncologica.setVisibility(View.GONE);
            }
            if(perfil.getResp() == 1){
                textViewRespiratoria.setText(R.string.doenca_respiratoria);
                textViewRespiratoria.setVisibility(View.VISIBLE);
            }else{
                textViewRespiratoria.setVisibility(View.GONE);
            }
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
            itemView.setBackgroundResource(R.color.colorSelected);


        }

        private void desSeleciona() {
        itemView.setBackgroundResource(android.R.color.white);
        }
    }
}
