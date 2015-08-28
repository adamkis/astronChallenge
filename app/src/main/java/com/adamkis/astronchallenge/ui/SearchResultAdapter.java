package com.adamkis.astronchallenge.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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

    private List<Attendee> mDataset;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
//        public TextView gender;
//        public TextView age;
//        public TextView id;
        public ImageView icon;

        public ViewHolder(View v) {
            super(v);
            this.name = (TextView) v.findViewById(R.id.name);
//            this.gender = (TextView) v.findViewById(R.id.gender);
//            this.age = (TextView) v.findViewById(R.id.age);
//            this.id = (TextView) v.findViewById(R.id.id);
            this.icon = (ImageView) v.findViewById(R.id.icon);
        }
    }


    public SearchResultAdapter(List<Attendee> myDataset) {
        this.mDataset = myDataset;
    }


    @Override
    public SearchResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendee_card, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(mDataset.get(position).getName().getFullName());
//        holder.gender.setText(mDataset.get(position).getGenderString());
//        holder.gender.setText(mDataset.get(position).getGender());
//        holder.age.setText(mDataset.get(position).getAge());
//        holder.id.setText(mDataset.get(position).getId());


        Attendee.AgeGroup ageGroup = mDataset.get(position).getAgeGroup();
        if( ageGroup == Attendee.AgeGroup.STUDENT ){
            holder.icon.setImageResource(R.drawable.abc_btn_check_material);
        }else if( ageGroup == Attendee.AgeGroup.WORKER ){
            holder.icon.setImageResource(R.drawable.abc_btn_radio_material);
        }else if( ageGroup == Attendee.AgeGroup.RETIRED ){
            holder.icon.setImageResource(R.drawable.abc_btn_radio_to_on_mtrl_015);
        }

        FrameLayout.LayoutParams lllp = (FrameLayout.LayoutParams)holder.icon.getLayoutParams();
        if( mDataset.get(position).getGender() == Attendee.GenderType.MALE ){
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
