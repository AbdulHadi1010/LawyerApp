package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.List;
import android.content.Intent;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> productList;
    private ImageButton buttonEdit;
    private ImageButton buttonDelete;
    private int productId;
    public ProductAdapter(Context context, List<Product> productList) {
        super(context, 0, productList);
        this.context = context;
        this.productList = productList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_product_main, parent, false);
        }

        Product product = productList.get(position);


        TextView textViewId = view.findViewById(R.id.textViewId);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewDescription = view.findViewById(R.id.textViewDescription);
        TextView textViewPrice = view.findViewById(R.id.textViewPrice);

        textViewId.setText("ID: " + product.getID());
        textViewName.setText("Name: " + product.getName());
        textViewDescription.setText("Description: " + product.getDescription());
        textViewPrice.setText("Price: " + product.getPrice());


        return view;
    }

}