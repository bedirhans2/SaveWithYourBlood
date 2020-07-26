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


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;


public  class RegisterActivity extends AppCompatActivity {

    EditText Tc,Ad,Sad,Tel,İl,Kan,Mail,Pass;
    Button btn_reg;
    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        Tc =findViewById(R.id.Tc);
        Ad =findViewById(R.id.Ad);
        Sad =findViewById(R.id.Sad);
        Tel =findViewById(R.id.Tel);
        İl =findViewById(R.id.İl);
        Kan =findViewById(R.id.Kan);
        Mail =findViewById(R.id.Mail);
        Pass =findViewById(R.id.Pass);
        btn_reg =findViewById(R.id.btn_reg);
        auth = FirebaseAuth.getInstance();

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_Tc= Tc.getText().toString();
                String txt_Ad= Ad.getText().toString();
                String txt_Sad= Sad.getText().toString();
                String txt_Tel= Tel.getText().toString();
                String txt_İl= İl.getText().toString();
                String txt_Kan= Kan.getText().toString();
                String txt_Mail= Mail.getText().toString();
                String txt_Pass= Pass.getText().toString();

                if(TextUtils.isEmpty(txt_Tc)&&TextUtils.isEmpty(txt_Ad)&&TextUtils.isEmpty(txt_Sad)&&TextUtils.isEmpty(txt_Tel)&&TextUtils.isEmpty(txt_İl)&&TextUtils.isEmpty(txt_Kan)&&TextUtils.isEmpty(txt_Mail)&&TextUtils.isEmpty(txt_Pass)){
                    Toast.makeText(getApplicationContext(),"Boş alanları doldur", Toast.LENGTH_SHORT).show();
                }else if (txt_Pass.length()<6)
                {
                    Toast.makeText(getApplicationContext(),"Parola en az 6 karakter olmalı!", Toast.LENGTH_SHORT).show();
                }else {
                    register(txt_Tc,txt_Ad,txt_Sad,txt_Tel,txt_İl,txt_Kan,txt_Mail,txt_Pass);
                    Toast.makeText(getApplicationContext(),"Üyelik işleminiz başarıyla gerçekleşti", Toast.LENGTH_SHORT).show();
                    Intent gerri22 = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(gerri22);
                }
            }
        });




        //Vazgeç butonnuna basıldığında, kayıt işleminden vazgeçilerek anasayfaya dönülür.
        Button btn_canc = (Button)findViewById(R.id.btn_canc);
        btn_canc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gerri = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(gerri);
            }
        });




    }
    //Veritabanına kullanıcı bilgilerinin eklenmesi
    private  void register(final String Tc, final String Ad, final String Sad, final String Tel, final String İl, final String Kan, final String Mail, final String Pass){
        auth.createUserWithEmailAndPassword(Mail,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    assert firebaseUser !=null;
                    String userid =firebaseUser.getUid();
                    reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("Id: ", userid);
                    hashMap.put("Tc: ", Tc);
                    hashMap.put("Ad: ", Ad);
                    hashMap.put("Soyad: ", Sad);
                    hashMap.put("Telefon: ", Tel);
                    hashMap.put("İli : ", İl);
                    hashMap.put("Kan Grubu: ", Kan);
                    hashMap.put("EMail: ", Mail);
                    hashMap.put("Parola: ", Pass);

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent baslangic = new Intent(RegisterActivity.this,MainActivity.class);
                                Toast.makeText(getApplicationContext(),"Kayıt işleminiz başarıyla gerçekleşti", Toast.LENGTH_SHORT).show();
                                startActivity(baslangic);
                            }
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(),"Kayıt Başarısız, Yeniden dene", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }



}
