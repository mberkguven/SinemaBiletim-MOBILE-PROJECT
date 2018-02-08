package com.example.baba.sinemabiletim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baba.sinemabiletim.Model.Kullanici;
import com.example.baba.sinemabiletim.Test.Test;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class signinActivity extends AppCompatActivity {

    EditText editPhone, editPw;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        editPhone = (MaterialEditText)findViewById(R.id.edittel);
        editPw = (MaterialEditText)findViewById(R.id.editsifre);

        btnLogin = (Button)findViewById(R.id.btngirisyap);

        //Veritabanı Bağlantıları.Önce vbyi bağlayıp,ardından Hesaplar tablosuyla ilişkilendiriyorum.

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference users_table = database.getReference("Hesaplar");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                users_table.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(editPhone.getText().toString()).exists()) {


                            // Girilen telefona gelen karşılık gelen datayı vb'den  class a çekiyorum.
                            Kullanici kul = dataSnapshot.child(editPhone.getText().toString()).getValue(Kullanici.class);

                            //Class a çektiğim datayla kullancının girdiği şifreyi karşılaştırıyorum.
                            if(kul.getSifre().equals(editPw.getText().toString())){

                                //Ana ekrana kullanıcıyı yönlendirmeden önce bilgilerini bi classa atıyorum.
                                Intent anaekran = new Intent(signinActivity.this, signedinmenuActivity.class );
                                Test.aTel = editPhone.getText().toString();
                                Test.aKul = kul;
                                startActivity(anaekran);
                                finish();
                            }
                            else{
                                Toast.makeText(signinActivity.this, "Hatalı Şifre !", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else
                            {
                                //Kullanıcı kayıt olsa bile bu mesajı basıyor,bug var.kontrol et.
                                Toast.makeText(signinActivity.this, "Kullanıcı bulunamadı !", Toast.LENGTH_SHORT).show();
                            }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {


                    }
                });

            }
        });

    }



    // geri tuşuna basıldığında gideceği aktivite.

    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(getBaseContext(), openingActivity.class));
        finish();
    }




}
