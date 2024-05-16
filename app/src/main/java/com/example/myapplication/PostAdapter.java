package com.example.myapplication;

import android.content.Intent;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
        public List<String> profileImages = Arrays.asList(
            "https://randomuser.me/api/portraits/men/90.jpg",
            "https://randomuser.me/api/portraits/women/51.jpg",
            "https://randomuser.me/api/portraits/men/76.jpg",
                "https://randomuser.me/api/portraits/women/20.jpg",
                "https://randomuser.me/api/portraits/men/64.jpg",
                "https://randomuser.me/api/portraits/men/60.jpg",
                "https://randomuser.me/api/portraits/women/60.jpg",
                "https://randomuser.me/api/portraits/men/10.jpg",
                "https://randomuser.me/api/portraits/women/60.jpg",
            "https://randomuser.me/api/portraits/men/30.jpg");
    // Generate max lawyer qualifications
    private final List<String> names = Arrays.asList(
            "John Smith",
            "Emily Johnson",
            "Michael Williams",
            "Sophia Brown",
            "Oliver Jones",
            "Charlotte Davis",
            "Daniel Martinez",
            "Emma Taylor",
            "William Anderson",
            "Ava Garcia"
    );

    // Generate max lawyer qualifications
    private final List<String> maxQualifications = Arrays.asList(
            "LLB",
            "LLM",
            "JD",
            "LLM",
            "LLB",
            "LLM",
            "JD",
            "LLM",
            "JD",
            "LLB"
    );

    // Generate random numbers from 1 to 10
    private final List<Integer> randomNumbers = Arrays.asList(
            8, 5, 3, 9, 7, 2, 4, 6, 1, 10
    );

    // Generate small temporary addresses
    private final List<String> addresses = Arrays.asList(
            "123 Street",
            "456 Avenue",
            "789 Road",
            "987 Boulevard",
            "321 Lane",
            "654 Place",
            "987 Court",
            "123 Drive",
            "456 Circle",
            "789 Alley"
    );

    // Generate random numbers from 100 to 900
    private final List<Integer> randomNumbers2 = Arrays.asList(
            456, 234, 678, 345, 789, 123, 567, 890, 432, 210
    );


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Bind data to views
        String username = names.get(position);
        String profileUrl = profileImages.get(position);
        String Qualification = maxQualifications.get(position);
        String experience = String.valueOf(randomNumbers.get(position));
        String address = addresses.get(position);
        String Fee = String.valueOf(randomNumbers2.get(position));
        holder.bind(username, Qualification, profileUrl, experience, address, Fee);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the position of the item that was clicked
                int clickedPosition = holder.getAdapterPosition();
                String clickedName = holder.usernameTextView.getText().toString();
                String clickedQualification = holder.qualifyText.getText().toString();
                String clickedExperience = holder.expText.getText().toString();
                String clickedAddress = holder.AddressText.getText().toString();
                String clickedFee = holder.FeeText.getText().toString();

                // Create an Intent to start the next activity
                Intent intent = new Intent(view.getContext(), Contact.class);
                // Pass data to the next activity using Intent extras
                intent.putExtra("name", clickedName);
                intent.putExtra("qualification", clickedQualification);
                intent.putExtra("experience", clickedExperience);
                intent.putExtra("address", clickedAddress);
                intent.putExtra("fee", clickedFee);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Define views
        private final TextView usernameTextView;
        private TextView qualifyText;
        private ImageView imageView;
        private TextView expText;
        private TextView AddressText;
        private TextView FeeText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views
            qualifyText = itemView.findViewById(R.id.qualifyText);
            usernameTextView = itemView.findViewById(R.id.editText);
            imageView =  itemView.findViewById(R.id.imageView);
            expText = itemView.findViewById(R.id.expText);
            AddressText= itemView.findViewById(R.id.AddressText);
            FeeText= itemView.findViewById(R.id.FeeText);

        }


        public void bind(String data, String qualificationText, String profileUrl, String Exp, String Address, String Fee) {
            // Bind data to views
            usernameTextView.setText(data);
            qualifyText.setText(qualificationText);
            Picasso.get().load(profileUrl).into(imageView);
            expText.setText(Exp+" Years of expereince");
            AddressText.setText(Address);
            FeeText.setText("$"+Fee);

        }


    }
}
