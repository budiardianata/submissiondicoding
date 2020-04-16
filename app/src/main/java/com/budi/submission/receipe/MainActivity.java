package com.budi.submission.receipe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.budi.submission.receipe.adapter.ReceipeAdapter;
import com.budi.submission.receipe.adapter.onReceipeClick;
import com.budi.submission.receipe.databinding.ActivityMainBinding;
import com.budi.submission.receipe.model.Receipe;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements onReceipeClick {
    private ActivityMainBinding binding;
    List<Receipe> receipes = new ArrayList<>();
    ReceipeAdapter adapter;
    GridLayoutManager gridLayoutManager;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        receipes = Arrays.asList(readJsonFile());
        binding.toolbar.setTitle("Masakan Khas Indonesia");
        setSupportActionBar(binding.toolbar);
        gridLayoutManager = new GridLayoutManager(this, App.getLayout());
        adapter =new ReceipeAdapter(gridLayoutManager,receipes, this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        binding.listTitle.setText("List");
        binding.tongle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    binding.listTitle.setText("Grid");
                    gridLayoutManager.setSpanCount(2);
                    SettingHelper.applyLayout(2);
                }else {
                    binding.listTitle.setText("List");
                    gridLayoutManager.setSpanCount(1);
                    SettingHelper.applyLayout(1);
                }
                adapter.notifyItemRangeChanged(0, adapter.getItemCount());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        gridLayoutManager.setSpanCount(App.getLayout());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public Receipe[] readJsonFile() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream in = getResources().openRawResource(R.raw.data);
        byte[] buf = new byte[1024];
        int len;
        try {
            while ((len = in.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            in.close();
        } catch (IOException e) {
            Log.e(TAG, "readJsonFile: ",e );
        }
        return new Gson().fromJson(outputStream.toString(), Receipe[].class);
    }

    @Override
    public void onClicked(Receipe receipe, ImageView imageView) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("data", receipe);
        ActivityOptionsCompat.makeSceneTransitionAnimation(this,imageView,imageView.getTransitionName());
        startActivity(i, ActivityOptionsCompat.makeSceneTransitionAnimation(this,imageView, Objects.requireNonNull(ViewCompat.getTransitionName(imageView))).toBundle());
    }

    @Override
    public void onBackPressed() {
        final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("KELUAR");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_theme);
        builder.setMessage("Anda ingin keluar dari aplikasi?");
        builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
