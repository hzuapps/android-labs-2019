package edu.hzuapps.androidlabs.soft1714080902110;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.util.Config.LOGD;

public class CountryList extends AppCompatActivity {

    private static final String TAG = "CountryList";

    private List<People> peopleList = new ArrayList<>();

    private static final String URL = "https://raw.githubusercontent.com/HanXuanMo/android-labs-2019/master/students/soft1714080902110/rank.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        initPeople();
    }

    private void initPeople() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(URL)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String score = jsonObject.getString("score");
                        Log.d(TAG, "showJson: " + name);
                        Log.d(TAG, "showJson: " + score);
                        People people = new People(name, score);
                        peopleList.add(people);
                    }
                    PeopleAdapter adapter = new PeopleAdapter(CountryList.this, R.layout.rank_item, peopleList);
                    ListView listView = findViewById(R.id.country_list);
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
