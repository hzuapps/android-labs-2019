package androidlabs2019.students.soft1714080902323;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static androidlabs2019.students.soft1714080902323.R.id.add;

public class Soft1714080902323Activity4 extends AppCompatActivity {
    private static final String TAG = "Soft1714080902323Activity4";
    ArrayList<Course> courses;
    ArrayAdapter<Course> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902323_activity4);
        courses = new ArrayList<>();
        restore();
        ListView listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<Course>(this,android.R.layout.simple_list_item_1,courses);
        listView.setAdapter(arrayAdapter);
}
    public void add(View v){
        EditText name = findViewById(R.id.name);
        EditText teacher = findViewById(R.id.teacher);

        Course course = new Course(name.getText().toString(),(teacher.getText().toString()));
        courses.add(course);
        arrayAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    private void save() {
        try {
            File file = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            Log.d(TAG, "save: "+file.getAbsolutePath());
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath()+"/courses");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(courses);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void restore() {
        try {
            File file = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath()+"/courses");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            courses = (ArrayList<Course>) objectInputStream.readObject();

            fileInputStream.close();
            objectInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    }