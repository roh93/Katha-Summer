package com.example.rohit.kathaproject.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rohit.kathaproject.R;
import com.example.rohit.kathaproject.helpers.NewIssueAddInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rohit on 20-05-2017.
 */

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.ViewHolder> {

    private List<Bitmap> issueItemList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private ItemLongClickListener longClickListener;
    private ItemClickListener clickListener;
    private NewIssueAddInterface newIssueAddInterface;

    public IssuesAdapter(Context context, List<Bitmap> issueImageList) {
        issueItemList = issueImageList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(this.context);
        this.newIssueAddInterface = (NewIssueAddInterface) context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.issue_grid_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.issueName.setText("new");
        //***NEED TO ADD IMAGE VIEW HERE***//
        holder.issueImage.setImageBitmap(issueItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return issueItemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener{
        TextView issueName;
        ImageView issueImage;
        public ViewHolder(View itemView) {
            super(itemView);
            issueName = (TextView) itemView.findViewById(R.id.issue_item_tv);
            issueImage = (ImageView) itemView.findViewById(R.id.issue_item_iv);
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            if (longClickListener != null) longClickListener.onItemLongClick(v, getAdapterPosition());
            return true;
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public void setLongClickListener(ItemLongClickListener itemLongClickListener) {
        longClickListener = itemLongClickListener;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        clickListener = itemClickListener;
    }

    public interface ItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
