package com.budi.submission.receipe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.budi.submission.receipe.model.Receipe;
import com.budi.submission.receipe.databinding.ActivityDetailBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    Receipe receipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        setSupportActionBar(binding.toolbar);
        if(getIntent().hasExtra("data")){
            receipe = getIntent().getExtras().getParcelable("data");
        }
        else {
            receipe = new Receipe("Data Tidak Ada Bro");
        }
        Log.e("budi", "onCreate: "+receipe.toString() );
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.setData(receipe);
        setBahan(receipe);
        setPembuatan(receipe);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(receipe.getSumber())));
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setBahan(Receipe recipe){
        binding.llBahan.removeAllViews();

        if(recipe.getBahan() != null){
            for(String bahan: recipe.getBahan()){
                TextView textView = new TextView(this);
                textView.setText(getString(R.string.list, bahan  ));
                textView.setTextSize(15);
                textView.setPadding(5,5,5,5);
                textView.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                binding.llBahan.addView(textView);
            }
        }
        else{
            TextView textView = new TextView(this);
            textView.setText("Bahan Tidak Ada");
            textView.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            binding.llBahan.addView(textView);
        }
    }
    private void setPembuatan(Receipe recipe){
        binding.llCaraBuat.removeAllViews();
        if(recipe.getStep() != null){
            int i =1;
            for(String step: recipe.getStep()){
                TextView textView = new TextView(this);
                textView.setText(step);
                textView.setText(getString(R.string.list2,i,step));
                textView.setTextSize(16);
                textView.setPadding(5,15,5,15);
                textView.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                i++;
                binding.llCaraBuat.addView(textView);
            }
        }
        else{
            TextView textView = new TextView(this);
            textView.setText("Cara Pembuatan Tidak Ada");
            textView.setTextSize(15);
            textView.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            binding.llCaraBuat.addView(textView);
        }
    }
}
