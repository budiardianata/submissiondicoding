package com.budi.submission.receipe.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.budi.submission.receipe.R;
import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;


/**
 * Created by Budi Ardianata on 15/04/2020.
 * Project: submission
 * Email: budiardianata@windowslive.com
 */
public class Receipe extends BaseObservable implements Parcelable {
    @SerializedName("judul")
    private String judul;
    @SerializedName("image")
    private String image = "https://via.placeholder.com/150x150";
    @SerializedName("deskripsi")
    private String deskripsi = null;
    @SerializedName("sumber")
    private String sumber = null;
    @SerializedName("bahan")
    private String[] bahan = null;

    @SerializedName("step")
    private String[] step = null;

    public Receipe() {
    }

    public Receipe(String judul) {
        this.judul = judul;
    }

    protected Receipe(Parcel in) {
        judul = in.readString();
        image = in.readString();
        deskripsi = in.readString();
        sumber = in.readString();
        bahan = in.createStringArray();
        step = in.createStringArray();
    }

    public static final Creator<Receipe> CREATOR = new Creator<Receipe>() {
        @Override
        public Receipe createFromParcel(Parcel in) {
            return new Receipe(in);
        }

        @Override
        public Receipe[] newArray(int size) {
            return new Receipe[size];
        }
    };

    @Bindable
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    @Bindable
    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    @Bindable
    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

    @Bindable
    public String[] getBahan() {
        return bahan;
    }

    public void setBahan(String[] bahan) {
        this.bahan = bahan;
    }

    @Bindable
    public String[] getStep() {
        return step;
    }

    public void setStep(String[] step) {
        this.step = step;
    }
    @BindingAdapter({ "gambar" })
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(Uri.parse("file:///android_asset/"+url))
                .placeholder(R.drawable.ic_image_placeholder)
                .into(imageView);
    }



    @Override
    public String toString() {
        return "Receipe{" +
                "judul='" + judul + '\'' +
                ", image='" + image + '\'' +
                ", deskripsi='" + deskripsi + '\'' +
                ", sumber='" + sumber + '\'' +
                ", bahan=" + Arrays.toString(bahan) +
                ", step=" + Arrays.toString(step) +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(judul);
        dest.writeString(image);
        dest.writeString(deskripsi);
        dest.writeString(sumber);
        dest.writeStringArray(bahan);
        dest.writeStringArray(step);
    }
}
