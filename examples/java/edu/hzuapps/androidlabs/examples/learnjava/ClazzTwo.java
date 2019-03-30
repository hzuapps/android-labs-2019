package edu.hzuapps.androidlabs.examples.learnjava;

import java.io.File;
import java.io.FileWriter;

public class ClazzTwo extends ClazzOne {

    int count;

    ClazzTwo(int number) {
        super(number);
    }

    @Override
    void plusOne() {
        count++;
        super.plusOne();
    }

    void show() {
        ClazzOne one = new ClazzOne(1);

        String str1 = "abc";

        String str2 = "abc";

        if (str1.equals(str2)) {

        }

        char ch = 'a';
        String str = "a";
    }
}

class ClazzThree {}
