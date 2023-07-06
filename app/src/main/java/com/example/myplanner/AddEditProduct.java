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

import com.example.myplanner.Fragments.ProductFragment;

import java.io.ByteArrayOutputStream;

import Controller.DatabaseHandler;
import Controller.ProductAdapter;
import Models.Product;

public class EditProduct extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.myplanner.EXTRA_ID";
    public static final String EXTRA_NAME =
            "com.example.myplanner.EXTRA_EXTRA_NAME";
    public static final String EXTRA_WEIGHT =
            "com.example.myplanner.EXTRA_WEIGHT";
    public static final String EXTRA_CADENCE =
            "com.example.myplanner.EXTRA_CADENCE";
    public static final String EXTRA_DATE_OF_MANUFACTURE =
            "com.example.myplanner.EXTRA_DATE_OF_MANUFACTURE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.myplanner.EXTRA_DESCRIPTION";
    public static final String EXTRA_IMAGE_BYTE=
            "com.example.myplanner.EXTRA_IMAGE_BYTE";

    private EditText pr_name , pr_weight , pr_cadence, pr_date_of_manufacture , pr_description;
    Button pr_edit , pr_delete ;
    ImageView a_imageView ;
    private DatabaseHandler databaseHandler;
    Product product ;
    int position;
    String str_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        pr_name = findViewById(R.id.p_name1);
        pr_weight = findViewById(R.id.p_weight1);
        pr_cadence = findViewById(R.id.p_cadence1);
        pr_date_of_manufacture = findViewById(R.id.p_dateManufacture1);
        pr_description = findViewById(R.id.p_description1);
        //selectimage = findViewById(R.id.addImagebtn1);
        a_imageView = findViewById(R.id.p_image);
        pr_edit = findViewById(R.id.p_edit1);
        pr_delete = findViewById(R.id.p_delete1);


        Bundle bundle = getIntent().getExtras();
        str_position = bundle.getString("position");
        position = Integer.parseInt(str_position);
        databaseHandler = new DatabaseHandler(this);
        product = databaseHandler.getProduct(position);

        if(product != null){
            pr_name.setText(product.getPr_name());
            pr_weight.setText(String.valueOf(product.getPr_weight()));
            pr_cadence.setText(String.valueOf(product.getPr_cadence()));
            pr_date_of_manufacture.setText(product.getPr_date_of_manufacture());
            pr_description.setText(product.getPr_description());
            a_imageView.setImageBitmap(convertByteArrayToBitmap(product.getPr_image()));
        }

        pr_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setPr_name(pr_name.getText().toString());
                product.setPr_weight(Integer.parseInt(pr_weight.getText().toString()));
                product.setPr_cadence(Integer.parseInt(pr_cadence.getText().toString()));
                product.setPr_date_of_manufacture(pr_date_of_manufacture.getText().toString());
                product.setPr_description(pr_description.getText().toString());

                a_imageView.setDrawingCacheEnabled(true);
                a_imageView.buildDrawingCache();
                Bitmap bitmap = a_imageView.getDrawingCache();
                byte[] imageBytes = getBytesFromBitmap(bitmap);
                product.setPr_image(imageBytes);


                databaseHandler.updateProduct(product);
                setResult(RESULT_OK);
                finish();
            }
        });

        pr_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseHandler.deleteProduct(position);
                ProductFragment.notifyAdapter();
//                Intent i = new Intent(EditProduct.this, Main_activity.class);
//                startActivity(i);
                finish();
                //deleteArticleFromRecyclerView(position1);
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