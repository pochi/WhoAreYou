package com.example.pochi.whoareyou;

import android.graphics.drawable.BitmapDrawable;

import com.google.android.glass.app.Card;

/**
 * Created by pochi on 2015/06/01.
 */
public class ResultCard {
    private String imageUrl;
    private String text;
    private String footerText;
    private Card.ImageLayout imgLayout;
    private int[] images;
    private BitmapDrawable image;

    public ResultCard(String imageUrl, String contents) {
        this.imageUrl = imageUrl;
        this.text = contents;
    }

    public void setImage(BitmapDrawable bitmap) {
        this.image = bitmap;
    }

    public BitmapDrawable getImage() {
        return this.image;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFooterText() {
        return footerText;
    }

    public void setFooterText(String footerText) {
        this.footerText = footerText;
    }

    public Card.ImageLayout getImgLayout() {
        return Card.ImageLayout.FULL;
    }

    public void setImgLayout(Card.ImageLayout imgLayout) {
        this.imgLayout = imgLayout;
    }

    public int[] getImages() {
        return images;
    }

    public void setImages(int[] images) {
        this.images = images;
    }
}
