package com.zzwei.generator;

public class MainGenerator {
    public static void main(String[] args) {
        MysqlGenerator mysqlGenerator = new MysqlGenerator("localhost", 3306, "test", "root", "weizhuang1994");
        mysqlGenerator.setParent("com.nsc.boot.security.modules");
        mysqlGenerator.run("security", "sys_user");
    }
}
