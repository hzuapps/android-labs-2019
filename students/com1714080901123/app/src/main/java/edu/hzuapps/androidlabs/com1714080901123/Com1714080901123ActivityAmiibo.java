package edu.hzuapps.androidlabs.com1714080901123;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Com1714080901123ActivityAmiibo extends AppCompatActivity {

    Com1714080901123ClassDrawableID drawableID = new Com1714080901123ClassDrawableID();
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com1714080901123_amiibo);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);  //为toolbar设置返回键有效
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //获取xml里的控件，以跟据传值更改为应该显示的内容
        TextView textView = (TextView)findViewById(R.id.textView_amiibo_bar);
        ImageView imageView = (ImageView)findViewById(R.id.image_amiibo);
        ImageView imageView_Headgear = (ImageView)findViewById(R.id.image_headgear);
        TextView textView_Headgear = (TextView)findViewById(R.id.text_headgear);
        ImageView imageView_Clothing = (ImageView)findViewById(R.id.image_clothing);
        TextView textView_Clothing = (TextView)findViewById(R.id.text_clothing);
        ImageView imageView_Shoes = (ImageView)findViewById(R.id.image_shoes);
        TextView textView_Shoes = (TextView)findViewById(R.id.text_shoes);
        ImageView imageView_Headgear_ability = (ImageView)findViewById(R.id.image_ability_headgear);
        ImageView imageView_Clothing_ability = (ImageView)findViewById(R.id.image_ability_clothing);
        ImageView imageView_Shoes_ability = (ImageView)findViewById(R.id.image_ability_shoes);
        TextView textView_Headgear_ability = (TextView)findViewById(R.id.text_amiibo_headgear_ability);
        TextView textView_Clothing_ability = (TextView)findViewById(R.id.text_amiibo_clothing_ability);
        TextView textView_Shoes_ability = (TextView)findViewById(R.id.text_amiibo_shoes_ability);

        Button button = (Button)findViewById(R.id.button_amiibo_share_photo);

        Intent intent = this.getIntent();
        i = intent.getIntExtra("imgButton", 0);    //获取前一个Activity传过来的值，以决定应该显示的图片和文字

        textView.setText(drawableID.getAmiiboName(i));
        imageView.setImageResource(drawableID.getAmiibo(i));
        imageView_Headgear.setImageResource(drawableID.getHeadGear(i));
        textView_Headgear.setText(drawableID.getHeadGearName(i));
        imageView_Clothing.setImageResource(drawableID.getClothing(i));
        textView_Clothing.setText(drawableID.getClothingName(i));
        imageView_Shoes.setImageResource(drawableID.getShoes(i));
        textView_Shoes.setText(drawableID.getShoesName(i));

        if(i==0) {
            imageView_Headgear_ability.setImageResource(R.drawable.ability_swim_speed_up);
            imageView_Clothing_ability.setImageResource(R.drawable.ability_ink_recovery_up);
            imageView_Shoes_ability.setImageResource(R.drawable.ability_ink_saver_sub);
            textView_Headgear_ability.setText(R.string.ability_swim_speed_up);
            textView_Clothing_ability.setText(R.string.ability_ink_recovery_up);
            textView_Shoes_ability.setText(R.string.ability_ink_saver_sub);
        }
        else if(i==1) {
            imageView_Headgear_ability.setImageResource(R.drawable.ability_special_charge_up);
            imageView_Clothing_ability.setImageResource(R.drawable.ability_quick_super_jump);
            imageView_Shoes_ability.setImageResource(R.drawable.ability_special_power_up);
            textView_Headgear_ability.setText(R.string.ability_special_charge_up);
            textView_Clothing_ability.setText(R.string.ability_quick_super_jump);
            textView_Shoes_ability.setText(R.string.ability_special_power_up);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Com1714080901123ActivityAmiibo.this, Com1714080901123ActivityAmiiboSharePhoto.class);
                intent.putExtra("amiibo",i);
                startActivity(intent);
            }
        });
    }
}
