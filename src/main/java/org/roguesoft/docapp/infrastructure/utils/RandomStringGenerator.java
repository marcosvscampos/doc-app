package org.roguesoft.docapp.infrastructure.utils;

public class RandomStringGenerator extends RandomValueGenerator {

    public RandomStringGenerator(){
        super();
    }

    @Override
    public String generate(Integer size) {
        StringBuilder randomLettersAndDigits = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (int i = 0; i < size; i++) {
            int index = super.getRandom().nextInt(characters.length());
            randomLettersAndDigits.append(characters.charAt(index));
        }
        return randomLettersAndDigits.toString();
    }
}
