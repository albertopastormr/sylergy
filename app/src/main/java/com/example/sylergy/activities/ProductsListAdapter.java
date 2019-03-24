package com.example.sylergy.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.sylergy.R;
import com.example.sylergy.objects.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ViewHolder> {

    private List<Product> products;
    private android.content.Context context;
    private OnItemClickListener listener;

    public ProductsListAdapter(List<Product> products, OnItemClickListener listener,
                               Context context) {
        this.products = products;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productlist_item,
                parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Product product = products.get(i);

        viewHolder.setListener(product,listener);

        Picasso.with(context).load(product.getImage()).into(viewHolder.productslistItemImage);
        viewHolder.productslistItemName.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void add(Product product){
        if(!products.contains(product)) {
            products.add(product);
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.productslist_item_Image)
        AppCompatImageView productslistItemImage;
        @BindView(R.id.productslist_item_name)
        AppCompatTextView productslistItemName;
        @BindView(R.id.container_listitem)
        RelativeLayout containerListitem;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void setListener(final Product product, final OnItemClickListener listener) {
            containerListitem.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    listener.onItemClick(product);
                }
            });

            containerListitem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemClick(product);
                    return true;
                }
            });
        }


    }
}
