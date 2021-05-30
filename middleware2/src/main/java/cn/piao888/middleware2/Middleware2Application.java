package cn.piao888.middleware2;

import cn.piao888.middleware2.classLoader.CustomClassLoader;
import cn.piao888.support.service.Person;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;


public class Middleware2Application {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, MalformedURLException {
        CustomClassLoader customClassLoader = new CustomClassLoader("file://../../support/target/support-2.0.jar", null);
        customClassLoader.loadClass("cn.piao888.support.service.Person");
        Class object = customClassLoader.loadClass("cn.piao888.support.service.PersonImpl");
        Person person = (Person) object.newInstance();
        person.say();
        person.singe();
    }

}
