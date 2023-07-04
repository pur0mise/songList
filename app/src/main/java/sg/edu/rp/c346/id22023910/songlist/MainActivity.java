package sg.edu.rp.c346.id22023910.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShowList;
    EditText songInsert;
    EditText singerInsert;
    EditText yearInsert;
    RadioGroup rg;
    //ListView lv;
    int star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        songInsert = findViewById(R.id.editSongTitle);
        singerInsert = findViewById(R.id.editSingers);
        yearInsert = findViewById(R.id.editYear);
        btnShowList = findViewById(R.id.btnShowList);
        //lv = findViewById(R.id.listView);
        rg = findViewById(R.id.ratingGroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rating1) {
                    star = 1;
                }
                else if(checkedId == R.id.rating2) {
                    star = 2;
                }
                else if(checkedId == R.id.rating3) {
                    star = 3;
                }
                else if(checkedId == R.id.rating4) {
                    star = 4;
                }
                else if(checkedId == R.id.rating5) {
                    star = 5;
                }

            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                String song = songInsert.getText().toString();
                String singers = singerInsert.getText().toString();
                String year = yearInsert.getText().toString();
                db.insertSong(song, singers, year, star);

            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
              /* ArrayList<Song> data = db.getAllSongs();
                ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, data);
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }

                lv.setAdapter(adapter);*/

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


    }
}