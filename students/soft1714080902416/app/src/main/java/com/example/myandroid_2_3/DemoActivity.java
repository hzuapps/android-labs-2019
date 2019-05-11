package com.example.myandroid_2_3;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myandroid_2_3.DAO.AccountDao;
import com.example.myandroid_2_3.bean.Account;

import java.util.List;

public class DemoActivity extends AppCompatActivity {
    private List<Account> list;
    private AccountDao dao;
    private EditText shijian;
    private EditText cost;
    private Button addbtn;
    private MyAdapter adapter;
    private ListView account ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
        dao =new AccountDao(this);
        list=dao.queryALL();
        adapter =new MyAdapter();
        account.setAdapter(adapter);
    }
    View.OnClickListener onClickListener=(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            add(v);
        }
    });
    private void initView(){
        account =(ListView)findViewById(R.id.account);
        shijian =(EditText)findViewById(R.id.shijian);
        cost= (EditText)findViewById(R.id.cost);
        addbtn=(Button)findViewById(R.id.addbtn);
        account.setOnItemClickListener(new MyOnItemClickListener());
        addbtn.setOnClickListener(onClickListener);
    }

    public void add(View v){
        String name=shijian.getText().toString().trim();
        String balance=cost.getText().toString().trim();
        Account a=new Account(name,balance.equals("")?0:Integer.parseInt(balance));
        dao.insert(a);
        list.add(a);
        adapter.notifyDataSetChanged();
        account.setSelection(account.getCount() - 1);
        shijian.setText("");
        cost.setText("");
    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View item = convertView !=null ? convertView:View.inflate(getApplicationContext(),R.layout.item,null);
            TextView idTV = (TextView)item.findViewById(R.id.idTV);
            TextView nameTV=(TextView)item.findViewById(R.id.nameTV);
            TextView balanceTV=(TextView)item.findViewById(R.id.balanceTV);
            final Account a=list.get(position);
            idTV.setText(a.getId()+"");
            nameTV.setText(a.getActions());
            balanceTV.setText(a.getCost()+"");
            return item;
        }
    }
    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Account account=(Account)parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(), account.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
