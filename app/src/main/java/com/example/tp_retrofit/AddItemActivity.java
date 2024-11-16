package com.example.tp_retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    private EditText inputName, inputDescription;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_item);

        // Initialize the views
        inputName = findViewById(R.id.inputName);
        inputDescription = findViewById(R.id.inputDescription);
        saveButton = findViewById(R.id.saveButton);

        // Set up save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the values from the EditText fields
                String name = inputName.getText().toString().trim();
                String description = inputDescription.getText().toString().trim();

                // Check if the fields are filled out
                if (!name.isEmpty() && !description.isEmpty()) {
                    // Create a new item
                    Item newItem = new Item();

                    // Call the method to add the item
                    addItem(newItem);
                } else {
                    Toast.makeText(AddItemActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addItem(Item item) {
        // You can call the API to add the item, assuming Retrofit is set up
        ApiService apiService = RetrofitClient.getRetrofit(false).create(ApiService.class); // Assume false is for JSON format

        apiService.addItem(item).enqueue(new retrofit2.Callback<Item>() {
            @Override
            public void onResponse(retrofit2.Call<Item> call, retrofit2.Response<Item> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddItemActivity.this, "Item added successfully", Toast.LENGTH_SHORT).show();
                    // Optionally, return to the previous activity
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(AddItemActivity.this, "Failed to add item", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Item> call, Throwable t) {
                Toast.makeText(AddItemActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

