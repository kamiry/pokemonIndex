package com.example.jcliu.pokemonindex;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner sp;
    ImageView[] iv = new ImageView[9];
    TextView txv;
    Uri imgUri;

    int[] Iv_Res = {R.id.imageView, R.id.imageView2, R.id.imageView3, R.id.imageView4, R.id.imageView5, R.id.imageView6, R.id.imageView7, R.id.imageView8, R.id.imageView9};
    String[] rarity = {"Everywhere", "Virtually Everywhere", "Very Common", "Common", "Uncommon", "Ununcommon", "Rare", "Very Rare", "Special", "Epic", "Myth", "Not Convinced It Exists"};
    int Img_Res[][] = {{R.drawable.poke1, R.drawable.poke2, R.drawable.poke3},
            {R.drawable.poke4, R.drawable.poke5, R.drawable.poke6, R.drawable.poke7},
            {R.drawable.poke8, R.drawable.poke9, R.drawable.poke10, R.drawable.poke11, R.drawable.poke12, R.drawable.poke13, R.drawable.poke14},
            {R.drawable.poke15, R.drawable.poke16, R.drawable.poke17, R.drawable.poke18, R.drawable.poke19, R.drawable.poke20, R.drawable.poke21, R.drawable.poke22},
            {R.drawable.poke23, R.drawable.poke24, R.drawable.poke25, R.drawable.poke26, R.drawable.poke27, R.drawable.poke28, R.drawable.poke29, R.drawable.poke30},
            {R.drawable.poke31, R.drawable.poke32, R.drawable.poke33, R.drawable.poke34, R.drawable.poke35, R.drawable.poke36, R.drawable.poke37},
            {R.drawable.poke38, R.drawable.poke39, R.drawable.poke40, R.drawable.poke41, R.drawable.poke42, R.drawable.poke43, R.drawable.poke44},
            {R.drawable.poke45, R.drawable.poke46, R.drawable.poke47, R.drawable.poke48, R.drawable.poke49, R.drawable.poke50, R.drawable.poke51},
            {R.drawable.poke52, R.drawable.poke53, R.drawable.poke54, R.drawable.poke55, R.drawable.poke56, R.drawable.poke57, R.drawable.poke58, R.drawable.poke59},
            {R.drawable.poke61, R.drawable.poke61, R.drawable.poke62, R.drawable.poke63, R.drawable.poke64, R.drawable.poke65, R.drawable.poke66},
            {R.drawable.poke67, R.drawable.poke68, R.drawable.poke69, R.drawable.poke70},
            {R.drawable.poke71}
    };

    public void onClick0(View v){
        Log.d("poke", "take picture");
        // directory
        String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        String fname = "p" + System.currentTimeMillis() + ".jpg";
        String fullpath = "file://" + dir + "/" + fname;
        imgUri = Uri.parse(fullpath);

        Log.d("poke", fullpath);

        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it, 100);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.d("poke", "onActivityResult");

        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode == 100){
            Log.d("poke", "Replace imageView");
            Bundle extras = data.getExtras();
            Bitmap bmp = (Bitmap) extras.get("data");
            (iv[0]).setImageBitmap(bmp);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // findallviews
        sp = (Spinner)findViewById(R.id.spinner);
        for(int i=0; i<9; i++)
            iv[i] = (ImageView)findViewById(Iv_Res[i]);
        txv = (TextView)findViewById(R.id.textView);

        // setup spinner
        ArrayAdapter<String> tempAd = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rarity);
        tempAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(tempAd);
        sp.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        int size = (Img_Res[position]).length;
        txv.setText(Integer.toString(size));

        for(int i=0; i<size; i++) {
            (iv[i]).setImageResource(Img_Res[position][i]);
            (iv[i]).setVisibility(View.VISIBLE);
        }
        for(int i=size; i<9; i++)
            (iv[i]).setVisibility(View.GONE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
