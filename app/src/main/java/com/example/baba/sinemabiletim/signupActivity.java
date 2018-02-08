package com.example.baba.sinemabiletim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.baba.sinemabiletim.Model.Biletkod;
import com.example.baba.sinemabiletim.Model.Kullanici;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class signupActivity extends AppCompatActivity {

    MaterialEditText editPhone, editName, editPw;
    Button btnRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editPhone = (MaterialEditText)findViewById(R.id.edittelkayit);
        editName = (MaterialEditText)findViewById(R.id.editisimkayit);
        editPw = (MaterialEditText)findViewById(R.id.editsifrekayit);
        btnRegister = (Button)findViewById(R.id.btnkayitol);





        //Veritabanı Bağlantısı
        final FirebaseDatabase veritabani = FirebaseDatabase.getInstance();
        final DatabaseReference kullanici_tablosu = veritabani.getReference("Hesaplar");
        final DatabaseReference biletkodu_tablosu = veritabani.getReference("BiletKod");






        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // Kullanıcı tablosuna butona yatıgımız gibi listener kullanıyorum.Firebase veri yazma bu şekilde.

                kullanici_tablosu.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //Bu tabloda kullanıcının girdiği tel noya karışılık gelen data var mı ona bakıyorum.

                        if(dataSnapshot.child(editPhone.getText().toString()).exists())
                        {
                            Toast.makeText(signupActivity.this, "Bu numaranın hesabı mevcut!", Toast.LENGTH_SHORT).show();
                        }
                        // Yoksa kullanıcının girdiği bilgilere göre veritabanına yeni kayıt atıyorum ve sayfayı kapatıyorum.
                        else
                        {
                            String yenikayit = "Henüz bilet satın almadınız.";
                            Kullanici kul = new Kullanici(editName.getText().toString(), editPw.getText().toString());
                            Biletkod bilkod = new Biletkod(yenikayit,yenikayit);
                            kullanici_tablosu.child(editPhone.getText().toString()).setValue(kul);
                            biletkodu_tablosu.child(editPhone.getText().toString()).setValue(bilkod);
                            Toast.makeText(signupActivity.this, "Hesabınız açılmıştır !", Toast.LENGTH_SHORT).show();

                            Intent girisekrani = new Intent(signupActivity.this, openingActivity.class );
                            startActivity(girisekrani);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }


    //Geri tuşuna baıslınca gidiceği aktivite ayarı.

    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(getBaseContext(), openingActivity.class));
        finish();
    }


}
