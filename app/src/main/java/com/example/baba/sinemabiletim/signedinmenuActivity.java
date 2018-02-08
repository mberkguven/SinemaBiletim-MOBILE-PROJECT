package com.example.baba.sinemabiletim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baba.sinemabiletim.Test.Test;

public class signedinmenuActivity extends AppCompatActivity {

    ImageView onCinemas, onlyTicket, promoTicket;
    TextView userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signedinmenu);



        //Kullanıcı isimini classtan çekip textviewa yazdırma.

        userName = (TextView)findViewById(R.id.kullaniciisim);
        userName.setText("Hoşgeldin "+Test.aKul.getIsim());




        //Imageviewların buton click eventleri



        onCinemas = (ImageView)findViewById(R.id.vizyon);
        onlyTicket = (ImageView)findViewById(R.id.sadebilet);
        promoTicket = (ImageView)findViewById(R.id.misirbilet);

        onCinemas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vizyondakiler = new Intent(signedinmenuActivity.this, oncinemasActivity.class );
                startActivity(vizyondakiler);
                finish();
            }
        });

        onlyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sadecebilet = new Intent(signedinmenuActivity.this, buyocodeactivity.class);
                startActivity(sadecebilet);
                finish();
            }
        });

        promoTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent misirbilet = new Intent(signedinmenuActivity.this, buypcodeactivity.class);
                startActivity(misirbilet);
                finish();
            }
        });

    }


    //Options menu ayarlari ...

    private static final int ID_PROFILE =Menu.FIRST;
    private static final int ID_ORDERS =Menu.FIRST+1;
    private static final int ID_EXIT =Menu.FIRST+2;





    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(Menu.NONE, ID_PROFILE, 0, "Kullanıcı Bilgileri");
        menu.add(Menu.NONE, ID_ORDERS,1,"Siparişlerim");
        menu.add(Menu.NONE, ID_EXIT, 2, "Çıkış");



        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case ID_PROFILE:

                Intent userinfoActivity = new Intent(signedinmenuActivity.this,userinfoActivity.class);
                startActivity(userinfoActivity);
                finish();
                return true;
            case ID_ORDERS:

                Intent ordersActivity = new Intent(signedinmenuActivity.this,ordersActivity.class);
                startActivity(ordersActivity);
                finish();
                return true;

            case ID_EXIT:

                Intent openingActivity = new Intent(signedinmenuActivity.this,openingActivity.class);
                startActivity(openingActivity);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    //Geriye tıklandığında gidilecek yer.


    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(getBaseContext(), openingActivity.class));
        System.exit(0);
    }


}