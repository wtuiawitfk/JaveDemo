package com.yuhh.middle.jdbc.smis.domain;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("t_student")
public class Student {
    private Long id;
    @Column("name")
    private String name;
    @Column("age")
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
