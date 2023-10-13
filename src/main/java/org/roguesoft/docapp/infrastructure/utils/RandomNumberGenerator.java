package org.roguesoft.docapp.infrastructure.utils;

import java.util.Random;

public class RandomNumberGenerator implements RandomValueGenerator {

    @Override
    public String generate(Integer size) {
        StringBuilder randomDigits = new StringBuilder();
        final Random random = new Random();
        for (int i = 0; i < size; i++) {
            randomDigits.append(random.nextInt(size));
        }
        return randomDigits.toString();
    }
}
