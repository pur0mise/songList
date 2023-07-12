package sg.edu.rp.c346.id22023910.songlist;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Song implements Serializable {
    private int _id;
    private String title;
    private  String singers;
    private int year;
    private  int stars;


    public Song(int id, String title, String singers, int year, int stars){
        this._id = id;
        this.title = title.toString();
        this.singers = singers.toString();
        this.year = year;
        this.stars = stars;

    }

    public int get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }
    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStar() {
        return stars;
    }


    @NonNull
    @Override
    public String toString() {
        String starsString = "";
        for (int i = 0; i < stars; i++) {
            starsString += "*";
        }
        return title + "\n" + singers + "\n" + year + "\n" + starsString;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setSingers(String singers) {
        this.singers = singers;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}

