package com.example.mohammad.tahminoyunu;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SorularActivity extends AppCompatActivity {

    private TextView      soru, zaman, puan;
    private String[]      sorular;
    private int[]         cevaplar, resimler;
    private Button        up, down, pas;
    private ImageView     reim;
    private int           dogruSayisi=0, clikKontrol, sn=60, soruKontrol=0;
    private MediaPlayer   alkis, aaa, son3sn, gecis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorular);

        soru= (TextView) findViewById(R.id.soru);
        puan= (TextView) findViewById(R.id.puan);
        zaman = (TextView) findViewById(R.id.zaman);
        up= (Button) findViewById(R.id.yukari);
        down= (Button) findViewById(R.id.asagi);
        pas= (Button) findViewById(R.id.pas);
        reim= (ImageView) findViewById(R.id.resim);

        son3sn= MediaPlayer.create(SorularActivity.this, R.raw.gerilim);
        alkis= MediaPlayer.create(SorularActivity.this, R.raw.alkis);
        aaa= MediaPlayer.create(SorularActivity.this, R.raw.yanlis);
        gecis= MediaPlayer.create(SorularActivity.this, R.raw.gecis);

        sorular= new String[]{
                "En yüksek banknotu 500 milyar dolardır.",
                "3+9.(2/8)= 44",
                "Fatih Sultan Mehmet İstanbulu fethettiğinde 21 yaşındaydı",
                "Eyfel kulesi 200 m dir",
                "Güneş ışıkları dünyaya 8.25 dk de ulaşır",
                "Bir kilo üzümde kaç adet üzüm tanesi 280 vardır.",
                "Tüm zamanların en uzun otobüsü 30 m dir.",
                "En uzun yaşayan japon balığı 67 yaşındadır.",
                "En ağır sumocu 250 kilodur.",
                "Satranç tahtasında 69 kare vardır.",
                "Matamatikçilere göre kıravat 177 farklı şekilde bağlanabilir.",
                "Uzunluğunun 1 cm olması için yan yana 96 saç teli koymak yeterlidir.",
                "Charles Osborne isimli bir adamın hıkırığı 70 yıl sürmüştür.",
                "TKD sözlüğüne göre \"çıkmak\" sözcüğünün 25 anlamı vardır.",
                "20 elma var 4 dünü aldım 15 elmam olur.",
                "Guguklu saati sabah 9.40 e kurup 8.00 da yatan birisi 11 saat uyur.",
        };
        resimler = new int[]{
                R.drawable.s0,
                R.drawable.s1,
                R.drawable.s2,
                R.drawable.s3,
                R.drawable.s4,
                R.drawable.s5,
                R.drawable.s6,
                R.drawable.s7,
                R.drawable.s8,
                R.drawable.s9,
                R.drawable.s10,
                R.drawable.s11,
                R.drawable.s12,
                R.drawable.s13,
                R.drawable.s14,
                R.drawable.s15,
        };
        // 1 yukarı 0 aşağı
        cevaplar= new int[]{
                1,1,0,1,0,0,1,0,1,1,1,1,0,1,0,0
        };
        //Geri Sayım
        new CountDownTimer(60000,1000) {
            public void onTick(long millisUntilFinished) {
                zaman.setText(" " + millisUntilFinished/1000);
                sn--;
                // soru
                soru.setText(" "+sorular[soruKontrol]);
                reim.setImageResource(resimler[soruKontrol]);
                // cevaplar
                up.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        if(clikKontrol==0) {
                            if (cevaplar[soruKontrol] == 1) {
                                dogruSayisi += 1;
                                puan.setText("puanınız : "+5*dogruSayisi);
                                alkis.start();
                            } else {
                                aaa.start();
                            }
                            clikKontrol = 1;
                        }
                    }
                });
                down.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        if(clikKontrol==0) {
                            if (cevaplar[soruKontrol] == 0) {
                                dogruSayisi += 1;
                                puan.setText("puanınız : "+5*dogruSayisi);
                                alkis.start();
                            } else {
                               aaa.start();
                            }
                            clikKontrol = 1;
                        }
                    }
                });
                // alternatif clikler
                pas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(clikKontrol==0) {
                            clikKontrol = 1;
                            gecis.start();
                        }
                    }
                });
                if (sn==3){son3sn.start();}
                // yeni soru
                // cevap verdikten yarım sn sonra komut çalışsın
                if(clikKontrol== 1){
                    soruKontrol+= 1;
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    clikKontrol= 0;
                                }
                            }, 500);
                    if (soruKontrol == sorular.length){
                        gecis.start();
                        Toast.makeText(SorularActivity.this,"oyun bitti...puanınız : "+dogruSayisi*5,Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SorularActivity.this,GirisActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
            public void onFinish(){
                gecis.start();
                Toast.makeText(SorularActivity.this,"oyun bitti...puanınız : "+dogruSayisi*5,Toast.LENGTH_LONG).show();
                Intent i = new Intent(SorularActivity.this,GirisActivity.class);
                startActivity(i);
                finish();
            }
        }.start();
    }
}
