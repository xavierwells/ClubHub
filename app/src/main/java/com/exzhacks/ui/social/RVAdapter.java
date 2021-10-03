package com.exzhacks.ui.social;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.exzhacks.Organization;
import com.exzhacks.R;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    private ArrayList<Organization> localDataSet;

    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        private final TextView textViewType;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
            textViewType = (TextView) view.findViewById(R.id.textViewType);
        }

        public TextView getTextView() {
            return textView;
        }
        public TextView getTextViewType(){
            return textViewType;
        }

    }


    public RVAdapter(ArrayList<Organization> dataSet) {
        localDataSet = dataSet;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(localDataSet.get(position).getName());
        viewHolder.getTextViewType().setText(localDataSet.get(position).getSummary());
        viewHolder.getTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });
        viewHolder.getTextViewType().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }

    public void updateList(ArrayList<Organization> list){
        localDataSet = list;
        notifyDataSetChanged();
    }
}

