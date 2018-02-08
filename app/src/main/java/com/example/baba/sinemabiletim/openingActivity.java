package com.example.baba.sinemabiletim;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
  This is a project that I did for my Mobile Programming class as homework.
  10.11.2017  Homework.
  What does this project do ?
  -In Turkey,you can buy  invitation codes,which is cheaper than buying ticket from box-office.
  People sell codes via WhatsApp,you have to wait him/her to answer you and he has to confirm ur payment bla bla bla long story.
  This app is for buying invitation codes with just a click and you are ready to go !
  There is no payment gateaway it is very simple app that I just did it for my homework.It uses Firebase as database.

  -There is some Turkish notes in the project but I also added some English ones to help you guys.


  Mobil Programlama dersi için yaptığım bir projedir.
  Sinema davetiye kodu satın almak için tasarladım.Ülkemizde zaten bunu WhatsApp üzerinden yapan birçok kişi var fakat
  onların ödemeyi onaylaması,sizin mesajını görmesi cart curt beklemeye gerek yok.Bunun yerine bir app olsa gişeden ucuza kodumuzu alsak ya?
  İşte bu app onun için yapıldı.Payment gateaway yok basit bir program.Firebase veritabani kullanıyor.

 */




public class openingActivity extends AppCompatActivity {

    Button btnSignin, btnSignup;
    TextView txtSlogan;
    String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);


        btnSignin = (Button)findViewById(R.id.btngiris);
        btnSignup = (Button)findViewById(R.id.btnkayit);
        txtSlogan = (TextView)findViewById(R.id.slogantxt);


        //Custom font stuff.
        //Burada textview fontunu kendi eklediğim font ile değiştiriyorum.

        Typeface newFont = Typeface.createFromAsset(getAssets(),"font/NABILA.TTF");
        txtSlogan.setTypeface(newFont);


        // Button click events.
        // Butonlara tıklandığında yapıcakları şeyler..

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signinActivity = new Intent(openingActivity.this, signinActivity.class);
                startActivity(signinActivity);
                finish();

            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signupActivity = new Intent(openingActivity.this, signupActivity.class);
                startActivity(signupActivity);

            }
        });
    }
}
