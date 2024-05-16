package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.TextView;
import android.os.Bundle;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String qualifyText = intent.getStringExtra("qualifyText");
        String expText = intent.getStringExtra("expText");
        String addressText = intent.getStringExtra("addressText");
        String feeText = intent.getStringExtra("feeText");

        // Display the data in TextViews
        TextView nameTextView = findViewById(R.id.nameTextView);
        nameTextView.setText("Name: " + name);

        TextView qualifyTextView = findViewById(R.id.qualifyTextView);
        qualifyTextView.setText("Qualification: " + qualifyText);

        TextView expTextView = findViewById(R.id.expTextView);
        expTextView.setText("Experience: " + expText);

        TextView addressTextView = findViewById(R.id.addressTextView);
        addressTextView.setText("Address: " + addressText);

        TextView feeTextView = findViewById(R.id.feeTextView);
        feeTextView.setText("Fee: " + feeText);
    }
}