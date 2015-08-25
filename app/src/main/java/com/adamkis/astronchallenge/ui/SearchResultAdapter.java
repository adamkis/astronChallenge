package com.adamkis.astronchallenge.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adamkis.astronchallenge.R;
import com.adamkis.astronchallenge.model.Attendee;
import com.adamkis.astronchallenge.network.VolleySingleton;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by adam on 7/26/15.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private final Context context;
    private List<Attendee> mDataset;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public NetworkImageView networkImageView;

        public ViewHolder(View v) {
            super(v);
            this.mTextView = (TextView) v.findViewById(R.id.mTextView);
            this.networkImageView = (NetworkImageView) v.findViewById(R.id.networkImageView);
        }
    }


    public SearchResultAdapter(Context context, List<Attendee> myDataset) {
        this.mDataset = myDataset;
        this.context = context;
    }


    @Override
    public SearchResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_card, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).toString());


//        holder.networkImageView.setImageUrl(
//                mDataset.get(position).getPhotoUrl(),
//                VolleySingleton.getInstance(context).getImageLoader());
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
