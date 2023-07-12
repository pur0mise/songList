package sg.edu.rp.c346.id22023910.songlist;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class ThirdActivity extends AppCompatActivity {


    EditText title;
    EditText singers;
    EditText year;

    RadioGroup stars;
    Button Update;
    Button Delete;
    Button Cancel;
    Song data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        title = findViewById(R.id.Title);
        singers = findViewById(R.id.Singers);
        year = findViewById(R.id.Year);
        Update = findViewById(R.id.update);
        Delete = findViewById(R.id.Delete);
        stars = findViewById(R.id.radioStars);
        Cancel = findViewById(R.id.Cancel);


        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");



        title.setText(data.getTitle());
        singers.setText(data.getSingers());
        year.setText(String.valueOf(data.getYear()));
        stars.check((data.getStar()));





        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                data.setTitle(title.getText().toString());
                data.setSingers(singers.getText().toString());
                data.setYear(Integer.parseInt(year.getText().toString()));
                int songRating = 0;

                int rating = stars.getCheckedRadioButtonId();
                if (rating == R.id.Star1){
                    songRating = 1;
                } else if (rating == R.id.Star2) {
                    songRating = 2;
                } else if (rating == R.id.Star3) {
                    songRating = 3;
                } else if (rating == R.id.Star4) {
                    songRating = 4;
                } else {
                    songRating = 5;
                }


                data.setStars(songRating);

                dbh.updateSong(data);
                dbh.close();

                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(data.get_id());
                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


    }
}