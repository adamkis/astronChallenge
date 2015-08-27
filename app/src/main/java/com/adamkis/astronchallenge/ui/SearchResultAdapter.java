package com.adamkis.astronchallenge.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adamkis.astronchallenge.R;
import com.adamkis.astronchallenge.common.Const;
import com.adamkis.astronchallenge.model.Attendee;
import com.adamkis.astronchallenge.network.VolleySingleton;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by adam on 8/25/15.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private final Context context;
    private List<Attendee> mDataset;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView icon;

        public ViewHolder(View v) {
            super(v);
            this.mTextView = (TextView) v.findViewById(R.id.mTextView);
            this.icon = (ImageView) v.findViewById(R.id.icon);
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


        Attendee.AgeGroup ageGroup = mDataset.get(position).getAgeGroup();
        if( ageGroup == Attendee.AgeGroup.STUDENT ){
            holder.icon.setImageResource(R.drawable.abc_btn_radio_material);
        }else if( ageGroup == Attendee.AgeGroup.WORKER ){
            holder.icon.setImageResource(R.drawable.abc_btn_check_to_on_mtrl_015);
        }else if( ageGroup == Attendee.AgeGroup.RETIRED ){
            holder.icon.setImageResource(R.drawable.abc_cab_background_top_mtrl_alpha);
        }

        LinearLayout.LayoutParams lllp = (LinearLayout.LayoutParams)holder.icon.getLayoutParams();

        if( mDataset.get(position).getGender().equals("male") ){
            lllp.gravity= Gravity.RIGHT;
        }
        else{
            lllp.gravity= Gravity.LEFT;
        }

        holder.icon.setLayoutParams(lllp);

    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
