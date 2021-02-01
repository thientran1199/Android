package com.example.doanandroid02.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanandroid02.CheckLoginRemember;
import com.example.doanandroid02.PrefConfig;
import com.example.doanandroid02.R;
import com.example.doanandroid02.models.Cart;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ProductDetailsActivity extends AppCompatActivity {

    TextView textTenChiTietSp;
    TextView textGiaChiTietSp;
    TextView textChiTietSp;
    ImageView imageChiTietSp;
    Button btAddCard;
    private static Long price;
    public static int id;
    public static String name;
    public static String image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

       ActionBar actionBar = getSupportActionBar();
       actionBar.setTitle(null);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textTenChiTietSp = findViewById(R.id.textTenChiTietSp);
        textGiaChiTietSp = findViewById(R.id.textGiaChiTietSp);
        textChiTietSp = findViewById(R.id.textChiTietSp);
        imageChiTietSp = findViewById(R.id.imageChiTietSp);
        btAddCard = findViewById(R.id.btAddCard);

        getProductDetails();

        btAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckLoginRemember.checkLoginRemember(getApplicationContext())>0){
                    card();
                }else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
            case R.id.card:
                if(CheckLoginRemember.checkLoginRemember(getApplicationContext())>0){
                    Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void getProductDetails() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        price = intent.getLongExtra("price", 0);
        String content = intent.getStringExtra("content");
        image = intent.getStringExtra("image");

        textTenChiTietSp.setText(name);
        textGiaChiTietSp.setText(decimalFormat.format(price) + "VND");
        textChiTietSp.setText(Html.fromHtml(content));
        Picasso.with(getApplicationContext()).load("http://127.0.0.1/DoAnLaravel_2/public/uploads/" + image).into(imageChiTietSp);
    }

    public void card() {
        if (MainActivity.cartArrayList.size() > 0) {
            int soln = 1;
            boolean exist = false; // tạo đk tồn tại sản phẩm
            for (int i = 0; i < MainActivity.cartArrayList.size(); i++) {
                if (id == MainActivity.cartArrayList.get(i).getIdsp()) {
                    // Nếu sp cùng id vs sp trong mảng thì tiến hành cập nhật giá và sl

                    MainActivity.cartArrayList.get(i).setSoluongsp(MainActivity.cartArrayList.get(i).getSoluongsp() + soln);
                    MainActivity.cartArrayList.get(i).setGiasp(price * MainActivity.cartArrayList.get(i).getSoluongsp());
                    Toast.makeText(ProductDetailsActivity.this, "update success", Toast.LENGTH_SHORT).show();
                    PrefConfig.writeList(getApplicationContext(),MainActivity.cartArrayList);
                    exist = true;
                }
            }

            if (exist == false) {
                // Nếu không thì tiến hàng thêm mới
                int soluong = 1;
                Long newprice = soluong * price;
                Cart cart = new Cart(id, name, newprice, soluong, image);
                MainActivity.cartArrayList.add(cart);
                PrefConfig.writeList(getApplicationContext(), MainActivity.cartArrayList);
                Toast.makeText(ProductDetailsActivity.this, "add success", Toast.LENGTH_SHORT).show();
            }

        } else {
            int soluong = 1;
            Long newprice = soluong * price;
            Cart cart = new Cart(id, name, newprice, soluong, image);
            MainActivity.cartArrayList.add(cart);
            PrefConfig.writeList(getApplicationContext(), MainActivity.cartArrayList);
            Toast.makeText(ProductDetailsActivity.this, "add success", Toast.LENGTH_SHORT).show();
        }
    }

}
