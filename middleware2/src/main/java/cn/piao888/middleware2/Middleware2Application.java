package cn.piao888.middleware2;

import cn.piao888.middleware2.classLoader.CustomClassLoader;
import sun.misc.Launcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;


public class Middleware2Application {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, MalformedURLException {
        Launcher launcher = new Launcher();
        CustomClassLoader customClassLoader = new CustomClassLoader("file:///Users/xuhongzhi/.m2/repository/cn/piao888/support/0.0.1-SNAPSHOT/support-0.0.1-SNAPSHOT.jar",null);
        Class object = customClassLoader.loadClass("cn.piao888.support.service.PersonImpl");
        Object person =  object.newInstance();
        Method[] methods =person.getClass().getDeclaredMethods();
        for (Method method:methods){
            method.invoke(person);
        }

    }

}