package com.example.myplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myplanner.Fragments.ArticleFragment;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;

import Controller.DatabaseHandler;
import Models.ArticleModel;

public class EditArticle extends AppCompatActivity {

    private EditText a_name , a_weight , a_cadence ;
    Button save  ;
    ImageView a_imageView ;
    private DatabaseHandler databaseHandler;
    ArticleModel article;
    int position;
    String str_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);

        a_name = findViewById(R.id.edit_name1);
        a_weight = findViewById(R.id.edit_weight1);
        a_cadence = findViewById(R.id.edit_cad1);
        //selectimage = findViewById(R.id.addImagebtn1);
        a_imageView = findViewById(R.id.article_image1);
        save = findViewById(R.id.savebtn1);


        Bundle bundle = getIntent().getExtras();
        str_position = bundle.getString("position");
        position = Integer.parseInt(str_position);
        databaseHandler = new DatabaseHandler(this);
        article = databaseHandler.getArticle(position);

        if(article != null){
            a_name.setText(article.getName_art());
            a_weight.setText(String.valueOf(article.getWeight_art()));
            a_cadence.setText(String.valueOf(article.getCadence_art()));
            a_imageView.setImageBitmap(convertByteArrayToBitmap(article.getImage_art()));
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                article.setName_art(a_name.getText().toString());
                article.setWeight_art(Integer.parseInt(a_weight.getText().toString()));
                article.setCadence_art(Integer.parseInt(a_cadence.getText().toString()));

                a_imageView.setDrawingCacheEnabled(true);
                a_imageView.buildDrawingCache();
                Bitmap bitmap = a_imageView.getDrawingCache();
                byte[] imageBytes = getBytesFromBitmap(bitmap);
                article.setImage_art(imageBytes);


                databaseHandler.updateArticle(article);
                ArticleFragment.notifyAdapter();
                Intent i = new Intent(EditArticle.this, Interface_Chef.class);
                startActivity(i);
                finish();
            }
        });

    }

    private byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public Bitmap convertByteArrayToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}