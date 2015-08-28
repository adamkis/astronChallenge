package com.adamkis.astronchallenge.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.adamkis.astronchallenge.R;
import com.adamkis.astronchallenge.common.Const;
import com.adamkis.astronchallenge.model.Attendee;
import com.adamkis.astronchallenge.model.Name;
import com.adamkis.astronchallenge.network.GsonRequest;
import com.adamkis.astronchallenge.network.VolleySingleton;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MainActivity extends Activity {


    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private SearchResultAdapter mAdapter;
    private Dialog loadingDialog;
    private Button btn_go_to_chart;
    private ArrayList<Attendee> attendees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        btn_go_to_chart = (Button) findViewById(R.id.btn_go_to_chart);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        loadingDialog = new Dialog(this ,android.R.style.Theme_Translucent_NoTitleBar);
//            loadingDialog = new Dialog(getActivity());
//            loadingDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        loadingDialog.setContentView(R.layout.dialog_loading);
        loadingDialog.show();

        download();


        btn_go_to_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChartActivity.class);
                intent.putParcelableArrayListExtra(Const.ATTENDEES_KEY, attendees);
                startActivity(intent);
            }
        });


    }



    private void download(){

        Log.i("LOG", "Call made: " + Const.buildSearchUrl());


        GsonRequest jsObjRequest = new GsonRequest(
                Const.buildSearchUrl(),
                Attendee[].class,
                null,
                new Response.Listener<Attendee[]>() {

                    @Override
                    public void onResponse(Attendee[] response) {
                        Log.i("LOG", "Response: " + response.toString());
                        Log.i("LOG", "Fist: " + response[0].toString());


                        attendees = new ArrayList<Attendee>(Arrays.asList(response));



                        Collections.sort(attendees);

                        // specify an adapter (see also next example)
                        mAdapter = new SearchResultAdapter(attendees);
                        mRecyclerView.setAdapter(mAdapter);

                        loadingDialog.dismiss();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "VolleyError: " + error.toString());

            }
        });


        VolleySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }





}
