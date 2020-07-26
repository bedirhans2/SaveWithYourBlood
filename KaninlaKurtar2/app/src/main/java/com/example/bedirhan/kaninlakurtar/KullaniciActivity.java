package com.example.bedirhan.kaninlakurtar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class KullaniciActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici);


    }

    //Menü Bar tanımlandı
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.menu_user,menu);
        return true;
    }

    //Menüdeki tüm öğeler buton olarak işlevlendirildi. Böylece fiziksel bir buton kullanımına gerek kalmadı
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.menu_ilanver){
            Intent ilanver1 = new Intent(KullaniciActivity.this,IlanverActivity.class);
            startActivity(ilanver1);
        }
        if(id==R.id.menu_ilanlarim){
            Intent ilanlarim = new Intent(KullaniciActivity.this,IlanlarimActivity.class);
            startActivity(ilanlarim);
        }
        if(id==R.id.menu_ilangor){
            Intent ilangor = new Intent(KullaniciActivity.this,IlangorActivity.class);
            startActivity(ilangor);
        }
        if(id==R.id.menu_update){
            Intent update = new Intent(KullaniciActivity.this,UpdateActivity.class);
            startActivity(update);

        }
        if(id==R.id.menu_logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(KullaniciActivity.this,MainActivity.class));
            finish();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
