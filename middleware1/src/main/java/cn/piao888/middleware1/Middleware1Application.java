package cn.piao888.middleware1;

import cn.piao888.middleware1.classLoader.CustomClassLoader;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class Middleware1Application {
        {
        ClassLoader classLoader = this.getClass().getClassLoader();
        System.out.println(classLoader);
        }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, MalformedURLException {
             String jarWholePath = Middleware1Application.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        try {
            jarWholePath = java.net.URLDecoder.decode(jarWholePath, "UTF-8");
        } catch (UnsupportedEncodingException e) { 
            System.out.println(e.toString()); 
        }
        String localPath = new File(jarWholePath).getParentFile().getAbsolutePath();
        String jarPath="file:/"+localPath+"/../../support/target/support-0.0.1-SNAPSHOT.jar";
        CustomClassLoader customClassLoader = new CustomClassLoader(jarPath, null);  
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
