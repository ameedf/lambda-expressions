package com.ameed;

public class SpaceReplacerManipulator implements StringManipulator {
    @Override
    public String manipulate(String text1, String text2) {
        return text1.replace(" ", text2);
    }
}
