package io.artcreativity.sketchapp.activities.products;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.math.BigDecimal;

import io.artcreativity.sketchapp.R;
import io.artcreativity.sketchapp.metiers.ProductManager;
import io.artcreativity.sketchapp.metiers.ProductManagerImplOnline;
import io.artcreativity.sketchapp.models.Product;

public class EditProductActivity extends AppCompatActivity {
    private static final String TAG = "EditProductActivity";
    public static final int REQUEST_CODE = 3022;
    private TextInputEditText productName;
    private TextInputEditText stockAvailable;
    private TextInputEditText productPrice;
    private TextInputEditText productDescription;
    private CircularProgressIndicator loader;
    private Button btnSave;

    private Product product;
    ProductManager productManager = new ProductManagerImplOnline();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recuperation des elements graphiques definis dans le XML
        setContentView(R.layout.activity_edit_product);
        productName = findViewById(R.id.product_name);
        stockAvailable = findViewById(R.id.stock_available);
        productPrice = findViewById(R.id.product_price);
        productDescription = findViewById(R.id.product_description);
        loader = findViewById(R.id.loader);
        btnSave = findViewById(R.id.btn_save);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnSave.setOnClickListener(this::saveData);

        // Recuperation des elements passes dans l'Intent utilise pour demarrer l'activity
        Intent data = getIntent();
        product = (Product) data.getSerializableExtra(ProductDisplayActivity.PRODUCT_ID);
        if(product!=null) {
            productName.setText(product.name);
            productDescription.setText(product.description);
            stockAvailable.setText(String.valueOf(product.stockAvailable));
            productPrice.setText(product.price.toString());
        } else {
            product = new Product();
        }
    }

    public void saveData(View v) {
        Log.d(TAG, "saveData: action de sauvegarde");
        Toast.makeText(this, "Clique sur Save", Toast.LENGTH_LONG).show();

        // recuperation des nouvelle donnee
        product.name = productName.getText().toString();
        product.description = productDescription.getText().toString();
        product.stockAvailable = Double.parseDouble(stockAvailable.getText().toString());
        product.price = BigDecimal.valueOf(Double.parseDouble(productPrice.getText().toString()));
        btnSave.setVisibility(View.GONE);
        loader.setVisibility(View.VISIBLE);

        new Thread(() -> {
            Product p;
            if(product.id>0) {
                p = productManager.updateProduct(product.id, product);
            } else {
                p = productManager.createProduct(product);
            }
            Log.d(TAG, "saveData: id == " + p.id);
            runOnUiThread(() -> {
//                Snackbar.make(getApplicationContext(), loader, "Enregistrer avec Success", 5000).show();
                Intent intent = new Intent();
                intent.putExtra(ProductDisplayActivity.PRODUCT_ID, p);
                // Informer le system que l'activite a effectue sa tache succes grace au parametre Activity.RESULT_OK
                // intent contient les informations a retourne a l'activite precedente
                setResult(Activity.RESULT_OK, intent);
                // Mettre fin a l'activite courante
                finish();
            });
        }).start();
        // Creation d'une Intent pour envoyer les nouvelles informations dans le product

    }
}