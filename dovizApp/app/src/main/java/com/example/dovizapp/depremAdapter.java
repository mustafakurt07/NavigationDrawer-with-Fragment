package com.example.dovizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class depremAdapter extends RecyclerView.Adapter< depremAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<depremModel> userModelArrayList;

    public  depremAdapter(Context context, ArrayList<depremModel> userModelArrayList){
        this.context = context;
        this.userModelArrayList = userModelArrayList;

    }

    @NonNull
    @Override
    public  depremAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_card_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull depremAdapter.MyViewHolder holder, int position) {
        depremModel user = userModelArrayList.get(position);
        holder.yer.setText(user.getYer());
        holder.tarih.setText("Tarih  :"+user.getTarih());
        holder.saat.setText("Saat :"+user.getSaat());
        holder.siddet.setText("Şiddet :"+user.getSiddet());
        holder.derinlik.setText("Derinlik:"+user.getDerinlik()+"km");
        holder.tur.setText("Türü:"+user.getTur());

    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView yer,tarih,saat,derinlik,siddet,tur;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            yer =(TextView) itemView.findViewById(R.id.yer);
            tarih= (TextView) itemView.findViewById(R.id.tarih);
            saat=itemView.findViewById(R.id.saat);
            siddet=itemView.findViewById(R.id.siddet);
            derinlik=itemView.findViewById(R.id.derinlik);
            tur=itemView.findViewById(R.id.tur);
        }
    }
}

