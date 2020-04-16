package com.budi.submission.receipe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.budi.submission.receipe.model.Receipe;
import com.budi.submission.receipe.databinding.ItemGridBinding;
import com.budi.submission.receipe.databinding.ItemListBinding;

import java.util.List;


/**
 * Created by Budi Ardianata on 15/04/2020.
 * Project: submission
 * Email: budiardianata@windowslive.com
 */
public class ReceipeAdapter extends RecyclerView.Adapter<ReceipeAdapter.ReceipeViewHolder> {
    private static final int VIEW_LIST = 1;
    private static final int VIEW_GRID = 2;
    private GridLayoutManager layoutManager;
    private List<Receipe> receipes;
    private onReceipeClick onReceipeClick;

    public ReceipeAdapter(GridLayoutManager layoutManager, List<Receipe> receipes, com.budi.submission.receipe.adapter.onReceipeClick onReceipeClick) {
        this.layoutManager = layoutManager;
        this.receipes = receipes;
        this.onReceipeClick = onReceipeClick;
    }

    @NonNull
    @Override
    public ReceipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_LIST) {
            ItemListBinding binding = ItemListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ReceipeViewHolder(binding, onReceipeClick);
        } else {
            ItemGridBinding binding = ItemGridBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ReceipeViewHolder(binding, onReceipeClick);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ReceipeViewHolder holder, int position) {
        if (getItemViewType(position)==VIEW_LIST) {
            holder.itemListBinding.setData(receipes.get(position));
        }else {
            holder.itemGridBinding.setData(receipes.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return receipes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return layoutManager.getSpanCount() == 1 ? VIEW_LIST : VIEW_GRID;
    }

    static class ReceipeViewHolder extends RecyclerView.ViewHolder {
        ItemListBinding itemListBinding;
        ItemGridBinding itemGridBinding;

        ReceipeViewHolder(final ItemListBinding itemListBinding, final onReceipeClick click) {
            super(itemListBinding.getRoot());
            this.itemListBinding = itemListBinding;
            itemListBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.onClicked(itemListBinding.getData(), itemListBinding.gambar);
                }
            });
        }

        ReceipeViewHolder(final ItemGridBinding itemGridBinding, final onReceipeClick click) {
            super(itemGridBinding.getRoot());
            this.itemGridBinding = itemGridBinding;
            itemGridBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.onClicked(itemGridBinding.getData(), itemGridBinding.gambar);
                }
            });
        }
    }
}
