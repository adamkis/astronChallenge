package com.adamkis.astronchallenge.ui;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.adamkis.astronchallenge.R;
import com.adamkis.astronchallenge.common.Const;
import com.adamkis.astronchallenge.model.AgeAnalysis;
import com.adamkis.astronchallenge.model.Attendee;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.ArrayList;

public class ChartActivity extends ActionBarActivity {

    private LinearLayout chartContainer;
    private ArrayList<Attendee> attendees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Bundle extras = getIntent().getExtras();

        if( extras == null || !extras.containsKey(Const.ATTENDEES_KEY) ){
            finish();
            Log.e(Const.LOG_TAG, "No data in ChartActivity - quitting");
            return;
        }

        attendees = extras.getParcelableArrayList(Const.ATTENDEES_KEY);


        chartContainer = (LinearLayout) findViewById(R.id.chart);
        openChart( chartContainer, attendees );

    }



    private void openChart(ViewGroup chartContainer, ArrayList<Attendee> attendees) {


        AgeAnalysis ageAnalysis = new AgeAnalysis(attendees);


        // Pie Chart Section Value
        int[] ageDistribution = { ageAnalysis.getNumberOfStudents(), ageAnalysis.getNumberOfWorkers(), ageAnalysis.getNumberOfRetired() };

        // Color of each Pie Chart Sections
        int[] colors = { Color.BLUE,Color.GREEN,Color.RED };

        // Instantiating CategorySeries to plot Pie Chart
        CategorySeries distributionSeries = new CategorySeries("Age group distribution of workers");
        for (int i = 0; i < ageDistribution.length; i++) {
            // Adding a slice with its values and name to the Pie Chart
            distributionSeries.add(Attendee.AGE_GROUP_NAMES[i], ageDistribution[i]);
        }

        // Instantiating a renderer for the Pie Chart
        DefaultRenderer defaultRenderer = new DefaultRenderer();
        for (int i = 0; i < ageDistribution.length; i++) {
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            seriesRenderer.setDisplayChartValues(true);

//            defaultRenderer.setBackgroundColor(Color.BLACK);
            defaultRenderer.setApplyBackgroundColor(true);
            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        defaultRenderer.setChartTitle("Age group distribution of workers");
        defaultRenderer.setChartTitleTextSize(50);
        defaultRenderer.setLabelsTextSize(50);
        defaultRenderer.setLegendTextSize(50);
        defaultRenderer.setZoomButtonsVisible(false);


        chartContainer.removeAllViews();
        // drawing pie chart
        View mChart = ChartFactory.getPieChartView(this,
                distributionSeries, defaultRenderer);
        // adding the view to the linearlayout
        chartContainer.addView(mChart);

    }


}
