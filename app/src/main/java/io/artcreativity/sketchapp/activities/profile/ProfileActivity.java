package io.artcreativity.sketchapp.activities.profile;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import io.artcreativity.sketchapp.R;
import io.artcreativity.sketchapp.activities.products.EditProductActivity;
import io.artcreativity.sketchapp.activities.products.ProductDisplayActivity;
import io.artcreativity.sketchapp.databinding.ActivityProfileBinding;
import io.artcreativity.sketchapp.models.Profile;

public class ProfileActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 3033;
    private ActivityProfileBinding binding;

    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        // Recuperation des elements passes dans l'Intent utilise pour demarrer l'activity
        Intent data = getIntent();
        profile = (Profile) data.getSerializableExtra(ProductDisplayActivity.PROFILE_ID);
        binding.firstName.setText(profile.firstName);
        binding.lastName.setText(profile.lastName);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(ProfileActivity.this);
        inflater.inflate(R.menu.save_profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_item) {
            Log.d(TAG, "saveData: action de sauvegarde du profile");
            Toast.makeText(this, "Sauvegarde du profil", Toast.LENGTH_LONG).show();

            // recuperation des nouvelle donnee
            profile.firstName = binding.firstName.getText().toString();
            profile.lastName = binding.lastName.getText().toString();

            // Creation d'une Intent pour envoyer les nouvelles informations dans le product
            Intent intent = new Intent();
            intent.putExtra(ProductDisplayActivity.PROFILE_ID, profile);
            setResult(RESULT_OK, intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}