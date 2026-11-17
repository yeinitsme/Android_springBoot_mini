
package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder> {

    private List<Memo> memoList = new ArrayList<>();

    public void setMemoList(List<Memo> memoList) {
        this.memoList = memoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_memo, parent, false);
        return new MemoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoViewHolder holder, int position) {
        Memo memo = memoList.get(position);

        holder.tvMemoTitle.setText(memo.getTitle());
        holder.tvMemoContent.setText(memo.getContent());
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }

    static class MemoViewHolder extends RecyclerView.ViewHolder {
        TextView tvMemoTitle;
        TextView tvMemoContent;

        public MemoViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMemoTitle = itemView.findViewById(R.id.tvMemoTitle);
            tvMemoContent = itemView.findViewById(R.id.tvMemoContent);
        }
    }
}
