package io.artcreativity.sketchapp.utils;

import android.content.Context;
import android.util.Log;

import java.math.BigDecimal;
import java.util.List;

import io.artcreativity.sketchapp.R;
import io.artcreativity.sketchapp.dao.DataBaseHelper;
import io.artcreativity.sketchapp.dao.ProductDao;
import io.artcreativity.sketchapp.models.Product;
import io.artcreativity.sketchapp.models.Profile;

public class BuildData {

    public static Product generateProduct() {
        return new Product(1, "Mercedes Benz Class C",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "R.drawable.mercedes_classe_c",
                new Profile("Burgady", "TOTO", ""),
                230, BigDecimal.valueOf(50_000));
    }

    public static void testDB(Context context) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance(context);
        ProductDao productDao = new ProductDao(dataBaseHelper);
        Product product = productDao.createProduct(generateProduct());
        List<Product> products = productDao.findAll();
        for (Product product1 : products) {
            Log.d("TAG", "testDB: " + product1);
        }
    }
}
