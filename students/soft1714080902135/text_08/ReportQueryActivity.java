//报告查询功能
public class OneclickRegistrationActivity{
    private EditText et_info;
    private Button btn_sava;
    private Button btn_read;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oneclick_registration_activity);
        //获取布局文件的控件
        et_info=(EditText)findViewById(R.id.et_info);
        btn_sava=(Button)findViewById(R.id.btn_save);
        btn_read=(Button)findViewById(R.id.btn_read);
        btn_sava.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
    }
    //定义Button按钮的点击事件
    private class ButtonListener implements View.OnClickListener{
        public void onClick(View V){
            switch(V.getId()){
                case R.id.btn_save:
                    String saveinfo = et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        //保存数据
                        fos = openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this,"数据保存成功", 0).show();
                    break;
                case R.id.btn_read:
                    String content="";
                    try{
                        //获取保存的数据
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                        fis.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this,"保存的数据是：姓名:李华;年龄:18岁;性别:男;症状:重感冒;排号:35号;备注："+content+";",0).show();
                    break;
                default:break;
            }
        }
    }
}
