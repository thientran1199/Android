package com.example.doanandroid02.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid02.ItemClickListener;
import com.example.doanandroid02.R;
import com.example.doanandroid02.activity.ProductDetailsActivity;
import com.example.doanandroid02.models.Product;
import com.example.doanandroid02.repositories.ProductRepository;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> {
    List<Product> products;
    Context context;

    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ImageView imageSanPham;
        public TextView textTenSp;
        public TextView textGiaSp;
        public CardView cardView;
        public ItemClickListener itemClickListener;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imageSanPham = itemView.findViewById(R.id.imageSanPham);
            textTenSp = itemView.findViewById(R.id.textTenSp);
            textGiaSp = itemView.findViewById(R.id.textGiaSp);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(this);
            cardView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return false;
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product, null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Product product = products.get(position);
        holder.textTenSp.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.textGiaSp.setText(decimalFormat.format(product.price) + "VND");
        Picasso.with(context).load("http://192.168.1.7/DoAnLaravel_2/public/uploads/" + product.getImage())
                .resize(1000, 1000)
                .centerCrop()
                .into(holder.imageSanPham);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                int id = ProductRepository.products.get(position).getId();
                String product_name = ProductRepository.products.get(position).getName();
                String product_image = ProductRepository.products.get(position).getImage();
                long product_price = ProductRepository.products.get(position).getPrice();
                String product_inf = ProductRepository.products.get(position).getContent();

                intent.putExtra("id", id);
                intent.putExtra("name", product_name);
                intent.putExtra("image", product_image);
                intent.putExtra("price", product_price);
                intent.putExtra("content", product_inf);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return products.size();
    }


}
