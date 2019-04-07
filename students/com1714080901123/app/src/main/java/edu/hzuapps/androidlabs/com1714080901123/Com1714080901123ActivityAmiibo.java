package edu.hzuapps.androidlabs.com1714080901123;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Com1714080901123ActivityAmiibo extends AppCompatActivity {

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

        Intent intent = this.getIntent();
        String msg = intent.getStringExtra("imgButton");    //获取前一个Activity传过来的值，以决定应该显示的图片和文字
        if(msg.equals("imgButton01")) {
            textView.setText(R.string.name_inkling_girl);
            imageView.setImageResource(R.drawable.amiibo_inkling_girl);
            imageView_Headgear.setImageResource(R.drawable.gear_headgear_squid_hairclip);
            textView_Headgear.setText(R.string.gear_headgear_squid_hairclip);
            imageView_Clothing.setImageResource(R.drawable.gear_clothing_school_uniform);
            textView_Clothing.setText(R.string.gear_clothing_school_uniform);
            imageView_Shoes.setImageResource(R.drawable.gear_shoes_school_shoes);
            textView_Shoes.setText(R.string.gear_shoes_school_shoes);
            imageView_Headgear_ability.setImageResource(R.drawable.ability_swim_speed_up);
            imageView_Clothing_ability.setImageResource(R.drawable.ability_ink_recovery_up);
            imageView_Shoes_ability.setImageResource(R.drawable.ability_ink_saver_sub);
            textView_Headgear_ability.setText(R.string.ability_swim_speed_up);
            textView_Clothing_ability.setText(R.string.ability_ink_recovery_up);
            textView_Shoes_ability.setText(R.string.ability_ink_saver_sub);
        }
        else if(msg.equals("imgButton02")) {
            textView.setText(R.string.name_inkling_boy);
            imageView.setImageResource(R.drawable.amiibo_inkling_boy);
            imageView_Headgear.setImageResource(R.drawable.gear_headgear_samurai_helmet);
            textView_Headgear.setText(R.string.gear_headgear_samurai_helmet);
            imageView_Clothing.setImageResource(R.drawable.gear_clothing_samurai_jacket);
            textView_Clothing.setText(R.string.gear_clothing_samurai_jacket);
            imageView_Shoes.setImageResource(R.drawable.gear_shoes_samurai_shoes);
            textView_Shoes.setText(R.string.gear_shoes_samurai_shoes);
            imageView_Headgear_ability.setImageResource(R.drawable.ability_special_charge_up);
            imageView_Clothing_ability.setImageResource(R.drawable.ability_quick_super_jump);
            imageView_Shoes_ability.setImageResource(R.drawable.ability_special_power_up);
            textView_Headgear_ability.setText(R.string.ability_special_charge_up);
            textView_Clothing_ability.setText(R.string.ability_quick_super_jump);
            textView_Shoes_ability.setText(R.string.ability_special_power_up);
        }
    }
}
