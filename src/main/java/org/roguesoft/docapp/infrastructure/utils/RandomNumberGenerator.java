package org.roguesoft.docapp.infrastructure.utils;

public class RandomNumberGenerator extends RandomValueGenerator {

    public RandomNumberGenerator(){
        super();
    }

    @Override
    public String generate(Integer size) {
        StringBuilder randomDigits = new StringBuilder();
        for (int i = 0; i < size; i++) {
            randomDigits.append(super.getRandom().nextInt(size));
        }
        return randomDigits.toString();
    }
}
