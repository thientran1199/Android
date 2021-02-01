package com.example.doanandroid02.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("price")
    @Expose
    public Integer price;
    @SerializedName("sale_price")
    @Expose
    public Integer salePrice;
    @SerializedName("category_id")
    @Expose
    public Integer categoryId;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("content")
    @Expose
    public String content;


    public Product(Integer id, String name, String slug, String image, Integer price, Integer salePrice, Integer categoryId, Integer status, String content) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.image = image;
        this.price = price;
        this.salePrice = salePrice;
        this.categoryId = categoryId;
        this.status = status;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", salePrice=" + salePrice +
                ", categoryId=" + categoryId +
                ", status=" + status +
                ", content='" + content + '\'' +
                '}';
    }
}
