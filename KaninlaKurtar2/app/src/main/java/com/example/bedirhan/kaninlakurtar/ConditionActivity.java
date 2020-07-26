package com.example.bedirhan.kaninlakurtar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class ConditionActivity extends AppCompatActivity {
RadioButton rb_evet1,rb_evet2,rb_evet3,rb_evet4,rb_hayir1,rb_hayir2,rb_hayir3,rb_hayir4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition);
        Button btn_okey = (Button)findViewById(R.id.btn_okey);
        rb_evet1=(RadioButton)findViewById(R.id.rb_evet1);
        rb_evet2=(RadioButton)findViewById(R.id.rb_evet2);
        rb_evet4=(RadioButton)findViewById(R.id.rb_evet4);
        rb_evet3=(RadioButton)findViewById(R.id.rb_evet3);
        rb_hayir2=(RadioButton)findViewById(R.id.rb_hayir2);
        rb_hayir1=(RadioButton)findViewById(R.id.rb_hayir1);
        rb_hayir3=(RadioButton)findViewById(R.id.rb_hayir3);
        rb_hayir4=(RadioButton)findViewById(R.id.rb_hayir4);
        Button btn_anamenu2 = (Button)findViewById(R.id.btn_anamenu2);

        btn_okey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_evet1.isChecked() || rb_evet2.isChecked() || rb_evet3.isChecked() || rb_evet4.isChecked()){
                    Toast.makeText(ConditionActivity.this,"Siz kan veya kan ürünü bağışı yapamazsınız.",Toast.LENGTH_SHORT).show();
                    Intent anamenu232 = new Intent(ConditionActivity.this,KullaniciActivity.class);
                    startActivity(anamenu232);
                }
                else if(rb_hayir1.isChecked() && rb_hayir2.isChecked() && rb_hayir3.isChecked() && rb_hayir4.isChecked()){
                    Toast.makeText(ConditionActivity.this,"Siz kan veya kan ürünü bağışı yapabilirsiniz.",Toast.LENGTH_SHORT).show();
                    Intent ilanson = new Intent(ConditionActivity.this,IlanSonActivity.class);
                    startActivity(ilanson);

                }
                else{
                    Toast.makeText(ConditionActivity.this,"Lütfen soruları cevaplayınız.",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btn_anamenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent anamenu234 = new Intent(ConditionActivity.this,KullaniciActivity.class);
                startActivity(anamenu234);
            }
        });
    }
}
