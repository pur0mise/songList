package sg.edu.rp.c346.id22023910.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Song> alSong;
    Button star5;
    Spinner yrspn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        alSong = new ArrayList<Song>();
        ArrayAdapter<String> Aspn;

        lv = findViewById(R.id.lv);
        star5 = findViewById(R.id.btn5Star);
        yrspn = findViewById(R.id.yrspn);

        Aspn = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);



        ArrayAdapter adapter = new ArrayAdapter<>(SecondActivity.this, android.R.layout.simple_list_item_1, alSong);

        List<Integer> dist = new ArrayList<>();
        DBHelper db = new DBHelper(SecondActivity.this);
        List<Song> songList =db.getSong();
        for(Song song : songList){
            int year = song.getYear();
            if(!dist.contains(year)){
                dist.add(year);
            }}


        for(int year : dist){
            Aspn.add(String.valueOf(year));
        }

        yrspn.setAdapter(Aspn);

        lv.setAdapter(adapter);

        alSong.clear();
        alSong.addAll(db.getSong());
        adapter.notifyDataSetChanged();


        ArrayList<String> data = db.getSongContent();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Song data = alSong.get(position);


                // Create an Intent to launch the third activity
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);

            }
        });

        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(SecondActivity.this);

                ArrayList<Song> fiveStarSongs = new ArrayList<>();

                for (Song song : alSong) {
                    if (song.getStar() == 5) {
                        fiveStarSongs.add(song);
                    }

                    ArrayAdapter<Song> adapter = new ArrayAdapter<>(SecondActivity.this, android.R.layout.simple_list_item_1, fiveStarSongs);
                    lv.setAdapter(adapter);
                }



            }
        });









    }




}