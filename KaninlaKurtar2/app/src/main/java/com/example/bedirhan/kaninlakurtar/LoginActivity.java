package com.example.bedirhan.kaninlakurtar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Firebase Libraries
import com.example.bedirhan.kaninlakurtar.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText email,pass;
    Button btn_giris ;

    //Yetkilendirme tanımlama
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //yetkilendirilmiş kullanıcı ekleme
        auth =FirebaseAuth.getInstance();

        email =findViewById(R.id.email);
        pass =findViewById(R.id.pass);
        btn_giris =findViewById(R.id.btn_giris);
        btn_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //E mail ve Parola ile giriş ve yetkilendirilmiş kullanıcı girişi
                String txt_EMail =email.getText().toString();
                String txt_Pass =pass.getText().toString();
                if(TextUtils.isEmpty(txt_EMail)||TextUtils.isEmpty(txt_Pass)){
                    Toast.makeText(LoginActivity.this,"Tüm Alanları Doldurunuz",Toast.LENGTH_SHORT).show();
                }else {
                    auth.signInWithEmailAndPassword(txt_EMail,txt_Pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent intentt = new Intent(LoginActivity.this,KullaniciActivity.class);
                                        startActivity(intentt);
                                    }else {
                                        Toast.makeText(LoginActivity.this,"Giriş Hatası",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        Button btn_geri2 =(Button)findViewById(R.id.btn_geri2);
        btn_geri2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent geri2 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(geri2);
            }
        });

    }
}
