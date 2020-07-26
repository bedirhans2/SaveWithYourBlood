package com.example.bedirhan.kaninlakurtar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Button btn_mainact = (Button)findViewById(R.id.btn_mainact);
        btn_mainact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainact = new Intent(AboutActivity.this,MainActivity.class);
                startActivity(mainact);
            }
        });
    }
}
