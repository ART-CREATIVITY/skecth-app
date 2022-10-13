package io.artcreativity.sketchapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import java.util.ArrayList;
import java.util.List;

import io.artcreativity.sketchapp.R;
import io.artcreativity.sketchapp.metiers.ProductManager;
import io.artcreativity.sketchapp.models.Product;

public class ProductAdapter extends BaseAdapter {

    public List<Product> products = new ArrayList<>();

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return products.get(i).id;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        LinearLayoutCompat layout = (LinearLayoutCompat) inflater.inflate(R.layout.product_item_view, viewGroup, false);

        Product product = products.get(i);
        ((TextView)layout.findViewById(R.id.product_name)).setText(product.name);
        ((TextView)layout.findViewById(R.id.stock_available)).setText(String.format("%s", product.stockAvailable));
        ((TextView)layout.findViewById(R.id.product_price)).setText(product.price.toString());

        return layout;
    }
}
