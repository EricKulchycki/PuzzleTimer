package sep.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

import sep.myapplication.R;
import sep.myapplication.objects.Timer;
import sep.myapplication.persistence.DataAccessStub;
import sep.myapplication.persistence.DatabaseInterface;

public class TimeListActivity extends AppCompatActivity {

    ListView timeList;

    DatabaseInterface timeDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_list);

        timeList = (ListView) findViewById(R.id.timeListView);

        timeDB = new DataAccessStub("timeList");

        timeDB.open("timeList");

        setupTimeList();

    }

    public void setupTimeList() {
        ArrayList<String> timeStrings = new ArrayList<String>();
        ArrayList<Long> timeDBList = timeDB.getList();


        for(int i = 0; i < timeDB.getSize(); i++) {


            long time = timeDBList.get(i);
            String timeString = Timer.toString(time);
            timeStrings.add(timeString);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timeStrings );

        timeList.setAdapter(arrayAdapter);
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
}
