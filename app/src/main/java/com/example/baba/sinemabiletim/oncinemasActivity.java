package com.example.baba.sinemabiletim;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class oncinemasActivity extends AppCompatActivity {


    /*
      We use JSOUP lib to get specific HTML data from internet and add it to ListView. To these we use listview,array list,array adaptor.
     */


    /*Burada JSOUP kütüphanesi sayesinde internet sayfasından belirli bir HTML kısımına karşılık
      datayı ListvView'a aktarıcaz.Bunun için listview,array listesi,array adatorude kullanıcaz.
    */


    private ListView vizyonView;
    public ArrayList vizyonliste = new ArrayList();
    private ArrayAdapter<String> adapter;
    private static String URL="http://sinema.mynet.com/vizyondaki-filmler";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oncinemas);

        vizyonView = (ListView)findViewById(R.id.liste);

        adapter = new ArrayAdapter<String>(this,R.layout.activity_forlistviewcolor,R.id.testrenk,vizyonliste); // forlistViewcolor activity used here.

        // Yazılan class çalıştırılacak ve listview dolucak.

        new VeriGetir().execute();

    }

    private class VeriGetir extends AsyncTask<Void, Void, Void>{



        //Vizyon sekmesi basıldığı an kullanıcı iş yapıldığını görsün diye buraları doldurdum.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog= new ProgressDialog(oncinemasActivity.this);
            progressDialog.setTitle("Vizyondakiler");
            progressDialog.setMessage("Lütfen Bekleyiniz...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        // we get the data we want from the internet by using JSOUP here.

        // veriler arka planda çekicez.Jsoup kullanıcaz.
        @Override
        protected Void doInBackground(Void... voids) {


            //You have to use try&catch or you can get an error.We connect to the website and filled the data of that website to DOC.

            //WEB sayfasına jsoup ile bağlayıp doca doldurdum.

            try {
                org.jsoup.nodes.Document doc = Jsoup.connect(URL).timeout(30*1000).get();


                //We filled the element filmisim with div.vizyonImg tagged data.

                //Sadece div.vizyonImg tagı olan verilerie filmisim'e attım.

                Elements filmisim= doc.select("div.vizyonImg");

                //We add the data of filmisim to arraylist.

                //Filmisimin içerdiği bütün bilgileri teker teker arraylistesine for döngüsüyle ekledik.

                for (int i=0;i<filmisim.size();i++){
                    vizyonliste.add(filmisim.get(i).text());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //We set an adapter that we filled with data we need to our Listview(vizyonView).
            //Arrayadapterüne vizyonliste'deki bilgileri koymuştuk.Burdada o adaptörü listview a set ediyoruz.

            vizyonView.setAdapter(adapter);
            progressDialog.dismiss();
        }

    }

    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(getBaseContext(), signedinmenuActivity.class));
        finish();
    }



}
