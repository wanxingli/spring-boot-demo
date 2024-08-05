package com.sb.demo.dining.philosopher.problem;

import java.util.HashMap;
import java.util.Map;

public class DeadLockOpen {
    
    public static void main(String[] args) {
        Chopsticks ch0 = new Chopsticks(0);
        Chopsticks ch1 = new Chopsticks(1);
        Chopsticks ch2 = new Chopsticks(2);
        Chopsticks ch3 = new Chopsticks(3);
        Chopsticks ch4 = new Chopsticks(4);

        Philosopher2 ph0 = new Philosopher2(0, "ph0", ch0, ch1);
        Philosopher2 ph1 = new Philosopher2(1, "ph1", ch1, ch2);
        Philosopher2 ph2 = new Philosopher2(2, "ph2", ch2, ch3);
        Philosopher2 ph3 = new Philosopher2(3, "ph3", ch3, ch4);
        Philosopher2 ph4 = new Philosopher2(4, "ph4", ch4, ch0);

        ph0.start();
        ph1.start();
        ph2.start();
        ph3.start();
        ph4.start();

        Map<String, String> map = new HashMap<>(16);



    }
}
