package io.artcreativity.sketchapp.activities.products;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import io.artcreativity.sketchapp.databinding.ActivityProductDisplayBinding;

import io.artcreativity.sketchapp.R;
import io.artcreativity.sketchapp.models.Product;
import io.artcreativity.sketchapp.utils.BuildData;

public class ProductDisplayActivity extends AppCompatActivity {

    private ActivityProductDisplayBinding binding;
    private Product product = BuildData.generateProduct();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProductDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        binding.productName.setText(product.name);
        binding.productImage.setImageResource(product.image);
        binding.stockAvailable.setText(String.format("%s", product.stockAvailable));
        binding.productDescription.setText(product.description);
        binding.author.setText(product.author.fullName());
// ok

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}