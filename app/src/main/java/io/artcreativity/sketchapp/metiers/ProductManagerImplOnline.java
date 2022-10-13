package io.artcreativity.sketchapp.metiers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.artcreativity.sketchapp.models.Product;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProductManagerImplOnline implements ProductManager {

    private OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private Gson gson = new Gson();
    private String PRODUCT_URL = "http://54.162.15.114:8080/sketch/products";


    @Override
    public Product createProduct(Product product) {
        String data = gson.toJson(product);
        try {
            String res = post(PRODUCT_URL, data);
            return gson.fromJson(res, Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        try {
            String res = get(PRODUCT_URL);
            List<Product> products = gson.fromJson(res, new TypeToken<List<Product>>(){}.getType());

            return products;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Product updateProduct(long productId, Product product) {
        // TODO: 13/10/2022 Implement update function
        return product;
    }

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
