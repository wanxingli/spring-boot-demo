package com.sb.demo.dining.philosopher.problem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chopsticks {
    int index;

    public Chopsticks(int index) {
        this.setIndex(index);
    }
}
