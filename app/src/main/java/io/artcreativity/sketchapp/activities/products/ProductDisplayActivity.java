package io.artcreativity.sketchapp.activities.products;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import io.artcreativity.sketchapp.databinding.ActivityProductDisplayBinding;

import io.artcreativity.sketchapp.R;
import io.artcreativity.sketchapp.models.Product;
import io.artcreativity.sketchapp.utils.BuildData;

public class ProductDisplayActivity extends AppCompatActivity {

    public static final String PRODUCT_ID = "product";
    private ActivityProductDisplayBinding binding;
    private Product product = BuildData.generateProduct();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProductDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        binding.productName.setText(product.name);
//        binding.productImage.setImageResource(product.image);
        binding.stockAvailable.setText(String.format("%s", product.stockAvailable));
        binding.productDescription.setText(product.description);
        binding.author.setText(product.author.fullName());
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
                Toast.makeText(this, "Clique sur Modification", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile_item:
                // TODO: 30/09/2022 Operation pour modifier le profile
                Toast.makeText(this, "Clique sur profil", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(this, p.toString(), Toast.LENGTH_LONG).show();
                }
        }
    }
}