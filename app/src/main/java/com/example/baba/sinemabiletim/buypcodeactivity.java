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

import static org.bitbucket.dollar.Dollar.$;

// This one is buying code with popcorn promation.

public class buypcodeactivity extends AppCompatActivity {


    String telno;
    EditText biletsayisi;
    Button misirbuton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buypcode);


        misirbuton = (Button)findViewById(R.id.btnmisir);


        final FirebaseDatabase veritabani = FirebaseDatabase.getInstance();
        final DatabaseReference biletkodu_tablosu = veritabani.getReference("BiletKod");

        biletsayisi = (EditText)findViewById(R.id.editbiletsayimisir);


        telno = Test.aTel;

        final String randombilet = randomString(8);




        misirbuton.setOnClickListener(new View.OnClickListener() {
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
                                String olusanbiletkodu = "MSR"+biletsayisi.getText().toString()+"-"+randombilet;

                                biletkodu_tablosu.child(telno).child("misirkod").setValue(olusanbiletkodu);
                                Toast.makeText(buypcodeactivity.this,"KODUNUZ SİPARİŞLERE EKLENMİŞTİR!", Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(buypcodeactivity.this,signedinmenuActivity.class);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();


                            }
                            else
                            {
                                Toast.makeText(buypcodeactivity.this,"BİLET SAYISI BOŞ BIRAKILAMAZ", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(buypcodeactivity.this,"VERİTABANI HATASI OLUŞTU!", Toast.LENGTH_SHORT).show();
                            Intent anaekran = new Intent(buypcodeactivity.this, signedinmenuActivity.class );
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



// Random kod için kullandığım fonksiyon..

String validCharacters = $('0', '9').join() + $('A', 'Z').join();

    String randomString(int length) {
        return $(validCharacters).shuffle().slice(length).toString();
    }


    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(getBaseContext(), signedinmenuActivity.class));
        finish();
    }
}
