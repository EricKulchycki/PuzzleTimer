package sep.myapplication.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import sep.myapplication.Application.Main;
import sep.myapplication.Application.Services;
import sep.myapplication.R;
import sep.myapplication.persistence.DatabaseInterface;

public class GraphActivity extends AppCompatActivity {
    DatabaseInterface timeDB;
    GraphView graph;
    DataPoint[] dataPoint;
    SharedPreferences.Editor editor;
    int[] colours = {R.color.white, R.color.yellow, R.color.blue, R.color.purple, R.color.green};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        timeDB = Services.getDataAccess(Main.dbName);
        dataPoint = new DataPoint[timeDB.getSize()];
        graph = (GraphView) findViewById(R.id.graphView);
        setupBackground();
        setupGraphLables();
        setupGraphData();
    }

    public void setupGraphLables() {
        graph.setTitle("Solve Time");
        graph.setTitleTextSize(50);
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Trial Number");
        gridLabel.setHorizontalAxisTitleTextSize(32);
        gridLabel.setVerticalAxisTitle("Time [ms]");
        gridLabel.setVerticalAxisTitleTextSize(32);
    }

    public void setupGraphData() {
        for(int i = 0; i < timeDB.getSize(); i++) {
            dataPoint[i] = new DataPoint(i, timeDB.getTime(i));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoint);
        graph.addSeries(series);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.back_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.back_action) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setupBackground() {
        View root = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        SharedPreferences settings = getSharedPreferences("bgcolourId", 0);
        root.setBackgroundResource(settings.getInt("bgcolour", colours[0]));
    }
}
