package io.artcreativity.sketchapp.dao;

import static io.artcreativity.sketchapp.models.Product.COLUMN_DESCRIPTION;
import static io.artcreativity.sketchapp.models.Product.COLUMN_IMAGE;
import static io.artcreativity.sketchapp.models.Product.COLUMN_NAME;
import static io.artcreativity.sketchapp.models.Product.COLUMN_PRICE;
import static io.artcreativity.sketchapp.models.Product.COLUMN_STOCK_AVAILABLE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.artcreativity.sketchapp.models.Product;

public class ProductDao {
    private final DataBaseHelper dataBaseHelper;


    public ProductDao(DataBaseHelper dataBaseHelper) {
        this.dataBaseHelper = dataBaseHelper;
    }

    public Product createProduct(Product product) {
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, product.name);
        contentValues.put(Product.COLUMN_DESCRIPTION, product.description);
        contentValues.put(Product.COLUMN_IMAGE, product.image);
        if(product.author!=null)
            contentValues.put(Product.COLUMN_AUTHOR_ID, product.author.id);
        contentValues.put(Product.COLUMN_STOCK_AVAILABLE, product.stockAvailable);
        contentValues.put(Product.COLUMN_PRICE, product.price.doubleValue());

        long id = database.insert(Product.TABLE, null, contentValues);
        product.id = id;

        return product;
    }

    @SuppressLint("Range")
    public List<Product> findAll() {
        SQLiteDatabase database = dataBaseHelper.getReadableDatabase();

        Cursor cursor = database.query(Product.TABLE, new String[]{}, "", new String[]{}, "", "", "");
        List<Product> products = new ArrayList<>();
        while(cursor.moveToNext()) {
            Product product = new Product();
            product.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            product.description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
            product.image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));
            product.stockAvailable = cursor.getDouble(cursor.getColumnIndex(COLUMN_STOCK_AVAILABLE));
            product.price = BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)));

            products.add(product);
        }

        return products;
    }
}
