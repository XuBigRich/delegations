package cn.piao888.middleware2.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @Author： hongzhi.xu
 * @Date: 2021/5/22 上午6:43
 * @Version 1.0
 */
public class CustomClassLoader extends URLClassLoader {
    private String director;//被加载类所在的目录

    //    public CustomClassLoader(String director, ClassLoader parent) {
//        super(parent);
//        this.director = director;
//    }
    public CustomClassLoader(String parent, ClassLoader classLoader) throws MalformedURLException {
        super(new URL[]{new URL(parent)}, classLoader);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

//    @Override
//    public Class<?> findClass(String name) throws ClassNotFoundException {
//        try {
//            //把类名转化为目录
//            String file = director + File.separator + name.replace('.', '/') + ".class"; //这个地方就是把完全限定名的.变成/（系统识别的）
//            FileInputStream fileInputStream = new FileInputStream(file);
//            System.out.println("1");
//            byte[] buf = new byte[1024];
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//            int len = -1;
//            while ((len = fileInputStream.read(buf)) != -1) {
//                outputStream.write(buf, 0, len);
//            }
//            System.out.println("2");
//            byte[] data = outputStream.toByteArray(); //读取到的字节码的二进制数字
//            fileInputStream.close();
//            outputStream.close();
//            return defineClass(name, data, 0, data.length);
//        } catch (IOException e) {
//            throw new RuntimeException();
//        }
//    }
}
