package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductFunctions extends AppCompatActivity {
    private EditText editTextProductId;
    private EditText editTextProductName;
    private EditText editTextProductDescription;
    private EditText editTextProductPrice;
    private Button buttonInsertProduct;
    private Button buttonUpdateProduct;
    private Button buttonDeleteProduct;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_functions);

        String action = getIntent().getStringExtra("action");

        dbHandler = new DatabaseHandler(this);

        editTextProductId = findViewById(R.id.editTextId);
        editTextProductName = findViewById(R.id.editTextProductName);
        editTextProductDescription = findViewById(R.id.editTextProductDescription);
        editTextProductPrice = findViewById(R.id.editTextProductPrice);
        buttonInsertProduct = findViewById(R.id.buttonInsertProduct);
        buttonUpdateProduct = findViewById(R.id.buttonUpdate);
        buttonDeleteProduct = findViewById(R.id.buttonDelete);

        if (action.equals("insert")) {
            // Show all EditTexts and Insert button for insert action
            editTextProductId.setVisibility(View.VISIBLE);
            editTextProductName.setVisibility(View.VISIBLE);
            editTextProductDescription.setVisibility(View.VISIBLE);
            editTextProductPrice.setVisibility(View.VISIBLE);
            buttonInsertProduct.setVisibility(View.VISIBLE);
            buttonUpdateProduct.setVisibility(View.GONE);
            buttonDeleteProduct.setVisibility(View.GONE);

            buttonInsertProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer productId = Integer.valueOf(editTextProductId.getText().toString());
                    String productName = editTextProductName.getText().toString();
                    String productDescription = editTextProductDescription.getText().toString();
                    double productPrice = Double.parseDouble(editTextProductPrice.getText().toString());

                    Product product = new Product(productId, productName, productDescription, productPrice);
                    dbHandler.addProduct(product);
                    setResult(RESULT_OK);
                    finish();
                    // Clear the edit texts
                    editTextProductId.setText("");
                    editTextProductName.setText("");
                    editTextProductDescription.setText("");
                    editTextProductPrice.setText("");
                }
            });
        } else if (action.equals("update")) {
            // Show only ID EditText and Update button for update action
            editTextProductId.setVisibility(View.VISIBLE);
            editTextProductName.setVisibility(View.GONE);
            editTextProductDescription.setVisibility(View.GONE);
            editTextProductPrice.setVisibility(View.GONE);
            buttonInsertProduct.setVisibility(View.GONE);
            buttonUpdateProduct.setVisibility(View.GONE);
            buttonDeleteProduct.setVisibility(View.GONE);
            // Add a fetch button
            Button buttonFetchProduct = findViewById(R.id.buttonFetchProduct);
            buttonFetchProduct.setVisibility(View.VISIBLE);

            buttonFetchProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Fetch product information based on the entered ID
                    int productId = Integer.parseInt(editTextProductId.getText().toString());
                    Product existingProduct = dbHandler.getProduct(productId);
                    if (existingProduct != null) {
                        // Show the fetched product information in EditText fields
                        editTextProductName.setVisibility(View.VISIBLE);
                        editTextProductDescription.setVisibility(View.VISIBLE);
                        editTextProductPrice.setVisibility(View.VISIBLE);
                        buttonFetchProduct.setVisibility(View.GONE);
                        buttonUpdateProduct.setVisibility(View.VISIBLE);

                        editTextProductName.setText(existingProduct.getName());
                        editTextProductDescription.setText(existingProduct.getDescription());
                        editTextProductPrice.setText(String.valueOf(existingProduct.getPrice()));

                    } else {
                        Toast.makeText(ProductFunctions.this, "Product not found", Toast.LENGTH_SHORT).show();
                        editTextProductName.setText("");
                        editTextProductDescription.setText("");
                        editTextProductPrice.setText("");
                    }
                }
            });

            buttonUpdateProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Update the product if it exists
                    Integer productId = Integer.valueOf(editTextProductId.getText().toString());
                    // Retrieve the existing product
                    Product existingProduct = dbHandler.getProduct(productId);
                    if (existingProduct != null) {
                        // Update the product
                        existingProduct.setName(editTextProductName.getText().toString());
                        existingProduct.setDescription(editTextProductDescription.getText().toString());
                        existingProduct.setPrice(Double.parseDouble(editTextProductPrice.getText().toString()));
                        dbHandler.updateProduct(existingProduct);
                        setResult(RESULT_OK);
                        finish();
                        // Clear the edit texts
                        editTextProductId.setText("");
                        editTextProductName.setText("");
                        editTextProductDescription.setText("");
                        editTextProductPrice.setText("");
                    }
                }
            });
        } else {
            // Show only ID EditText and Delete button for delete action
            editTextProductId.setVisibility(View.VISIBLE);
            editTextProductName.setVisibility(View.GONE);
            editTextProductDescription.setVisibility(View.GONE);
            editTextProductPrice.setVisibility(View.GONE);
            buttonInsertProduct.setVisibility(View.GONE);
            buttonUpdateProduct.setVisibility(View.GONE);
            buttonDeleteProduct.setVisibility(View.VISIBLE);

            buttonDeleteProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer productId = Integer.valueOf(editTextProductId.getText().toString());
                    // Retrieve the existing product
                    Product existingProduct = dbHandler.getProduct(productId);
                    if (existingProduct != null) {
                        // Delete the product
                        dbHandler.deleteProduct(existingProduct);
                        setResult(RESULT_OK);
                        finish();
                        // Clear the edit text
                        editTextProductId.setText("");
                    }
                }
            });
        }
    }

}