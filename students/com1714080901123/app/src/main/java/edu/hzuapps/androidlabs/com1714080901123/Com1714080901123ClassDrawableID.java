package edu.hzuapps.androidlabs.com1714080901123;

public class Com1714080901123ClassDrawableID {

    protected static int[] weapon = new int[]{
            R.drawable.weapon_main_enperry_splat_dualies,
            R.drawable.weapon_main_kensa_charger
    };
    protected static int[] subWeapon = new int[]{
            R.drawable.weapon_sub_curling_bomb,
            R.drawable.weapon_sub_sprinkler
    };
    protected static int[] specialWeapon = new int[]{
            R.drawable.weapon_special_inkjet,
            R.drawable.weapon_special_baller
    };
    protected static int[] weaponText = new int[]{
            R.string.weapon_main_enperry_splat_dualies,
            R.string.weapon_main_kensa_charger
    };
    protected static int[] subWeaponText = new int[]{
            R.string.weapon_special_inkjet,
            R.string.weapon_special_baller
    };
    protected static int[] specialWeaponText = new int[]{
            R.string.weapon_special_inkjet,
            R.string.weapon_special_baller
    };
    protected static int[] ability = new int[]{
            R.drawable.ability_ink_saver_sub,
            R.drawable.ability_ink_recovery_up,
            R.drawable.ability_swim_speed_up,
            R.drawable.ability_quick_super_jump
    };
    protected static int[] abilityText = new int[]{
            R.string.ability_ink_saver_sub,
            R.string.ability_ink_recovery_up,
            R.string.ability_swim_speed_up,
            R.string.ability_quick_super_jump
    };

    public int getWeapon(int i) {
        return weapon[i];
    }
    public int getWeaponText(int i) {
        return weaponText[i];
    }
    public int getSubWeapon(int i) {
        return subWeapon[i];
    }
    public int getSubWeaponText(int i) {
        return subWeaponText[i];
    }
    public int getSpecialWeapon(int i) {
        return specialWeapon[i];
    }
    public int getSpecialWeaponText(int i) {
        return specialWeaponText[i];
    }
    public int getAbility(int i) {
        return ability[i];
    }
    public int getAbilityText(int i) {
        return abilityText[i];
    }
}
