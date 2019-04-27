package edu.androidlabs.soft1714080902227;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tuku3.R;

public class Soft1714080902227Fragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.album, container, false);
        Button button = (Button) view.findViewById(R.id.btn_contact);
        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Toast.makeText(getActivity(),"相册",Toast.LENGTH_SHORT).show();
            }

        });
        return view;
    }
}
