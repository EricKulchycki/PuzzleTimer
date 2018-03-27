package sep.myapplication.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import sep.myapplication.Application.Main;
import sep.myapplication.Application.Services;
import sep.myapplication.R;
import sep.myapplication.objects.Timer;
import sep.myapplication.persistence.DatabaseInterface;

public class TimeListActivity extends AppCompatActivity {

    ListView timeList;

    DatabaseInterface timeDB;
    ArrayList<Long> timeDBList;
    Button deleteItem, clearList, modify, dnf;
    long time;
    long incrementTwo = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_list);

        timeList = (ListView) findViewById(R.id.timeListView);

        timeDB = Services.getDataAccess(Main.dbName);

        setupTimeList();

        timeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                time = timeDBList.get(position);
                Toast toast = Toast.makeText(getApplicationContext(),Timer.toString(time),Toast.LENGTH_LONG );
                toast.show();

            }
        });

        deleteItem = (Button)findViewById(R.id.Delete);
        deleteItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                timeDB.delete(time);
                setupTimeList();
            }
        });

        clearList = (Button)findViewById(R.id.ClearList);
        clearList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                timeDB.reset();
                setupTimeList();
            }
        });

        modify = (Button)findViewById(R.id.Modify);
        modify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                timeDB.modify(time, time+incrementTwo);
                setupTimeList();
            }
        });

        dnf = (Button)findViewById(R.id.DNF);
        dnf.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                timeDB.modify(time, 0);
                setupTimeList();
            }
        });


    }

    public void setupTimeList() {
        ArrayList<String> timeStrings = new ArrayList<String>();
        timeDBList = timeDB.getList();
        String timeString = "";

        for(int i = 0; i < timeDBList.size(); i++) {


            long time = timeDBList.get(i);
            if (time >= 0) {
                if(time == 0){
                    timeString = "DNF";
                }
                else {
                    timeString = Timer.toString(time);
                }
                    timeStrings.add(timeString);

            }
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
