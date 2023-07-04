package sg.edu.rp.c346.id22023910.songlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    // Start version with 1
    // increment by 1 whenever db schema changes.
    private static final int DATABASE_VER = 1;
    // Filename of the database
    private static final String DATABASE_NAME = "Songs.db";

    private static final String TABLE_SONG = "Song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEARS = "years";
    private static final String COLUMN_STARS = "stars";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_SONG +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGERS + " TEXT,"
                + COLUMN_STARS + " TEXT,"
                + COLUMN_YEARS + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");
        Log.i("info" ,createTableSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        // Create table(s) again
        onCreate(db);
    }

    public void insertSong(String song, String singers, String years, int rate){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, song);
        values.put(COLUMN_SINGERS, singers);
        values.put(COLUMN_YEARS, Integer.parseInt(years));
        values.put(COLUMN_STARS, rate);

        // Insert the row into the TABLE_SONG
        db.insert(TABLE_SONG, null, values);
        // Close the database connection
        db.close();
    }

    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songslist = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_TITLE, COLUMN_STARS};
        Cursor cursor;
        cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song newSong = new Song(id, title, singers, year, stars);
                songslist.add(newSong);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songslist;
    }

}
