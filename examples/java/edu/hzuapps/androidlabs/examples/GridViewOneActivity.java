package edu.hzuapps.androidlabs.examples;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import edu.hzuapps.androidlabs.R;
import edu.hzuapps.androidlabs.BackActivity;

public class GridViewOneActivity extends BackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_one);

        // Get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.grid_view_one);
        imageView.setImageResource(imageAdapter.mThumbIds[position]);
    }

}
