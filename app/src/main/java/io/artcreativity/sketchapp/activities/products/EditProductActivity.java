package io.artcreativity.sketchapp.activities.products;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.math.BigDecimal;

import io.artcreativity.sketchapp.R;
import io.artcreativity.sketchapp.models.Product;

public class EditProductActivity extends AppCompatActivity {
    private static final String TAG = "EditProductActivity";
    public static final int REQUEST_CODE = 3022;
    private TextInputEditText productName;
    private TextInputEditText stockAvailable;
    private TextInputEditText productPrice;
    private TextInputEditText productDescription;

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recuperation des elements graphiques definis dans le XML
        setContentView(R.layout.activity_edit_product);
        productName = findViewById(R.id.product_name);
        stockAvailable = findViewById(R.id.stock_available);
        productPrice = findViewById(R.id.product_price);
        productDescription = findViewById(R.id.product_description);

        // Recuperation des elements passes dans l'Intent utilise pour demarrer l'activity
        Intent data = getIntent();
        product = (Product) data.getSerializableExtra(ProductDisplayActivity.PRODUCT_ID);
        productName.setText(product.name);
        productDescription.setText(product.description);
        stockAvailable.setText(String.valueOf(product.stockAvailable));
        productPrice.setText(product.price.toString());
    }

    public void saveData(View v) {
        Log.d(TAG, "saveData: action de sauvegarde");
        Toast.makeText(this, "Clique sur Save", Toast.LENGTH_LONG).show();

        // recuperation des nouvelle donnee
        product.name = productName.getText().toString();
        product.description = productDescription.getText().toString();
        product.stockAvailable = Double.parseDouble(stockAvailable.getText().toString());
        product.price = BigDecimal.valueOf(Double.parseDouble(productPrice.getText().toString()));

        // Creation d'une Intent pour envoyer les nouvelles informations dans le product
        Intent intent = new Intent();
        intent.putExtra(ProductDisplayActivity.PRODUCT_ID, product);
        // Informer le system que l'activite a effectue sa tache succes grace au parametre Activity.RESULT_OK
        // intent contient les informations a retourne a l'activite precedente
        setResult(Activity.RESULT_OK, intent);
        // Mettre fin a l'activite courante
        finish();
    }
}