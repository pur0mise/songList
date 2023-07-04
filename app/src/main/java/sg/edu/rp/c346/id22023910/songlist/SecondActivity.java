package sg.edu.rp.c346.id22023910.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = findViewById(R.id.listView);
        //Intent intentReceived = getIntent();
        DBHelper db = new DBHelper(SecondActivity.this);
        ArrayList<Song> data = db.getAllSongs();
        ArrayAdapter adapter = new ArrayAdapter<>(SecondActivity.this, android.R.layout.simple_list_item_1, data);
        db.close();

        String txt = "";
        for (int i = 0; i < data.size(); i++) {
            Log.d("Database Content", i +". "+data.get(i));
            txt += i + ". " + data.get(i) + "\n";
        }

        lv.setAdapter(adapter);
    }
}