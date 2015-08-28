package com.adamkis.astronchallenge.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.adamkis.astronchallenge.R;
import com.adamkis.astronchallenge.common.Const;
import com.adamkis.astronchallenge.common.Utils;
import com.adamkis.astronchallenge.model.Attendee;
import com.adamkis.astronchallenge.network.GsonRequest;
import com.adamkis.astronchallenge.network.VolleySingleton;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class MainActivity extends Activity {


    private RecyclerView searchResultContainer;
    private LinearLayoutManager mLayoutManager;
    private SearchResultAdapter mAdapter;
    private View goToChart;
    private View loadingProgressBar;
    private ArrayList<Attendee> attendees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchResultContainer = (RecyclerView) findViewById(R.id.searchResultContainer);

        goToChart = findViewById(R.id.goToChart);
        loadingProgressBar =  findViewById(R.id.loadingProgressBar);

        searchResultContainer.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        searchResultContainer.setLayoutManager(mLayoutManager);

        download();


        goToChart.setOnClickListener(new View.OnClickListener() {
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


                        mAdapter = new SearchResultAdapter(attendees);
                        searchResultContainer.setAdapter(mAdapter);

                        Utils.hideLoadingAnimated(loadingProgressBar, searchResultContainer);
                        Utils.revealViewAnimated(goToChart);


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
