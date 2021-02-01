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
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class ProductByIdAdapter extends RecyclerView.Adapter<ProductByIdAdapter.ItemHolder>{
    List<Product> products;
    Context context;

    public ProductByIdAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

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
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
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
        Picasso.with(context).load("http://127.0.0.1/DoAnLaravel_2/public/uploads/" + product.getImage())
                .resize(1000, 1000)
                .centerCrop()
                .into(holder.imageSanPham);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(context, " " + products.get(position), Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    int id = products.get(position).getId();
                    String product_name = products.get(position).getName();
                    String product_image = products.get(position).getImage();
                    long product_price = products.get(position).getPrice();
                    String product_inf = products.get(position).getContent();

                    intent.putExtra("id",id);
                    intent.putExtra("name", product_name);
                    intent.putExtra("image", product_image);
                    intent.putExtra("price", product_price);
                    intent.putExtra("content", product_inf);
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }


}
