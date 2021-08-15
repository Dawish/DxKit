package com.dxkit.demo.test;

/**
 * 测试object
 *
 * @author danxingxi
 */
public class TestBean {

    private String name;
    private int age;

    public TestBean() {

    }

    public TestBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
