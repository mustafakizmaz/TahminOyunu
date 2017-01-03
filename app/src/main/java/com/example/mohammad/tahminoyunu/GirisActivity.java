package com.example.mohammad.tahminoyunu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GirisActivity extends AppCompatActivity {


    private Button yeni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        yeni=(Button) findViewById(R.id.yenioyun);
        yeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(GirisActivity.this,SorularActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
