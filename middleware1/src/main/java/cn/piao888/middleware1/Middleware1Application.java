package cn.piao888.middleware1;

import cn.piao888.middleware1.classLoader.CustomClassLoader;
import cn.piao888.support.service.Person;
import cn.piao888.support.service.PersonImpl;
import sun.misc.Launcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class Middleware1Application {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, MalformedURLException {
        Launcher launcher = new Launcher();
//        CustomClassLoader customClassLoader = new CustomClassLoader("file:///Users/xuhongzhi/.m2/repository/cn/piao888/support/0.0.2-SNAPSHOT/support-0.0.2-SNAPSHOT.jar", null);
        CustomClassLoader customClassLoader = new CustomClassLoader("file:/C:\\Users\\DELL\\.m2\\repository\\cn\\piao888\\support\\0.0.2-SNAPSHOT\\support-0.0.2-SNAPSHOT.jar", null);
        Class object = customClassLoader.loadClass("cn.piao888.support.service.PersonImpl");
        Object person =  object.newInstance();
        Method[] methods =person.getClass().getDeclaredMethods();
        for (Method method:methods){
            String methodString=method.toGenericString();
            if(methodString.contains("public")){
                method.invoke(person);
            }

        }
    }

}
