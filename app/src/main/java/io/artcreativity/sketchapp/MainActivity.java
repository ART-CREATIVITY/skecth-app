package io.artcreativity.sketchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.artcreativity.sketchapp.dao.DataBaseHelper;
import io.artcreativity.sketchapp.dao.ProductDao;
import io.artcreativity.sketchapp.models.Product;

public class MainActivity extends AppCompatActivity {

    private ListView productListView;
    private DataBaseHelper dataBaseHelper;
    List<Product> products = new ArrayList<>();
    ProductDao productDao;

    @Override // premiere fonction appelee
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productListView = findViewById(R.id.product_list);

        dataBaseHelper = DataBaseHelper.getInstance(this);
        productDao = new ProductDao(dataBaseHelper);
        products.addAll(productDao.findAll());
//        List<String> strs = new ArrayList<>();
//        for (int i=1; i<100; i++)  {
//            strs.add(String.valueOf(i));
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.simple_product, strs);
//        productListView.setAdapter(adapter);
//        List<Map<String, String>> building = products.stream().map(Product::toMap).collect(Collectors.toList());
        List<Map<String, String>> building = new ArrayList<>();
        for (Product product : products) {
            building.add(product.toMap());
        }
        SimpleAdapter adapter = new SimpleAdapter(this, building, R.layout.product_item_view, Product.FROM, Product.TO);

        productListView.setAdapter(adapter);
    }
    @Override // appelee apres le onCreate | Rappelee apres onStop si activite non detruite
    protected void onStart() {
        super.onStart();
    }
    @Override // appelee apres onStart | Elle est egalement appele du retour de onPause
    protected void onResume() {
        super.onResume();
    }
    @Override // appelee quand Android veut faire passer une interface d'une autre
    // application au dessus de notre application
    protected void onPause() {
        super.onPause();
    }
    @Override // appelee lorsque Android l'activite veut etre suspendu par Android
    protected void onStop() {
        super.onStop();
    }
    @Override // appelee lorsque l'activite veut etre detruite
    protected void onDestroy() {
        super.onDestroy();
    }
}