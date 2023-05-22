package com.example.myplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myplanner.Fragments.ArticleFragment;

import java.io.ByteArrayOutputStream;

import Controller.DatabaseHandler;
import Models.ArticleModel;

public class Add_Article extends AppCompatActivity {

    private EditText name , weight , cadence ;
    private Button image_btn , save_btn ;
    private ImageView imageView ;
    int SELECT_IMAGE_CODE = 1;
    private DatabaseHandler db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        db = new DatabaseHandler(this);
        name = findViewById(R.id.edit_name);
        weight = findViewById(R.id.edit_weight);
        cadence = findViewById(R.id.edit_cad);
        image_btn = findViewById(R.id.addImagebtn);
        save_btn = findViewById(R.id.savebtn);
        imageView = findViewById(R.id.article_image);


    // Path into gallery
        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Title"),SELECT_IMAGE_CODE);
            }
        });

    // insert data into article table
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String name_art = name.getText().toString();
               int weight_art = Integer.parseInt(weight.getText().toString());
               int cadence_art = Integer.parseInt(cadence.getText().toString());

                // Convert the bitmap to a byte array
                imageView.setDrawingCacheEnabled(true);
                imageView.buildDrawingCache();
                Bitmap bitmap = imageView.getDrawingCache();
                byte[] imageBytes = getBytesFromBitmap(bitmap);

                int id = db.addArticle(new ArticleModel(name_art,weight_art,cadence_art,imageBytes));
                Intent intent = new Intent(Add_Article.this, ArticleFragment.class);
                startActivity(intent);
                //Toast.makeText(Add_Article.this, "Successfull", Toast.LENGTH_SHORT).show();
                finish();
            }

            private byte[] getBytesFromBitmap(Bitmap bitmap) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                return stream.toByteArray();
            }
        });
    }


    // link the selected image into the image_view
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
        }
    }
}