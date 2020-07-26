package com.example.bedirhan.kaninlakurtar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IlanlarimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlarim);

        Button btn_ilgeri = (Button)findViewById(R.id.btn_ilgeri);
        btn_ilgeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent geri3459 = new Intent(IlanlarimActivity.this,KullaniciActivity.class);
                startActivity(geri3459);
            }
        });
    }
}
