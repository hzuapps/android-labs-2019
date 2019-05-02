package edu.hzuapps.androidlabs.com1713071002108;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Com1713071002108Activity2 extends AppCompatActivity{
    SQDB mydb=new SQDB(this);
    private Button takephoto;
    private ImageView imageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView .setImageBitmap(imageBitmap);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1713071002108_activity2);
        Button buttonSave=findViewById(R.id.button_save);
        final EditText editTitle=findViewById(R.id.edit_title);
        final EditText editBody=findViewById(R.id.edit_body);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String  title;
                final String body;
                title = editTitle.getText().toString();
                body = editBody.getText().toString();
                AlertDialog.Builder    dialog = new AlertDialog.Builder(Com1713071002108Activity2.this);
                dialog.setTitle("提示");
                dialog.setMessage("是否保存当前编辑内容");
                dialog.setPositiveButton("保存",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                add(title, body);
                                intentStart();
                            }
                        });
                dialog.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                intentStart();
                            }
                        });
                dialog.show();
            }
        });
        imageView=findViewById(R.id.image);
        takephoto=findViewById(R.id.button_takephoto);
        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

    }
    public void add(String title,String body)
    {
        SQLiteDatabase db=mydb.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SQDB.RECORD_TITLE,title);
        values.put(SQDB.RECORD_BODY,body);
        long id=db.insert(SQDB.TABLE_NAME,"",values);
        db.close();
    }
    public void intentStart(){
        Intent intent = new Intent(Com1713071002108Activity2.this,Com1713071002108Activity.class);
        startActivity(intent);
        this.finish();
    }
}