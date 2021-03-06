package com.example.app_covid_19;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorRegistos extends RecyclerView.Adapter<AdaptadorRegistos.ViewHolderRegisto> {
    private Context context;

    private Cursor cursor = null;

    public void setCursor(Cursor cursor){
        if(cursor != this.cursor){
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }

    public AdaptadorRegistos(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderRegisto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRegisto = LayoutInflater.from(context).inflate(R.layout.item_registo, parent, false);

        return new ViewHolderRegisto(itemRegisto);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRegisto holder, int position) {
        cursor.moveToPosition(position);
        Registo registo = Converte.cursorParaRegisto(cursor);
        holder.setRegisto(registo);
    }

    @Override
    public int getItemCount() {
        if(cursor == null){
            return 0;
        }
        return cursor.getCount();
    }

    private ViewHolderRegisto viewHolderRegistoSelecionado = null;

    public class ViewHolderRegisto extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Registo registo = null;

        private final TextView textViewNomeItemRegisto;
        private final TextView textViewDataRegistoItemRegisto;
        private final TextView textViewTemperaturaItemRegisto;
        private final TextView textViewTosse;
        private final TextView textViewDifResp;


        public ViewHolderRegisto(@NonNull View itemView) {
            super(itemView);

            textViewNomeItemRegisto = (TextView)itemView.findViewById(R.id.textViewNomeItemRegisto);
            textViewDataRegistoItemRegisto = (TextView)itemView.findViewById(R.id.textViewDataRegistoItemRegisto);
            textViewTemperaturaItemRegisto = (TextView)itemView.findViewById(R.id.textViewTemperaturaItemRegisto);
            textViewTosse = (TextView)itemView.findViewById(R.id.textViewTosse);

            textViewDifResp =(TextView)itemView.findViewById(R.id.textViewDifResp);


            itemView.setOnClickListener(this);
        }

        public void setRegisto(Registo registo) {
            this.registo = registo;
            textViewNomeItemRegisto.setText(registo.getPerfil());
            textViewDataRegistoItemRegisto.setText(registo.getDataRegisto());
            textViewTemperaturaItemRegisto.setText(String.valueOf(registo.getTemperatura()));
            if(registo.getTosse() == 1) {
                textViewTosse.setText(R.string.tosse);
                textViewTosse.setVisibility(View.VISIBLE);
            } else{
                textViewTosse.setVisibility(View.GONE);
            }
            if(registo.getDifResp() == 1){
                textViewDifResp.setText(R.string.dificuldade_respiratoria);
                textViewDifResp.setVisibility(View.VISIBLE);
            }else{
                textViewDifResp.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            if(viewHolderRegistoSelecionado == this){
                return;
            }

            if(viewHolderRegistoSelecionado != null){
                viewHolderRegistoSelecionado.desSeleciona();
            }
            viewHolderRegistoSelecionado = this;
            seleciona();

            MainActivity activity = (MainActivity) AdaptadorRegistos.this.context;
            activity.registoAlterado(registo);

        }

        private void seleciona() {
            itemView.setBackgroundResource(R.color.colorItemSelected);
        }

        private void desSeleciona() {
            itemView.setBackgroundResource(R.color.fundos);
        }
    }
}
