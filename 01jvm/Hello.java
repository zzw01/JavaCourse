package entity;

import java.util.List;

/**
 * 第一周jvm作业：第一题-选做
 * 自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论
 */
public class Hello {
    /**
     * 年龄
     */
    private int age;

    /**
     * 姓
     */
    private String surname;

    /**
     * 名
     */
    private String firstName;

    /**
     * 成长
     *
     * @return
     */
    public int addAge() {
        int age = this.age;
        age++;
        this.age = age;
        return age;
    }

    /**
     * 是否成年
     *
     * @return
     */
    public boolean isAdult() {
        if (this.age >= 18) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取姓名全称
     * @return
     */
    public String getName() {
        String surname = this.surname;
        String firstName = this.firstName;
        String name = surname + firstName;
        return name;
    }


}
