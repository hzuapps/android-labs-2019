package edu.hzuapps.androidlabs.com1714080901123;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.hzuapps.androidlabs.com1714080901123.Com1714080901123ClassDrawableID;
import edu.hzuapps.androidlabs.com1714080901123.Com1714080901123ClassGearConfig;

public class Com1714080901123FragmentCounter extends Fragment {

    protected Context context;
    protected Com1714080901123ClassDrawableID drawableID = new Com1714080901123ClassDrawableID();

    protected static int selectState = 1;
    protected static int requestState = 1;
    protected static int resultState = 1;

    protected static int weaponID = 1;
    protected static int abilityID11 = 1;   //ID后两个数字相当于下标
    protected static int abilityID12 = 1;
    protected static int abilityID13 = 1;
    protected static int abilityID14 = 1;
    protected static int abilityID21 = 1;
    protected static int abilityID22 = 1;
    protected static int abilityID23 = 1;
    protected static int abilityID24 = 1;
    protected static int abilityID31 = 1;
    protected static int abilityID32 = 1;
    protected static int abilityID33 = 1;
    protected static int abilityID34 = 1;
    /*
    protected static int[] changeID = new int[]{weaponID,
            abilityID11, abilityID12, abilityID13, abilityID14,
            abilityID21, abilityID22, abilityID23, abilityID24,
            abilityID31, abilityID32, abilityID33, abilityID34};    //方便统一操作，省去if判断
    //*/

    protected static ImageButton imageButtonWeapon; //声明在外面，以便updateCounterView()调用
    protected static ImageView imageViewWeaponSub;
    protected static ImageView imageViewWeaponSpecial;
    protected static ImageButton imageButtonHeadAbility1;
    protected static ImageButton imageButtonHeadAbility2;
    protected static ImageButton imageButtonHeadAbility3;
    protected static ImageButton imageButtonHeadAbility4;
    protected static ImageButton imageButtonClothingAbility1;
    protected static ImageButton imageButtonClothingAbility2;
    protected static ImageButton imageButtonClothingAbility3;
    protected static ImageButton imageButtonClothingAbility4;
    protected static ImageButton imageButtonShoesAbility1;
    protected static ImageButton imageButtonShoesAbility2;
    protected static ImageButton imageButtonShoesAbility3;
    protected static ImageButton imageButtonShoesAbility4;

    protected static TextView textViewWeaponMain;
    protected static TextView textViewWeaponSub;
    protected static TextView textViewWeaponSpecial;
    private static TextView textViewAbilityHeadgear;
    private static TextView textViewAbilityClothing;
    private static TextView textViewAbilityShoes;

    private static Button buttonLoadOthersConfig;

