package sg.edu.rp.c346.id22023910.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText title;
    EditText singers;
    EditText year;

    RadioGroup stars;
    Button insert;
    Button Show;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.Title);
        singers = findViewById(R.id.Singers);
        year = findViewById(R.id.Year);
        insert = findViewById(R.id.update);
        Show = findViewById(R.id.Delete);
        stars = findViewById(R.id.radioStars);




        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DBHelper db = new DBHelper(MainActivity.this);

                String songTitle = title.getText().toString();
                String songSingers = singers.getText().toString();
                int songYear = Integer.parseInt(year.getText().toString());
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



                // Insert a task
                db.insertSong(songTitle,songSingers,songYear,songRating);

            }
        });

        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });






    }



}
