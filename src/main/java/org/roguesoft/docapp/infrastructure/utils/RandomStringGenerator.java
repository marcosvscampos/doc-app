package org.roguesoft.docapp.infrastructure.utils;

import java.util.Random;

public class RandomStringGenerator implements RandomValueGenerator {
    @Override
    public String generate(Integer size) {
        final Random random = new Random();
        StringBuilder randomLettersAndDigits = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (int i = 0; i < size; i++) {
            int index = random.nextInt(characters.length());
            randomLettersAndDigits.append(characters.charAt(index));
        }
        return randomLettersAndDigits.toString();
    }
}
