package sg.edu.rp.c346.id22023910.songlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        versionList = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.songTitle);
        TextView tvYears = rowView.findViewById(R.id.yearsReleased);
        TextView tvStars = rowView.findViewById(R.id.stars);
        TextView tvSingers = rowView.findViewById(R.id.singers);

        // Obtain the Android Version information based on the position
        Song currentVersion = versionList.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentVersion.getTitle());
            tvYears.setText("" + currentVersion.getYear());
            String message = "";
        for (int i = 0; i < currentVersion.getStar(); i++ ) {
                message += "* ";
        }
            tvStars.setText("" + currentVersion.getStar());
            tvSingers.setText(currentVersion.getSingers());

            tvStars.setText(message);
        return rowView;
    }

}
