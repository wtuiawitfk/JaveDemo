package com.yuhh.base;

public class Hero {
    String name;
    float hp;
    float armor;
    int moveSpeed;

    public Hero(String name, float hp, float armor, int moveSpeed) {
        this.name = name;
        this.hp = hp;
        this.armor = armor;
        this.moveSpeed = moveSpeed;
    }

    public void keng() {
        System.out.println("坑队友");
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", armor=" + armor +
                ", moveSpeed=" + moveSpeed +
                '}';
    }

    public static void main(String[] args) {
        Hero aser = new Hero("亚瑟", 100, 100, 50);
        Hero ppp = new Hero("钟无艳", 100, 100, 80);

        System.out.println(aser);
        System.out.println(ppp);

        aser.keng();
    }
}
