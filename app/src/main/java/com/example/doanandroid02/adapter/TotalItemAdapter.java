package com.example.doanandroid02.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid02.R;
import com.example.doanandroid02.activity.CartActivity;
import com.example.doanandroid02.models.Cart;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class TotalItemAdapter extends RecyclerView.Adapter<TotalItemAdapter.ItemHolder>{


    List<Cart> carts;
    Context context;

    public TotalItemAdapter(List<Cart> carts, Context context) {
        this.carts = carts;
        this.context = context;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private TextView textProductNameCrt, textGiaSpCrtquant, quant;
        private ImageView imageTotal;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            quant = itemView.findViewById(R.id.quantTotal);
            textProductNameCrt = itemView.findViewById(R.id.textProductTotal);
            textGiaSpCrtquant = itemView.findViewById(R.id.textGiaTotal);
            imageTotal = itemView.findViewById(R.id.imageTotal);
        }

    }

    @NonNull
    @Override
    public TotalItemAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_total_item, null);
        TotalItemAdapter.ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TotalItemAdapter.ItemHolder holder, int position) {
        Cart cart = carts.get(position);
        holder.textProductNameCrt.setText(cart.tensp);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.textGiaSpCrtquant.setText(decimalFormat.format(cart.giasp) + "VND");
        holder.quant.setText("x" + (cart.soluongsp));
        Picasso.with(context).load("http://127.0.0.1/DoAnLaravel_2/public/uploads/" + cart.getimage())
                .into(holder.imageTotal);
        CartActivity.countPrice(context);

    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

}
