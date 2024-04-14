package com.example.myapplication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class HomeScreenAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Inside HomeScreen");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_acitivity);
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin a new FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Create a new instance of the fragment
        PostFragment myFragment = new PostFragment();

        // Replace the contents of the container with the new fragment
        fragmentTransaction.replace(R.id.fragment_container, myFragment);

        // Commit the transaction
        fragmentTransaction.commit();
    }
}