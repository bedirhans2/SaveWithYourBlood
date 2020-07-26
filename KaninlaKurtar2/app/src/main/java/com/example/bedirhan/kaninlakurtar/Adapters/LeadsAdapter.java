package com.example.bedirhan.kaninlakurtar.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.bedirhan.kaninlakurtar.Lead;
import com.example.bedirhan.kaninlakurtar.R;

import java.util.ArrayList;

public class LeadsAdapter extends RecyclerView.Adapter<LeadsAdapter.ViewHolder> {
    Context context;
    ArrayList<Lead> uri_list;

    public LeadsAdapter(Context context, ArrayList<Lead> uri_list) {
        this.context = context;
        this.uri_list = uri_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lead, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.e("ImgInAdapter", uri_list.get(position).toString());
        holder.Detay.setText(uri_list.get(position).getDetaylar());
        holder.Name.setText(uri_list.get(position).getIsimSoyisim());
        holder.Sehir.setText(uri_list.get(position).getIl());
        holder.Tel.setText(uri_list.get(position).getTelefon());
        holder.Hastane.setText(uri_list.get(position).getHastane());
        holder.btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", uri_list.get((position)).getTelefon(), null));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return uri_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name, Sehir, Hastane, Tel, Detay;
        Button btn_call;

        public ViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Name);
            Sehir = itemView.findViewById(R.id.Sehir);
            Hastane = itemView.findViewById(R.id.Hastane);
            Tel = itemView.findViewById(R.id.Tel);
            Detay = itemView.findViewById(R.id.Detay);
            btn_call = itemView.findViewById(R.id.btn_call);


        }

    }
}