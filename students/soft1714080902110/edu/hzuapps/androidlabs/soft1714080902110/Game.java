package edu.hzuapps.androidlabs.soft1714080902110;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Game extends AppCompatActivity {

    private static final String INSERT_DATA = "insert into rank (name, grade) values (?,?)";

    private static final String TAG = "Game";

//    拍照
    public static final int TAKE_PHOTO = 1;
    private RelativeLayout game;
    private File outputImage;

//    游戏功能
    int grade = 100;
    Number number;
    private EditText input;
    private TextView tip;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//        Toolbar显示
        Toolbar toolbar = findViewById(R.id.game_title);
        setSupportActionBar(toolbar);

        input = findViewById(R.id.input);
        tip = findViewById(R.id.tip);
        number = new Number();
        Log.e(TAG, "onCreate: " + number.getQian());
        Log.e(TAG, "onCreate: " + number.getBai());
        Log.e(TAG, "onCreate: " + number.getShi());
        Log.e(TAG, "onCreate: " + number.getGe());

//        如果已拍过图片，直接显示
        game = findViewById(R.id.game);
        outputImage = new File(getCacheDir(), "myImage.jpg");
        if (outputImage.exists()) {
            showBitmap();
        } else {
            game.setBackground(new ColorDrawable(getResources().getColor(R.color.colorGreen)));
        }
    }

//    添加菜单显示
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }

//    实现菜单功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_background:
                takePhoto();
                break;
            default:
                break;
        }
        return true;
    }

//    拍照准备
    private void takePhoto() {
//        创建文件
        outputImage = new File(getCacheDir(), "myImage.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        调用相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri());
        startActivityForResult(intent, TAKE_PHOTO);
    }

//    拍照返回
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    showBitmap();
                }
                break;
            default:
                break;
        }
    }

//    获取图片uri
    private Uri getImageUri() {
        Uri imageUri;
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(Game.this, getPackageName(), outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        return imageUri;
    }

//    显示bitmap图片
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void showBitmap() {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(getImageUri()));
            game.setBackground(new BitmapDrawable(bitmap));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

//    提交数字对比
    public void submit(View view) {
        int a = 0, b = 0;
        Number inputNumber = new Number(Integer.parseInt(input.getText().toString()));
        a = getA(number, inputNumber);
        b = getB(number, inputNumber);
        if (grade >= 0) {
            switch (a) {
                case 1:
                case 2:
                case 3:
                    grade -= 20;
                    tip.setText(a + "A" + b + "B");
                    break;
                case 4:
                    tip.setText(a + "A" + b + "B");
                    writeGrade();
                    showAlertDialog();
                    break;
                default:
                    break;
            }
        } else {
            tip.setText(getResources().getText(R.string.gameOver));
        }
    }

//    获取A
    private int getA(@NotNull Number source, @NotNull Number target) {
        int a = 0;
        if (source.getQian() == target.getQian()) {
            a++;
        }
        if (source.getBai() == target.getBai()) {
            a++;
        }
        if (source.getShi() == target.getShi()) {
            a++;
        }
        if (source.getGe() == target.getGe()) {
            a++;
        }
        return a;
    }

//    获取B
    private int getB(@NotNull Number source, @NotNull Number target) {
        int b = 0;
        if (target.getQian() == source.getBai() || target.getQian() == source.getShi() || target.getQian() == source.getGe()) {
            b++;
        }
        if (target.getBai() == source.getQian() || target.getBai() == source.getShi() || target.getBai() == source.getGe()) {
            b++;
        }
        if (target.getShi() == source.getQian() || target.getShi() == source.getBai() || target.getShi() == source.getGe()) {
            b++;
        }
        if (target.getGe() == source.getQian() || target.getGe() == source.getBai() || target.getGe() == source.getShi()) {
            b++;
        }
        return b;
    }

//    将成绩写入记录
    private void writeGrade() {
        RankListSQLHelper helper = new RankListSQLHelper(this, "rank.db", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(INSERT_DATA, new String[] {"本人", String.valueOf(grade)});
        db.delete("rank", "id > ?", new String[] {"10"});
    }

    private void showAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Game.this);
        dialog.setTitle("o(￣▽￣)ｄ good");
        dialog.setMessage("恭喜你取得胜利，成绩为" + grade);
        dialog.setCancelable(false);
        dialog.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialog.setNegativeButton("重新开始", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Game.this, Game.class);
                startActivity(intent);
                finish();
            }
        });
        dialog.show();
    }
}
