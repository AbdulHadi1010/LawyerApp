package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.List;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProductMain extends AppCompatActivity {
    private ListView listView;
    private List<Product> productList;
    private ProductAdapter adapter;
    private DatabaseHandler dbHandler;
    private FirebaseFirestore db;

    private static final int REQUEST_CODE_INSERT_PRODUCT = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        db = FirebaseFirestore.getInstance();
        listView = findViewById(R.id.listView);

        productList = new ArrayList<>();
        adapter = new ProductAdapter(this, productList);
        listView.setAdapter(adapter);

        // Load products from Firestore
        loadProductsFromFirestore();

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

    private void loadProductsFromFirestore() {
        db.collection("products")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            // Handle error
                            return;
                        }

                        productList.clear();
                        for (QueryDocumentSnapshot doc : value) {
                            Product product = doc.toObject(Product.class);
                            productList.add(product);
                        }

                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_INSERT_PRODUCT) {
            if (resultCode == RESULT_OK) {
                // Refresh the list of products
                loadProductsFromFirestore();
            }
        }
    }
}
