package io.artcreativity.sketchapp.activities.profile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import io.artcreativity.sketchapp.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

    }

}