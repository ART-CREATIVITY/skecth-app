package io.artcreativity.sketchapp;

import static io.artcreativity.sketchapp.activities.products.ProductDisplayActivity.PRODUCT_ID;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.artcreativity.sketchapp.activities.products.EditProductActivity;
import io.artcreativity.sketchapp.activities.products.ProductDisplayActivity;
import io.artcreativity.sketchapp.adapters.ProductAdapter;
import io.artcreativity.sketchapp.dao.DataBaseHelper;
import io.artcreativity.sketchapp.dao.ProductDao;
import io.artcreativity.sketchapp.metiers.ProductManager;
import io.artcreativity.sketchapp.metiers.ProductManagerImplOnline;
import io.artcreativity.sketchapp.models.Product;

public class MainActivity extends AppCompatActivity {

    private static int CREATE_REQUEST = 405;
    private static int DISPLAY_REQUEST = 405;
    private ListView productListView;
    private DataBaseHelper dataBaseHelper;
    List<Product> products = new ArrayList<>();
    ProductDao productDao;
    FloatingActionButton fab;
    CircularProgressIndicator loader;
    ProductManager productManager = new ProductManagerImplOnline();
    ProductAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override // premiere fonction appelee
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productListView = findViewById(R.id.product_list);
        fab = findViewById(R.id.fab);
        loader= findViewById(R.id.loader);

        dataBaseHelper = DataBaseHelper.getInstance(this);
        productDao = new ProductDao(dataBaseHelper);
//        products.addAll(productDao.findAll());
//        List<String> strs = new ArrayList<>();
//        for (int i=1; i<100; i++)  {
//            strs.add(String.valueOf(i));
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.simple_product, strs);
//        productListView.setAdapter(adapter);
//        List<Map<String, String>> building = products.stream().map(Product::toMap).collect(Collectors.toList());
//        List<Map<String, String>> building = new ArrayList<>();
//        for (Product product : products) {
//            building.add(product.toMap());
//        }
//        adapter = new SimpleAdapter(this, building, R.layout.product_item_view, Product.FROM, Product.TO);
        adapter = new ProductAdapter(products);

        productListView.setAdapter(adapter);

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EditProductActivity.class);
            startActivityIfNeeded(intent, CREATE_REQUEST);
        });

        productListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MainActivity.this, ProductDisplayActivity.class);
            intent.putExtra(PRODUCT_ID, products.get(i));
            startActivityIfNeeded(intent, DISPLAY_REQUEST);
        });
    }
    @Override // appelee apres le onCreate | Rappelee apres onStop si activite non detruite
    protected void onStart() {
        super.onStart();
        fetchData();
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

    private void fetchData() {
        new Thread(() -> {
            List<Product> ps = productManager.findAll();
            runOnUiThread(() -> {
                adapter.products.addAll(ps);
                adapter.notifyDataSetChanged();
                loader.setVisibility(View.GONE);
            });
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CREATE_REQUEST){
            if(resultCode == RESULT_OK && data!=null) {
                Product product = (Product) data.getSerializableExtra("product");
                adapter.products.set(0, product);
                adapter.notifyDataSetChanged();
            }
        }
    }
}