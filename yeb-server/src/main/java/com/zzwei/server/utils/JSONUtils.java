package com.zzwei.server.utils;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JSONUtils {


    public static void main(String[] args) throws JsonProcessingException {
        //对象
        Student beanString = new Student(1, 1, 1, "张三");

        //集合
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, 1, 1, "张三"));
        list.add(new Student(1, 1, 2, "李四"));
        list.add(new Student(1, 1, 3, "王五"));

        System.out.println(new ObjectMapper().writeValueAsString(beanString));
        System.out.println("JSONUtil.toJsonStr(beanString) = " + JSONUtil.toJsonStr(beanString));
        System.out.println("JSONUtil.toJsonStr(list) = " + JSONUtil.toJsonStr(list));


    }


    private static class Student implements Serializable {

        private Integer id;
        private Integer age;
        private Integer sex;
        private String name;

        public Student() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Student(Integer id, Integer age, Integer sex, String name) {
            this.id = id;
            this.age = age;
            this.sex = sex;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", age=" + age +
                    ", sex=" + sex +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
