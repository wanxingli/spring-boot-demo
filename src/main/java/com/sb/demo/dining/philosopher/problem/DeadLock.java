package com.sb.demo.dining.philosopher.problem;

public class DeadLock {

    public static void main(String[] args) {
        Chopsticks ch0 = new Chopsticks(0);
        Chopsticks ch1 = new Chopsticks(1);
        Chopsticks ch2 = new Chopsticks(2);
        Chopsticks ch3 = new Chopsticks(3);
        Chopsticks ch4 = new Chopsticks(4);

        Philosopher1 ph0 = new Philosopher1(0, "ph0", ch0, ch1);
        Philosopher1 ph1 = new Philosopher1(1, "ph1", ch1, ch2);
        Philosopher1 ph2 = new Philosopher1(2, "ph2", ch2, ch3);
        Philosopher1 ph3 = new Philosopher1(3, "ph3", ch3, ch4);
        Philosopher1 ph4 = new Philosopher1(4, "ph4", ch4, ch0);

        ph0.start();
        ph1.start();
        ph2.start();
        ph3.start();
        ph4.start();

    }
}
