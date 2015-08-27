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


    private NetworkImageView networkImageView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private SearchResultAdapter mAdapter;
    private Dialog loadingDialog;
    private LinearLayout chartContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

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



        chartContainer = (LinearLayout) findViewById(R.id.chart);
        openChart( chartContainer );



    }



    private void openChart(ViewGroup chartContainer) {

        // Pie Chart Section Names
        String[] code = new String[] { "Froyo", "Gingerbread",
                "IceCream Sandwich", "Jelly Bean", "KitKat" };

        // Pie Chart Section Value
        double[] distribution = { 0.5, 9.1, 7.8, 45.5, 33.9 };

        // Color of each Pie Chart Sections
        int[] colors = { Color.BLUE, Color.MAGENTA, Color.GREEN, Color.CYAN,
                Color.RED };

        // Instantiating CategorySeries to plot Pie Chart
        CategorySeries distributionSeries = new CategorySeries(
                " Android version distribution as on October 1, 2012");
        for (int i = 0; i < distribution.length; i++) {
            // Adding a slice with its values and name to the Pie Chart
            distributionSeries.add(code[i], distribution[i]);
        }

        // Instantiating a renderer for the Pie Chart
        DefaultRenderer defaultRenderer = new DefaultRenderer();
        for (int i = 0; i < distribution.length; i++) {
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            seriesRenderer.setDisplayChartValues(true);
//Adding colors to the chart
            defaultRenderer.setBackgroundColor(Color.BLACK);
            defaultRenderer.setApplyBackgroundColor(true);
            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        defaultRenderer
                .setChartTitle("Android version distribution as on December 1, 2014. ");
        defaultRenderer.setChartTitleTextSize(20);
        defaultRenderer.setZoomButtonsVisible(false);

        // this part is used to display graph on the xml
        // Creating an intent to plot bar chart using dataset and
        // multipleRenderer
        // Intent intent = ChartFactory.getPieChartIntent(getBaseContext(),
        // distributionSeries , defaultRenderer, "AChartEnginePieChartDemo");

        // Start Activity
        // startActivity(intent);

        // remove any views before u paint the chart
        chartContainer.removeAllViews();
        // drawing pie chart
        View mChart = ChartFactory.getPieChartView(this,
                distributionSeries, defaultRenderer);
        // adding the view to the linearlayout
        chartContainer.addView(mChart);

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


                        List attendees = Arrays.asList(response);



                        Collections.sort(attendees);

                        // specify an adapter (see also next example)
                        mAdapter = new SearchResultAdapter(MainActivity.this,
                                attendees);
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
