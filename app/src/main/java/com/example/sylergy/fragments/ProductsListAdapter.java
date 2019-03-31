package com.example.sylergy.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sylergy.R;
import com.example.sylergy.objects.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductsListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> arrayListProduct;

    public ProductsListAdapter(Context context, ArrayList<Product> arrayListProduct){
        this.context = context;
        this.arrayListProduct = arrayListProduct;
    }

    @Override
    public int getCount() {
        return arrayListProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService
                    (context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.productlist_item, null);
        }

        ImageView imageProduct = (ImageView) convertView.findViewById(R.id.productslist_item_Image);
        TextView textViewProduct = (TextView) convertView.findViewById(R.id.productslist_item_name);
        textViewProduct.setText(arrayListProduct.get(position).getName());
        try {
            Picasso.with(context).load(arrayListProduct.get(position).getImage()).into(imageProduct);
        } catch (Exception e) {
            Picasso.with(context).load(R.drawable.logo_v3).into(imageProduct);
        }
        return convertView;
    }
}
