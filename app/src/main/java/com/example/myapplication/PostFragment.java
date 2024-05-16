package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class PostFragment extends Fragment {
      TextView usernameTextView;
     TextView qualifyText;
     ImageView imageView;
     TextView expText;
     TextView AddressText;
     TextView FeeText;
    LinearLayout mainLayout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    public PostFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_fragment, container, false);
        mainLayout = view.findViewById(R.id.mainLayout);
        usernameTextView = view.findViewById(R.id.editText);
        qualifyText = view.findViewById(R.id.qualifyText);
        imageView =  view.findViewById(R.id.imageView);
        expText = view.findViewById(R.id.expText);
        AddressText= view.findViewById(R.id.AddressText);
        FeeText= view.findViewById(R.id.FeeText);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        PostAdapter adapter = new PostAdapter();
        recyclerView.setAdapter(adapter);
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = usernameTextView.getText().toString();
                String qualifytext = qualifyText.getText().toString();
                String exptext = expText.getText().toString();
                String addressText = AddressText.getText().toString();
                String feeText = FeeText.getText().toString();

                // Create an Intent to start the second activity
                Intent intent = new Intent(getActivity(), Contact.class);
                // Pass the data to the second activity using Intent extras
                intent.putExtra("name", name);
                intent.putExtra("qualifyText", qualifytext);
                intent.putExtra("expText", exptext);
                intent.putExtra("addressText", addressText);
                intent.putExtra("feeText", feeText);
                startActivity(intent);
            }
        });
        return view;


    }

}