package edu.hzuapps.androidlabs.soft1714080902121.exratecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Soft1714080902121MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String strRateList;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(Soft1714080902121MainActivity.this);
        getRateListJson(queue);
        Toast.makeText(Soft1714080902121MainActivity.this,"正在获取汇率表",Toast.LENGTH_SHORT).show();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId()) {
            case R.id.btnRateListAct:{
                Intent intent = new Intent(Soft1714080902121MainActivity.this, Soft1714080902121RateListActivity.class);
                intent.putExtra("strRateList", strRateList);
                startActivity(intent);
                break;
            }
        }
    }
    private void getRateListJson(RequestQueue queue)
    {
        String url = "https://raw.githubusercontent.com/VOEZ21/android-labs-2019/master/students/soft1714080902121/app/src/main/RateList.json";
        JsonObjectRequest ratelist = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray currencies = response.getJSONArray("Currency");
                    String[] r1 = getResources().getStringArray(R.array.r1);
                    String[] r2 = getResources().getStringArray(R.array.r2);
                    String t1 = "";
                    String t2 = "";
                    for(int i = 0; i < currencies.length(); i++)
                    {
                        JSONObject currency = currencies.getJSONObject(i);
                        String name = currency.getString("name");
                        String abbr = currency.getString("abbr");
                        double CNYtoItself = currency.getDouble("CNYtoItself");
                        double toCNY = currency.getDouble("toCNY");

                        r1[i] = String.format(r1[i], name, abbr, CNYtoItself);
                        t1 += r1[i];
                        r2[i] = String.format(r2[i], name, abbr, toCNY);
                        t2 += r2[i];
                    }
                    setRateList(t1 + '\n' + t2);
                    Toast.makeText(Soft1714080902121MainActivity.this,"获取成功",Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(ratelist);
    }

    private void setRateList(String str)
    {
        strRateList = str;
    }
}
