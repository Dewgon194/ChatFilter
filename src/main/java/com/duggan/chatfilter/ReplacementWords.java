package com.duggan.chatfilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReplacementWords {

    public String replacementWords() {
        List words = new ArrayList();
        words.add(0,"Cabbage");
        words.add(1,"Banana");
        words.add(2,"Potato");
        words.add(3,"Turnip");
        words.add(4,"Cucumber");
        words.add(5,"Pickles");
        words.add(6,"Pineapples");
        words.add(7,"Carrots");
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(7) + 1;
        String replacement = (String) words.get(randomInt);
        return replacement;
    }


}
