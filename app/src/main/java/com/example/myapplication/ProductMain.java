package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductMain extends AppCompatActivity {
    private ListView listView;
    private DatabaseHandler dbHandler;
    private static final int REQUEST_CODE_INSERT_PRODUCT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        dbHandler = new DatabaseHandler(this);

        listView = findViewById(R.id.listView);

        // Get all products from database
        List<Product> productList = dbHandler.getAllProducts();

        // Create an adapter to display the products in the ListView
        ProductAdapter adapter = new ProductAdapter(this, productList);
        listView.setAdapter(adapter);

        Button buttonInsertProduct = findViewById(R.id.buttonInsertProduct);
        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        Button buttonDelete = findViewById(R.id.buttonDelete);

        buttonInsertProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductMain.this, ProductFunctions.class);
                intent.putExtra("action", "insert");
                startActivityForResult(intent, REQUEST_CODE_INSERT_PRODUCT);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductMain.this, ProductFunctions.class);
                intent.putExtra("action", "update");
                startActivityForResult(intent, REQUEST_CODE_INSERT_PRODUCT);

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductMain.this, ProductFunctions.class);
                intent.putExtra("action", "delete");
                startActivityForResult(intent, REQUEST_CODE_INSERT_PRODUCT);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_INSERT_PRODUCT) {
            if (resultCode == RESULT_OK) {
                // Refresh the list of products
                List<Product> productList = dbHandler.getAllProducts();
                ProductAdapter adapter = new ProductAdapter(this, productList);
                listView.setAdapter(adapter);
            }
        }
    }
}
