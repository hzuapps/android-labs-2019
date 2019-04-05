package edu.hzuapps.androidlabs.soft1714080902110;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RankList extends AppCompatActivity {

    private RankListSQLHelper sqlHelper;

    private List<People> peopleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_list);

        sqlHelper = new RankListSQLHelper(this, "rank.db", null, 2);

        initPeople();
        PeopleAdapter adapter = new PeopleAdapter(RankList.this, R.layout.rank_item, peopleList);
        ListView listView = findViewById(R.id.rank_list);
        listView.setAdapter(adapter);
    }

    private void initPeople() {
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        Cursor cursor = db.query("rank", null, null,
                null, null, null, "score");
        if (cursor.moveToLast()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String score = cursor.getString(cursor.getColumnIndex("score"));
                People people = new People(name, score);
                peopleList.add(people);
            } while (cursor.moveToPrevious());
        }
        cursor.close();
    }

}
