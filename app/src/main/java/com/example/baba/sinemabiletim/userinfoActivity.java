package com.example.baba.sinemabiletim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.baba.sinemabiletim.Test.Test;

public class userinfoActivity extends AppCompatActivity {

    EditText phoneInfo, nameInfo, pwInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);


        /*
            We filled the editTexts with the current user's infos.We filled it via Kullanici class.
        */

        /*Giriş yapan kullanıcı başarılıysa onu bir Kullanıcı classı kullarak tutmuştum.Classı kullanarak bu aktivitedeki edittextleri doldurdum.
          Her giriş yapıldığında atama yapıldığı için bir önceki kullanıcının bilgilerini göstermeyeceğinden veritabanından çekmedim.
        */



        phoneInfo = (EditText)findViewById(R.id.edittelbilgi);
        nameInfo = (EditText)findViewById(R.id.editisimbilgi);
        pwInfo = (EditText)findViewById(R.id.editsifrebilgi);



        phoneInfo.setText(Test.aTel);
        nameInfo.setText(Test.aKul.getIsim());
        pwInfo.setText(Test.aKul.getSifre());


        phoneInfo.setKeyListener(null);
        nameInfo.setKeyListener(null);
        pwInfo.setKeyListener(null);

    }


    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(getBaseContext(), signedinmenuActivity.class));
        finish();
    }
}
