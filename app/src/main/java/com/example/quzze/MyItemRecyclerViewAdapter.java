package com.example.quzze;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quzze.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.quzze.databinding.FragmentHistoryBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderItem> mValues;

    public MyItemRecyclerViewAdapter(List<PlaceholderItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        PlaceholderItem item = mValues.get(position);

        // Set values in the view holder
        holder.tvResultStatus.setText(item.score >= item.total / 2 ? "You Won!" : "You Lost!"); // Simple logic for win/loss
        holder.tvScore.setText("Score: " + item.score + " out of " + item.total);
        holder.tvContinent.setText("Continent: " + item.continent);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvResultStatus;
        public final TextView tvScore;
        public final TextView tvContinent;
        public PlaceholderItem mItem;

        public ViewHolder(FragmentHistoryBinding binding) {
            super(binding.getRoot());
            tvResultStatus = binding.tvResultStatus; // Corresponding TextView in your XML
            tvScore = binding.tvScore;
            tvContinent = binding.tvContinent;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvScore.getText() + "'";
        }
    }
}
