package io.artcreativity.sketchapp.activities.products;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Currency;
import java.util.Locale;

import io.artcreativity.sketchapp.activities.profile.ProfileActivity;
import io.artcreativity.sketchapp.databinding.ActivityProductDisplayBinding;

import io.artcreativity.sketchapp.R;
import io.artcreativity.sketchapp.models.Product;
import io.artcreativity.sketchapp.models.Profile;
import io.artcreativity.sketchapp.utils.BuildData;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ProductDisplayActivity extends AppCompatActivity {

    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
    public static final String PRODUCT_ID = "product";
    public static final String PROFILE_ID = "profile";
    private ActivityProductDisplayBinding binding;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProductDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        product = (Product) getIntent().getSerializableExtra(PRODUCT_ID);
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        binding.productName.setText(product.name);
//        binding.productImage.setImageResource(product.image);
        binding.stockAvailable.setText(String.format("%s", product.stockAvailable));
        binding.productDescription.setText(product.description);
        if(product.author!=null)
            binding.author.setText(product.author.fullName());
        binding.productPrice.setText(numberFormat.format(product.price));

// ok

        // Ecoute du clique sur le FloatingActionButton
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialisation d'une intension (Intent) pour demarrer l'activity EditProductActivity
                Intent intent = new Intent(getApplicationContext(), EditProductActivity.class);
                // Transmission d'objet dans l'Intent
                intent.putExtra(ProductDisplayActivity.PRODUCT_ID, product);
                // Lancement de demarrage d'une nouvelle activity
                startActivityForResult(intent, EditProductActivity.REQUEST_CODE);
            }
        });

        BuildData.testDB(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(ProductDisplayActivity.this);
        inflater.inflate(R.menu.product_display_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit_item:
                // TODO: 30/09/2022 Operation pour mettre a jour le produit
                // Initialisation d'une intension (Intent) pour demarrer l'activity EditProductActivity
                Intent editItemIntent = new Intent(getApplicationContext(), EditProductActivity.class);
                // Transmission d'objet dans l'Intent
                editItemIntent.putExtra(ProductDisplayActivity.PRODUCT_ID, product);
                // Lancement de demarrage d'une nouvelle activity
                startActivityForResult(editItemIntent, EditProductActivity.REQUEST_CODE);
                Toast.makeText(this, "Clique sur Modification", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile_item:
                // TODO: 30/09/2022 Operation pour modifier le profile
                // Initialisation d'une intension (Intent) pour demarrer l'activity ProfileActivity
                Intent profileItemIntent = new Intent(getApplicationContext(), ProfileActivity.class);
                // Transmission d'objet dans l'Intent
                profileItemIntent.putExtra(ProductDisplayActivity.PROFILE_ID, product.author);
                // Lancement de demarrage d'une nouvelle activity
                startActivityForResult(profileItemIntent, ProfileActivity.REQUEST_CODE);
                Toast.makeText(this, "Clique sur profil", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case EditProductActivity.REQUEST_CODE:
                if(resultCode == Activity.RESULT_OK && data!=null) {
                    Product p = (Product) data.getSerializableExtra(PRODUCT_ID);
                    product.name = p.name;
                    product.stockAvailable = p.stockAvailable;
                    product.price = p.price;
                    product.description = p.description;
                    binding.productName.setText(product.name);
                    binding.stockAvailable.setText(String.format("%s", product.stockAvailable));
                    binding.productPrice.setText(numberFormat.format(product.price));
                    binding.productDescription.setText(product.description);
                    Toast.makeText(this, p.toString(), Toast.LENGTH_LONG).show();
                }
                break;

            case ProfileActivity.REQUEST_CODE:
                if(resultCode == Activity.RESULT_OK && data!=null) {
                    Profile p = (Profile) data.getSerializableExtra(PROFILE_ID);
                    product.author = p;
                    binding.author.setText(p.fullName());
                    Toast.makeText(this, p.toString(), Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}