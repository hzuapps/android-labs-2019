package android.MusicPlayer;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class Com1714080901240Activity_04 extends AppCompatActivity {

    private BluetoothAdapter mBluetoothAdapter;
    private TextView text1,text2,text3;
    private Button btn,btn_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com1714080901240_04);

        text1=(TextView) this.findViewById(R.id.tv2);  //已配对
        text2= (TextView) this.findViewById(R.id.tv1); //状态信息
        text3= (TextView) this.findViewById(R.id.tv3); //未配对
        btn=(Button) this.findViewById(R.id.btn);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver,filter);
        IntentFilter filter2=new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver,filter2);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                if(!mBluetoothAdapter.isEnabled()){
                    mBluetoothAdapter.enable();
                }
                mBluetoothAdapter.startDiscovery();
                text2.setText("正在搜索...");
            }

        });
    }
    public void onDestroy(){
        super.onDestroy();
        //解除注册
        unregisterReceiver(mReceiver);
        Log.e("destroy","解除注册");
    }
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.e("sky",action);
            if(action.equals(BluetoothDevice.ACTION_FOUND)){
                //BluetoothDevice device = intent.getParcelableArrayExtra(BluetoothDevice.EXTRA_DEVICE);
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if(device.getBondState()==BluetoothDevice.BOND_BONDED){
                    //显示已配对设备
                    text1.append("\n"+device.getName()+"==>"+device.getAddress()+"\n");
                }else if(device.getBondState()!=BluetoothDevice.BOND_BONDED){
                    text3.append("\n"+device.getName()+"==>"+device.getAddress()+"\n");
                }
            }else if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){
                text2.setText("搜索完成...");
            }
        }
    };

}
