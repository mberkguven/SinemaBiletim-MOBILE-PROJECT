package com.example.baba.sinemabiletim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.baba.sinemabiletim.Test.Test;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ordersActivity extends AppCompatActivity {


    EditText onlyTicket, promoTicket;

    private DatabaseReference onlyTicketDB;
    private DatabaseReference promoTicketDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);


        onlyTicket = (EditText)findViewById(R.id.editsipariskodu);
        onlyTicket.setKeyListener(null);
        promoTicket = (EditText)findViewById(R.id.editsiparismsrkodu);
        promoTicket.setKeyListener(null);



        onlyTicketDB = FirebaseDatabase.getInstance().getReference().child("BiletKod").child(Test.aTel).child("biletkod");
        promoTicketDB = FirebaseDatabase.getInstance().getReference().child("BiletKod").child(Test.aTel).child("misirkod");

        onlyTicketDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                onlyTicket.setText(dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        promoTicketDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                promoTicket.setText(dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(getBaseContext(), signedinmenuActivity.class));
        finish();
    }


}
