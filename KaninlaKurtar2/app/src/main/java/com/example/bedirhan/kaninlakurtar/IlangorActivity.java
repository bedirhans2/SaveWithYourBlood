package com.example.bedirhan.kaninlakurtar;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bedirhan.kaninlakurtar.Adapters.LeadsAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class IlangorActivity extends AppCompatActivity {
RecyclerView rv_leads;
LeadsAdapter leadsAdapter;
    ArrayList<Lead> leads = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilangor);
        rv_leads = findViewById(R.id.rv_leads);
        rv_leads.setLayoutManager(new LinearLayoutManager(this));
        leadsAdapter = new LeadsAdapter(this , leads);
        rv_leads.setAdapter(leadsAdapter);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("İlanlar");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("DataGet", dataSnapshot.toString());
                Log.e("DataGet", dataSnapshot.getRef().toString());
                Log.e("DataGet", dataSnapshot.getValue().toString());
                Log.e("DataGet", dataSnapshot.getChildren().toString());
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.e("DataDS", ds.getValue().toString());
                    Lead lead = new Lead();
                    String İsimSoyisim = ds.child("ilanVeren").getValue().toString();
                    String hastane = ds.child("hastane").getValue().toString();
                    String Detaylar = ds.child("detay").getValue().toString();
                    String Telefon = ds.child("iletişim").getValue().toString();
                    String il = ds.child("il").getValue().toString();
                    Log.e("ilanVeren ", İsimSoyisim);
                    Log.e("hastane ", hastane);
                    Log.e("Detaylar ", Detaylar);
                    Log.e("Telefon ", Telefon);
                    Log.e("Hastane ", il);
                    lead.setDetaylar(Detaylar);
                    lead.setHastane(hastane);
                    lead.setIsimSoyisim(İsimSoyisim);
                    lead.setTelefon(Telefon);
                    lead.setIl(il);
                    leads.add(lead);
                }
               leadsAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
