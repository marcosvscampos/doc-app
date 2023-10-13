package org.roguesoft.docapp.infrastructure.utils;

import lombok.Getter;

import java.util.Random;

@Getter
public abstract class RandomValueGenerator {

    private final Random random;

    protected RandomValueGenerator(){
        this.random = new Random();
    }

    public abstract String generate(final Integer size);

}
