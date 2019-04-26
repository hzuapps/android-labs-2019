package edu.hzuapps.androidlabs.com1714080901141;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Com1714080901141FragmentStudy extends Fragment implements View.OnClickListener{
    private String TAG="Com1714080901141FragmentStudy";

    private Button button_save,button_clear;
    private EditText editText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fregment_study,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initListener();
    }

    private void initView(){
        button_save = (Button) getView().findViewById(R.id.save_button);
        button_clear=(Button)   getView().findViewById(R.id.clear_button);
        editText=(EditText) getView().findViewById(R.id.note_edit);
    }
    private void initListener(){
        button_save.setOnClickListener(this);
        button_clear.setOnClickListener(this);
        editText.setText(load());//read from the saved file
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.clear_button:
                editText.setText("");
                break;
            case R.id.save_button:
                save(editText.getText().toString());
                break;
            default:
                break;
        }
    }
    public void save(String inputText){//save in data/data/edu.hzuapps.androidlabs.com1714080901141/files/

        Log.d(TAG,"saving");
        Context context;
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            out=getActivity().openFileOutput("data",Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public String load(){
        FileInputStream in=null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try{
            in=getActivity().openFileInput("data");
            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while((line=reader.readLine())!=null){//read line by line
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}