    public Com1714080901123FragmentCounter() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_com1714080901123_fragment_counter, container, false);

        imageButtonWeapon = (ImageButton)view.findViewById(R.id.imgButton_weapon_main);
        imageViewWeaponSub = (ImageView)view.findViewById(R.id.img_weapon_sub);
        imageViewWeaponSpecial = (ImageView)view.findViewById(R.id.img_weapon_special);

        imageButtonHeadAbility1 = (ImageButton)view.findViewById(R.id.imgButton_ability_headgear_1);
        imageButtonHeadAbility2 = (ImageButton)view.findViewById(R.id.imgButton_ability_headgear_2);
        imageButtonHeadAbility3 = (ImageButton)view.findViewById(R.id.imgButton_ability_headgear_3);
        imageButtonHeadAbility4 = (ImageButton)view.findViewById(R.id.imgButton_ability_headgear_4);
        imageButtonClothingAbility1 = (ImageButton)view.findViewById(R.id.imgButton_ability_clothing_1);
        imageButtonClothingAbility2 = (ImageButton)view.findViewById(R.id.imgButton_ability_clothing_2);
        imageButtonClothingAbility3 = (ImageButton)view.findViewById(R.id.imgButton_ability_clothing_3);
        imageButtonClothingAbility4 = (ImageButton)view.findViewById(R.id.imgButton_ability_clothing_4);
        imageButtonShoesAbility1 = (ImageButton)view.findViewById(R.id.imgButton_ability_shoes_1);
        imageButtonShoesAbility2 = (ImageButton)view.findViewById(R.id.imgButton_ability_shoes_2);
        imageButtonShoesAbility3 = (ImageButton)view.findViewById(R.id.imgButton_ability_shoes_3);
        imageButtonShoesAbility4 = (ImageButton)view.findViewById(R.id.imgButton_ability_shoes_4);

        textViewWeaponMain = (TextView)view.findViewById(R.id.text_weapon_main);
        textViewWeaponSub = (TextView)view.findViewById(R.id.text_weapon_sub);
        textViewWeaponSpecial = (TextView)view.findViewById(R.id.text_weapon_special);
        textViewAbilityHeadgear = (TextView)view.findViewById(R.id.text_ability_headgear_1);
        textViewAbilityClothing = (TextView)view.findViewById(R.id.text_ability_clothing_1);
        textViewAbilityShoes = (TextView)view.findViewById(R.id.text_ability_shoes_1);

        Button buttonConfig1 = (Button)view.findViewById(R.id.button_config_1);
        Button buttonConfig2 = (Button)view.findViewById(R.id.button_config_2);
        Button buttonConfig3 = (Button)view.findViewById(R.id.button_config_3);
        Button buttonConfig4 = (Button)view.findViewById(R.id.button_config_4);

        Button buttonSaveConfig1 = (Button)view.findViewById(R.id.button_save_config_1);
        Button buttonSaveConfig2 = (Button)view.findViewById(R.id.button_save_config_2);
        Button buttonSaveConfig3 = (Button)view.findViewById(R.id.button_save_config_3);
        Button buttonSaveConfig4 = (Button)view.findViewById(R.id.button_save_config_4);

        buttonLoadOthersConfig = (Button)view.findViewById(R.id.button_load_others_config);

        //武器选择按键监听:
        imageButtonWeapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Com1714080901123ActivitySelectWeapon.class);   //Fragment获取activity要用getActivity()
                Com1714080901123FragmentCounter.this.startActivityForResult(intent,1);
            }
        });

        //头部装备技能选择按键监听:
        imageButtonHeadAbility1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                clickImgButtonSelectAbility(2);
            }
        });
        imageButtonHeadAbility2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                clickImgButtonSelectAbility(3);
            }
        });
        imageButtonHeadAbility3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                clickImgButtonSelectAbility(4);
            }
        });
        imageButtonHeadAbility4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                clickImgButtonSelectAbility(5);
            }
        });

        //衣服技能选择按键监听:
        imageButtonClothingAbility1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                clickImgButtonSelectAbility(6);
            }
        });
        imageButtonClothingAbility2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                clickImgButtonSelectAbility(7);
            }
        });
        imageButtonClothingAbility3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                clickImgButtonSelectAbility(8);
            }
        });
        imageButtonClothingAbility4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                clickImgButtonSelectAbility(9);
            }
        });

        //鞋子技能选择按键监听:
        imageButtonShoesAbility1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                clickImgButtonSelectAbility(10);
            }
        });
        imageButtonShoesAbility2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                clickImgButtonSelectAbility(11);
            }
        });
        imageButtonShoesAbility3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                clickImgButtonSelectAbility(12);
            }
        });
        imageButtonShoesAbility4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                clickImgButtonSelectAbility(13);
            }
        });

        //配置按键 监听:
        buttonConfig1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtonConfig(1);
            }
        });
        buttonConfig2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtonConfig(2);
            }
        });
        buttonConfig3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtonConfig(3);
            }
        });
        buttonConfig4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtonConfig(4);
            }
        });
        buttonSaveConfig1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtonSaveConfig(1);
            }
        });
        buttonSaveConfig2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtonSaveConfig(2);
            }
        });
        buttonSaveConfig3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtonSaveConfig(3);
            }
        });
        buttonSaveConfig4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtonSaveConfig(4);
            }
        });
        //加载其他玩家 按键 监听
        buttonLoadOthersConfig.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Com1714080901123ActivitySelectPlayersConfig.class);
                Com1714080901123FragmentCounter.this.startActivityForResult(intent, 0);
            }
        });

        return view;
    }

    public void clickImgButtonSelectAbility(int i) {    //startActivityForResult时，根据不同的按键传递不同的数据，表明返回时应该修改的按键
        Intent intent = new Intent();
        intent.setClass(getActivity(), Com1714080901123ActivitySelectAbility.class);   //Fragment获取activity要用getActivity()
        Com1714080901123FragmentCounter.this.startActivityForResult(intent,i);
    }

    public void clickButtonConfig(int i) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Config", Context.MODE_PRIVATE);
        weaponID = sharedPreferences.getInt("Config"+i+"Weapon", 0);
        abilityID11 = sharedPreferences.getInt("Config"+i+"Ability11", 0);
        abilityID12 = sharedPreferences.getInt("Config"+i+"Ability12", 0);
        abilityID13 = sharedPreferences.getInt("Config"+i+"Ability13", 0);
        abilityID14 = sharedPreferences.getInt("Config"+i+"Ability14", 0);
        abilityID21 = sharedPreferences.getInt("Config"+i+"Ability21", 0);
        abilityID22 = sharedPreferences.getInt("Config"+i+"Ability22", 0);
        abilityID23 = sharedPreferences.getInt("Config"+i+"Ability23", 0);
        abilityID24 = sharedPreferences.getInt("Config"+i+"Ability24", 0);
        abilityID31 = sharedPreferences.getInt("Config"+i+"Ability31", 0);
        abilityID32 = sharedPreferences.getInt("Config"+i+"Ability32", 0);
        abilityID33 = sharedPreferences.getInt("Config"+i+"Ability33", 0);
        abilityID34 = sharedPreferences.getInt("Config"+i+"Ability34", 0);
        updateCounterView();
        Toast.makeText(context, "读取成功", Toast.LENGTH_SHORT).show();
    }

    public void clickButtonSaveConfig(int i) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Config"+i+"Weapon", weaponID);
        editor.putInt("Config"+i+"Ability11", abilityID11);
        editor.putInt("Config"+i+"Ability12", abilityID12);
        editor.putInt("Config"+i+"Ability13", abilityID13);
        editor.putInt("Config"+i+"Ability14", abilityID14);
        editor.putInt("Config"+i+"Ability21", abilityID21);
        editor.putInt("Config"+i+"Ability22", abilityID22);
        editor.putInt("Config"+i+"Ability23", abilityID23);
        editor.putInt("Config"+i+"Ability24", abilityID24);
        editor.putInt("Config"+i+"Ability31", abilityID31);
        editor.putInt("Config"+i+"Ability32", abilityID32);
        editor.putInt("Config"+i+"Ability33", abilityID33);
        editor.putInt("Config"+i+"Ability34", abilityID34);
        editor.commit();
        Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
    }

    public void updateCounterView() {
        //武器更新：
        if(weaponID != 0) {
            imageButtonWeapon.setImageResource(drawableID.getWeapon(weaponID-1));
            imageViewWeaponSub.setImageResource(drawableID.getSubWeapon(weaponID-1));
            imageViewWeaponSpecial.setImageResource(drawableID.getSpecialWeapon(weaponID-1));
            textViewWeaponMain.setText(drawableID.getWeaponText(weaponID-1));
            textViewWeaponSub.setText(drawableID.getSubWeaponText(weaponID-1));
            textViewWeaponSpecial.setText(drawableID.getSpecialWeaponText(weaponID-1));
        }
        //头部装备技能更新：
        if(abilityID11 != 0) {
            imageButtonHeadAbility1.setImageResource(drawableID.getAbility(abilityID11-1));
            textViewAbilityHeadgear.setText(drawableID.getAbilityText(abilityID11-1));
        }
        if(abilityID12 != 0) {
            imageButtonHeadAbility2.setImageResource(drawableID.getAbility(abilityID12-1));
        }
        if(abilityID13 != 0) {
            imageButtonHeadAbility3.setImageResource(drawableID.getAbility(abilityID13-1));
        }
        if(abilityID14 != 0) {
            imageButtonHeadAbility4.setImageResource(drawableID.getAbility(abilityID14-1));
        }
        //衣服技能更新：
        if(abilityID21 != 0) {
            imageButtonClothingAbility1.setImageResource(drawableID.getAbility(abilityID21-1));
            textViewAbilityClothing.setText(drawableID.getAbilityText(abilityID21-1));
        }
        if(abilityID22 != 0) {
            imageButtonClothingAbility2.setImageResource(drawableID.getAbility(abilityID22-1));
        }
        if(abilityID23 != 0) {
            imageButtonClothingAbility3.setImageResource(drawableID.getAbility(abilityID23-1));
        }
        if(abilityID24 != 0) {
            imageButtonClothingAbility4.setImageResource(drawableID.getAbility(abilityID24-1));
        }
        //鞋子技能更新：
        if(abilityID31 != 0) {
            imageButtonShoesAbility1.setImageResource(drawableID.getAbility(abilityID31-1));
            textViewAbilityShoes.setText(drawableID.getAbilityText(abilityID31-1));
        }
        if(abilityID32 != 0) {
            imageButtonShoesAbility2.setImageResource(drawableID.getAbility(abilityID32-1));
        }
        if(abilityID33 != 0) {
            imageButtonShoesAbility3.setImageResource(drawableID.getAbility(abilityID33-1));
        }
        if(abilityID34 != 0) {
            imageButtonShoesAbility4.setImageResource(drawableID.getAbility(abilityID34-1));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int content = 0;
        if (requestCode == 0) {
            weaponID = data.getIntExtra("weaponID", 1);
            abilityID11 = data.getIntExtra("abilityID11",1);
            abilityID12 = data.getIntExtra("abilityID12",1);
            abilityID13 = data.getIntExtra("abilityID13",1);
            abilityID14 = data.getIntExtra("abilityID14",1);
            abilityID21 = data.getIntExtra("abilityID21",1);
            abilityID22 = data.getIntExtra("abilityID22",1);
            abilityID23 = data.getIntExtra("abilityID23",1);
            abilityID24 = data.getIntExtra("abilityID24",1);
            abilityID31 = data.getIntExtra("abilityID31",1);
            abilityID32 = data.getIntExtra("abilityID32",1);
            abilityID33 = data.getIntExtra("abilityID33",1);
            abilityID34 = data.getIntExtra("abilityID34",1);
        }
        else if (requestCode == 1)
            content = data.getIntExtra("WeaponSelect", 0);
        else
            content = data.getIntExtra("AbilitySelect", 0);

        selectState = content;      //对应选择界面的选项
        requestState = requestCode; // 1为武器选择，2-13为对应的位置的技能
        resultState = resultCode;   // 1为武器选择，2为技能选择
        //Log.d("Weapon", "success"); //test
        //Log.d("selectState", ""+ selectState);   //test
        //Log.d("requestState",""+ requestCode);   //test
        //Log.d("resultState", ""+ resultCode);    //test
    }

    @Override
    public void onResume() {
        ///*
        if (requestState==0 && resultState==0) {
            updateCounterView();
            Toast.makeText(context, "加载成功", Toast.LENGTH_SHORT).show();
        }
        else if(requestState==1 && resultState==1){
            weaponID = selectState;
            updateCounterView();
        }
        else if(requestState==2 && resultState==2) {
            abilityID11 = selectState;
            updateCounterView();
        }
        else if(requestState==3 && resultState==2) {
            abilityID12 = selectState;
            updateCounterView();
        }
        else if(requestState==4 && resultState==2) {
            abilityID13 = selectState;
            updateCounterView();
        }
        else if(requestState==5 && resultState==2) {
            abilityID14 = selectState;
            updateCounterView();
        }
        else if(requestState==6 && resultState==2) {
            abilityID21 = selectState;
            updateCounterView();
        }
        else if(requestState==7 && resultState==2) {
            abilityID22 = selectState;
            updateCounterView();
        }
        else if(requestState==8 && resultState==2) {
            abilityID23 = selectState;
            updateCounterView();
        }
        else if(requestState==9 && resultState==2) {
            abilityID24 = selectState;
            updateCounterView();
        }
        else if(requestState==10 && resultState==2) {
            abilityID31 = selectState;
            updateCounterView();
        }
        else if(requestState==11 && resultState==2) {
            abilityID32 = selectState;
            updateCounterView();
        }
        else if(requestState==12 && resultState==2) {
            abilityID33 = selectState;
            updateCounterView();
        }
        else if(requestState==13 && resultState==2) {
            abilityID34 = selectState;
            updateCounterView();
        }
        //*/
        /*不知道为什么不起作用，只好用if了
        changeID[requestState-1] = selectState;
        updateCounterView();
        //*/
        super.onResume();
    }
}
