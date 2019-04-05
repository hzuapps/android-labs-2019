package edu.hzuapps.androidlabs.soft1714080902110;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PeopleAdapter extends ArrayAdapter<People> {

    private int resourceId;

    public PeopleAdapter(Context context, int textViewResourceId, List<People> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        People people = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView rankName = view.findViewById(R.id.rank_name);
        TextView rankScore = view.findViewById(R.id.rank_score);
        rankName.setText(people.getName());
        rankScore.setText(people.getScore());
        return view;
    }
}
