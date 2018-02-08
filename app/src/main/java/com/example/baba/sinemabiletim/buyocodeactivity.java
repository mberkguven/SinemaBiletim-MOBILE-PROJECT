package com.example.baba.sinemabiletim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baba.sinemabiletim.Test.Test;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static org.bitbucket.dollar.Dollar.*;

// This one is buying cinema code only.

public class buyocodeactivity extends AppCompatActivity {

    String telno;
    EditText biletsayisi;
    Button sadecebiletbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyocode);


        // vb bağlantısı falan filan

        final FirebaseDatabase veritabani = FirebaseDatabase.getInstance();
        final DatabaseReference biletkodu_tablosu = veritabani.getReference("BiletKod");

        sadecebiletbtn = (Button)findViewById(R.id.btnsdcbilet);
        biletsayisi = (EditText)findViewById(R.id.editbiletsayisadece);



        telno = Test.aTel;

        // 8 haneli random kod üretiyorum.
        final String randombilet = randomString(8);





        sadecebiletbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                biletkodu_tablosu.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Kişinin telefonuna karşılık gelen yer var mı diye bakıyorum.
                        if (dataSnapshot.child(telno).exists()){

                                // Varsa edittext boş mu değilmi ona bakıyorum.
                            if (biletsayisi.getText().toString().trim().length() > 0)
                                {
                                    //Random biletin son halinin oluştuğu yer.TAG+kisisayisi+randombilet kodu.
                                    String olusanbiletkodu = "BLT"+biletsayisi.getText().toString()+"-"+randombilet;
                                    biletkodu_tablosu.child(telno).child("biletkod").setValue(olusanbiletkodu);
                                    Toast.makeText(buyocodeactivity.this,"KODUNUZ SİPARİŞLERE EKLENMİŞTİR!", Toast.LENGTH_SHORT).show();



                                    Intent intent=new Intent(buyocodeactivity.this,signedinmenuActivity.class);
                                    intent.addCategory(Intent.CATEGORY_HOME);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }
                            else
                                {
                                Toast.makeText(buyocodeactivity.this,"BİLET SAYISI BOŞ BIRAKILAMAZ", Toast.LENGTH_SHORT).show();
                                }

                        }
                        else
                            {
                            Toast.makeText(buyocodeactivity.this,"VERİTABANI HATASI OLUŞTU!", Toast.LENGTH_SHORT).show();
                            Intent anaekran = new Intent(buyocodeactivity.this, signedinmenuActivity.class );
                            startActivity(anaekran);
                            finish();
                            }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });


    }




    // Random bilet üretmek için kullandığım fonksiyon.

String validCharacters = $('0', '9').join() + $('A', 'Z').join();

    String randomString(int length) {
        return $(validCharacters).shuffle().slice(length).toString();
    }



    private void kapat() {
        finish();
    }



    public void onBackPressed()
    {
        super.onBackPressed();
        Intent anaekran = new Intent(buyocodeactivity.this, signedinmenuActivity.class );
        startActivity(anaekran);
        kapat();
    }


}
