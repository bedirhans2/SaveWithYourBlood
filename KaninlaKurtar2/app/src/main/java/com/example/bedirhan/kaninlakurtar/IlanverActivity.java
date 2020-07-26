package com.example.bedirhan.kaninlakurtar;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class IlanverActivity extends AppCompatActivity {
    DatabaseReference reff;
    EditText Name, Sehir, Hastane, Tel, Detay;
    Button btn_ilanver, btn_canc2;
    İlanlar ilan; // ilan ekleme işini yaptığımız nesne

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanver);
        Name = (EditText) findViewById(R.id.Name);
        Sehir = (EditText) findViewById(R.id.Sehir);
        Hastane = (EditText) findViewById(R.id.Hastane);
        Tel = (EditText) findViewById(R.id.Tel);
        Detay = (EditText) findViewById(R.id.Detay);
        btn_canc2 = (Button) findViewById(R.id.btn_canc2);
        btn_ilanver = (Button) findViewById(R.id.btn_ilanver);
        ilan = new İlanlar();

        //reff bir referans nesnesi ve bu nesne sayesinde veritabanımızda "İlanlar" adlı tabloyu oluştururuz.
        reff = FirebaseDatabase.getInstance().getReference().child("İlanlar");

        btn_ilanver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //edit textlerden girilen veriyi ilanlar adlı java sınıfında tanımlanmış değişkenlere setter yoluyla atama yapar. Burası aynı zamanda ilanlar adlı tablodaki sütunları da tanımlar.
                ilan.setİlanVeren(Name.getText().toString().trim());
                ilan.setDetay(Detay.getText().toString().trim());
                ilan.setİl(Sehir.getText().toString().trim());
                ilan.setHastane(Hastane.getText().toString().trim());
                ilan.setİletişim(Tel.getText().toString().trim());
                //ilan classına set edilen veriyi reff ile tanımlanmış olan veritabanındaki ilanlar adlı tabloya ekler.
                reff.push().setValue(ilan);
                try {
                    sendNotification();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(IlanverActivity.this, "İlan Başarıyla Verildi", Toast.LENGTH_SHORT).show();
                Intent intentty = new Intent(IlanverActivity.this, KullaniciActivity.class);
                startActivity(intentty);
            }
        });
        btn_canc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kullanicigeri = new Intent(IlanverActivity.this, KullaniciActivity.class);
                startActivity(kullanicigeri);
            }
        });
    }

    private void sendNotification() throws JSONException {
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject data = new JSONObject();
        data.put("to", "/topics/all");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", getString(R.string.fcm_message));
        jsonObject.put("message", getString(R.string.fcm_message2));
        jsonObject.put("body", getString(R.string.fcm_message2));
        data.put("data", jsonObject);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, "https://fcm.googleapis.com/fcm/send", data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Res" , response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String , String> params = new HashMap<>();
                params.put("Authorization" , "key=AIzaSyDv70tc_P1j2TnRWXioMXo-cv1a2Kki4hk");
                return params;
            }
        };
        requestQueue.add(stringRequest);
//        Intent intent = new Intent(this, IlanGosterActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        String channelId = getString(R.string.default_notification_channel_id);
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder =
//                new NotificationCompat.Builder(this, channelId)
//                        .setSmallIcon(R.drawable.logo)
//                        .setContentTitle(getString(R.string.fcm_message))
//                        .setContentText(getString(R.string.fcm_message2))
//                        .setAutoCancel(true)
//                        .setSound(defaultSoundUri)
//                        .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        // Since android Oreo notification channel is needed.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channelId,
//                    "Channel human readable title",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}