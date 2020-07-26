package com.example.bedirhan.kaninlakurtar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        }

    //Menü Bar tanımlandı
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.main_menu,menu);
        return true;
    }

    //Menüdeki tüm öğeler buton olarak işlevlendirildi. Böylece fiziksel bir buton kullanımına gerek kalmadı
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.menu_login){
            Intent ders = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(ders);
        }
        if(id==R.id.menu_register){
            Intent video = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(video);
        }
        if(id==R.id.menu_about){
            Intent about = new Intent(MainActivity.this,AboutActivity.class);
            startActivity(about);
        }
        if(id==R.id.menu_exit){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
