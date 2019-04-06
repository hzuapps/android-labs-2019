package com.hzuandroid.soft1714080902408;

import android.accessibilityservice.GestureDescription;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import com.hzuandroid.soft1714080902408.bean.Account;
import com.hzuandroid.soft1714080902408.dao.AccountDao;
import com.hzuandroid.soft1714080902408.dao.MyHelper;

import java.util.List;

public class Mygwc extends Activity {
    private List<Account> list;
    private AccountDao dao;
    private EditText nameET;
    private EditText balanceET;
    private MyAdapter adapter;
    private ListView accountLV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._sy);
        initView();
        dao = new AccountDao(this);
        list = dao.queryALL();
        adapter = new MyAdapter();
        accountLV.setAdapter(adapter);
    }

    private void initView() {
        accountLV = (ListView) findViewById(R.id.accountLV);
        nameET = (EditText) findViewById(R.id.nameET);
        balanceET = (EditText) findViewById(R.id.balanceET);
        accountLV.setOnItemClickListener(new MyOnItemClickListener());
    }

    public void add(View v) {
        String name = nameET.getText().toString().trim();
        String balance = balanceET.getText().toString().trim();
        Account a = new Account(name, balance.equals("") ? 0 : Integer.parseInt(balance));
        dao.insert(a);
        list.add(a);
        adapter.notifyDataSetChanged();
        accountLV.setSelection(accountLV.getCount() - 1);
        nameET.setText("");
        balanceET.setText("");
    }

    private class MyAdapter extends BaseAdapter {
        public int getCount() {
            return list.size();
        }

        public Object getItem(int position) {
            return list.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView != null ? convertView : View.inflate(getApplicationContext(), R.layout._item, null);
            TextView idTV = (TextView) item.findViewById(R.id.idTV);
            TextView nameTV = (TextView) item.findViewById(R.id.nameTV);
            TextView balanceTV = (TextView) item.findViewById(R.id.balanceTV);
            final Account a = list.get(position);
            idTV.setText(a.getId() + "");
            nameTV.setText(a.getName());
            balanceTV.setText(a.getBalance() + "");
            ImageView upIV = (ImageView) item.findViewById(R.id.upIV);
            ImageView downIV = (ImageView) item.findViewById(R.id.downIV);
            ImageView deleteIV = (ImageView) item.findViewById(R.id.deleteIV);
            upIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    a.setBalance(a.getBalance() + 1);
                    notifyDataSetChanged();
                    dao.update(a);
                }
            });
            downIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a.setBalance(a.getBalance() - 1);
                    notifyDataSetChanged();
                    dao.update(a);
                }
            });
            deleteIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    android.content.DialogInterface.OnClickListener listener = new android.content.DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            list.remove(a);
                            dao.delete(a.getId());
                            notifyDataSetChanged();
                        }
                    };
                        new AlertDialog.Builder(Mygwc.this)
                                .setTitle("确定要删除吗？")
                                .setPositiveButton("确定",listener)
                                .setNegativeButton("取消",null)
                                .show();
                }
            });
            return item;
        }
    }

    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Account a = (Account) parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(), a.toString(), Toast.LENGTH_SHORT).show();
    }
}
}

