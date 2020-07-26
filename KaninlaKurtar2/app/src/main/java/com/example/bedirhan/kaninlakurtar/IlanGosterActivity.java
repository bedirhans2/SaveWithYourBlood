package com.example.bedirhan.kaninlakurtar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class IlanGosterActivity extends AppCompatActivity {
Button btn_varim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_goster);
        btn_varim=(Button) findViewById(R.id.btn_varim);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("İlanlar");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("DataGet" , dataSnapshot.toString());
                Log.e("DataGet" , dataSnapshot.getRef().toString());
                Log.e("DataGet" , dataSnapshot.getValue().toString());
                Log.e("DataGet" , dataSnapshot.getChildren().toString());
                ArrayList<Lead> leads = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                 Log.e("DataDS" , ds.getValue().toString());
                 Lead lead = new Lead();
                   String İsimSoyisim = ds.child("ilanVeren").getValue().toString();
                   String hastane = ds.child("hastane").getValue().toString();
                   String Detaylar = ds.child("detay").getValue().toString();
                   String Telefon = ds.child("iletişim").getValue().toString();
                   String il = ds.child("il").getValue().toString();
                   Log.e("ilanVeren " , İsimSoyisim);
                   Log.e("hastane " , hastane);
                   Log.e("Detaylar " , Detaylar);
                   Log.e("Telefon " , Telefon);
                   Log.e("Hastane " , il);
                    lead.setDetaylar(Detaylar);
                    lead.setHastane(hastane);
                    lead.setIsimSoyisim(İsimSoyisim);
                    lead.setTelefon(Telefon);
                    lead.setIl(il);
                    leads.add(lead);
                }
                ((TextView)findViewById(R.id.Name)).setText(leads.get((leads.size()-1)).getIsimSoyisim());
                ((TextView)findViewById(R.id.Sehir)).setText(leads.get((leads.size()-1)).getIl());
                ((TextView)findViewById(R.id.Hastane)).setText(leads.get((leads.size()-1)).getHastane());
                ((TextView)findViewById(R.id.Tel)).setText(leads.get((leads.size()-1)).getTelefon());
                ((TextView)findViewById(R.id.Detay)).setText(leads.get((leads.size()-1)).getDetaylar());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });










        btn_varim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sorgu = new Intent(IlanGosterActivity.this,ConditionActivity.class);
                startActivity(sorgu);
            }
        });

    }
}
