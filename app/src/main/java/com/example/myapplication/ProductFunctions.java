package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import kotlinx.coroutines.scheduling.Task;

public class ProductFunctions extends AppCompatActivity {
    private EditText editTextProductId;
    private EditText editTextProductName;
    private EditText editTextProductDescription;
    private EditText editTextProductPrice;
    private Button buttonInsertProduct;
    private Button buttonUpdateProduct;
    private Button buttonDeleteProduct;
    private DatabaseHandler dbHandler;
    private Button buttonFetchProduct;
    private static final String COLLECTION_PRODUCTS = "products";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_functions);

        String action = getIntent().getStringExtra("action");
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        dbHandler = new DatabaseHandler(this);

        editTextProductId = findViewById(R.id.editTextId);
        editTextProductName = findViewById(R.id.editTextProductName);
        editTextProductDescription = findViewById(R.id.editTextProductDescription);
        editTextProductPrice = findViewById(R.id.editTextProductPrice);
        buttonInsertProduct = findViewById(R.id.buttonInsertProduct);
        buttonUpdateProduct = findViewById(R.id.buttonUpdate);
        buttonDeleteProduct = findViewById(R.id.buttonDelete);
        buttonFetchProduct = findViewById(R.id.buttonFetchProduct);

        if (action.equals("insert")) {
            // Show all EditTexts and Insert button for insert action
            editTextProductId.setVisibility(View.VISIBLE);
            editTextProductName.setVisibility(View.VISIBLE);
            editTextProductDescription.setVisibility(View.VISIBLE);
            editTextProductPrice.setVisibility(View.VISIBLE);
            buttonInsertProduct.setVisibility(View.VISIBLE);
            buttonUpdateProduct.setVisibility(View.GONE);
            buttonDeleteProduct.setVisibility(View.GONE);
            buttonFetchProduct.setVisibility(View.GONE);

            buttonInsertProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer productId = Integer.valueOf(editTextProductId.getText().toString());
                    String productName = editTextProductName.getText().toString();
                    String productDescription = editTextProductDescription.getText().toString();
                    double productPrice = Double.parseDouble(editTextProductPrice.getText().toString());

                    Product product = new Product(productId, productName, productDescription, productPrice);
                    db.collection(COLLECTION_PRODUCTS).document(String.valueOf(productId))
                            .set(product, SetOptions.merge())
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(ProductFunctions.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK);
                                finish();
                                // Clear the edit texts
                                editTextProductId.setText("");
                                editTextProductName.setText("");
                                editTextProductDescription.setText("");
                                editTextProductPrice.setText("");
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(ProductFunctions.this, "Error adding product", Toast.LENGTH_SHORT).show();
                            });
//                    dbHandler.addProduct(product);
//                    setResult(RESULT_OK);
//                    finish();
                    // Clear the edit texts
//                    editTextProductId.setText("");
//                    editTextProductName.setText("");
//                    editTextProductDescription.setText("");
//                    editTextProductPrice.setText("");
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
            buttonFetchProduct.setVisibility(View.VISIBLE);

            buttonFetchProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Fetch product information based on the entered ID
                    int productId = Integer.parseInt(editTextProductId.getText().toString());
                    db.collection(COLLECTION_PRODUCTS).document(String.valueOf(productId))
                            .get()
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Product existingProduct = document.toObject(Product.class);
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
                                } else {
                                    Toast.makeText(ProductFunctions.this, "Error fetching product", Toast.LENGTH_SHORT).show();
                                }
                            });
//                    Product existingProduct = dbHandler.getProduct(productId);
//                    if (existingProduct != null) {
//                        // Show the fetched product information in EditText fields
//                        editTextProductName.setVisibility(View.VISIBLE);
//                        editTextProductDescription.setVisibility(View.VISIBLE);
//                        editTextProductPrice.setVisibility(View.VISIBLE);
//                        buttonFetchProduct.setVisibility(View.GONE);
//                        buttonUpdateProduct.setVisibility(View.VISIBLE);
//
//                        editTextProductName.setText(existingProduct.getName());
//                        editTextProductDescription.setText(existingProduct.getDescription());
//                        editTextProductPrice.setText(String.valueOf(existingProduct.getPrice()));
//
//                    } else {
//                        Toast.makeText(ProductFunctions.this, "Product not found", Toast.LENGTH_SHORT).show();
//                        editTextProductName.setText("");
//                        editTextProductDescription.setText("");
//                        editTextProductPrice.setText("");
//                    }
                }
            });

            buttonUpdateProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer productId = Integer.valueOf(editTextProductId.getText().toString());
                    String productName = editTextProductName.getText().toString();
                    String productDescription = editTextProductDescription.getText().toString();
                    double productPrice = Double.parseDouble(editTextProductPrice.getText().toString());

                    Product product = new Product(productId, productName, productDescription, productPrice);

                    db.collection(COLLECTION_PRODUCTS).document(String.valueOf(productId))
                            .set(product, SetOptions.merge())
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(ProductFunctions.this, "Product updated successfully", Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK);
                                finish();
                                // Clear the edit texts
                                editTextProductId.setText("");
                                editTextProductName.setText("");
                                editTextProductDescription.setText("");
                                editTextProductPrice.setText("");
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(ProductFunctions.this, "Error updating product", Toast.LENGTH_SHORT).show();
                            });
//                    Integer productId = Integer.valueOf(editTextProductId.getText().toString());
//                    Product existingProduct = dbHandler.getProduct(productId);
//                    if (existingProduct != null) {
//                        // Update the product
//                        existingProduct.setName(editTextProductName.getText().toString());
//                        existingProduct.setDescription(editTextProductDescription.getText().toString());
//                        existingProduct.setPrice(Double.parseDouble(editTextProductPrice.getText().toString()));
//                        dbHandler.updateProduct(existingProduct);
//                        setResult(RESULT_OK);
//                        finish();
//                        // Clear the edit texts
//                        editTextProductId.setText("");
//                        editTextProductName.setText("");
//                        editTextProductDescription.setText("");
//                        editTextProductPrice.setText("");
//                    }
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

                    db.collection(COLLECTION_PRODUCTS).document(String.valueOf(productId))
                            .delete()
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(ProductFunctions.this, "Product deleted successfully", Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK);
                                finish();
                                // Clear the edit text
                                editTextProductId.setText("");
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(ProductFunctions.this, "Error deleting product", Toast.LENGTH_SHORT).show();
                            });
//                    Integer productId = Integer.valueOf(editTextProductId.getText().toString());
//                    // Retrieve the existing product
//                    Product existingProduct = dbHandler.getProduct(productId);
//                    if (existingProduct != null) {
//                        // Delete the product
//                        dbHandler.deleteProduct(existingProduct);
//                        setResult(RESULT_OK);
//                        finish();
//                        // Clear the edit text
//                        editTextProductId.setText("");
//                    }
                }
            });
        }
    }

}