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
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        alSong = new ArrayList<>();
        lv = findViewById(R.id.lv);
        star5 = findViewById(R.id.btn5Star);
        yrspn = findViewById(R.id.yrspn);

        adapter = new CustomAdapter(this, R.layout.row, alSong);
        lv.setAdapter(adapter);

        DBHelper db = new DBHelper(SecondActivity.this);
        List<Song> songList = db.getSong();
        List<Integer> dist = new ArrayList<>();

        for (Song song : songList) {
            int year = song.getYear();
            if (!dist.contains(year)) {
                dist.add(year);
            }
        }

        ArrayAdapter<String> Aspn = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);

        for (int year : dist) {
            Aspn.add(String.valueOf(year));
        }

        yrspn.setAdapter(Aspn);

        alSong.clear();
        alSong.addAll(db.getSong());
        adapter.notifyDataSetChanged();

        ArrayList<String> data = db.getSongContent();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Song data = alSong.get(position);

                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });

        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Song> fiveStarSongs = new ArrayList<>();

                for (Song song : alSong) {
                    if (song.getStar() == 5) {
                        fiveStarSongs.add(song);
                    }
                }

                adapter = new CustomAdapter(SecondActivity.this, R.layout.row, fiveStarSongs);
                lv.setAdapter(adapter);
            }
        });
    }
}