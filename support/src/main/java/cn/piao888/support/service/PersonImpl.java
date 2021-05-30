package cn.piao888.support.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * @Author： hongzhi.xu
 * @Date: 2021/5/21 上午6:49
 * @Version 1.0
 */
public class PersonImpl implements Person {
    {
        ClassLoader classLoader = this.getClass().getClassLoader();
        System.out.println(classLoader);
        try {
            //不同类加载器执行这个方法时会得到不一样的输出
            getProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void say() {
        System.out.println("我是说界巨星");
    }

            @Override
    public void singe() {
        System.out.println("我是唱界巨星");
    }
//将URL路径下的配置文件转换为Properties对象
    private Properties getProperties(URL url) throws IOException {
        InputStream inputStream = url.openStream();
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream, "utf-8"));
        return properties;
    }

    //读取路径下的key
    @Override
    public void getProperties() throws IOException {
        List<String> results = new ArrayList<String>();
        //使用上下文加载器 加载
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader = this.getClass().getClassLoader();
        Enumeration<URL> urls = classLoader.getResources("application.properties");
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            Properties properties = getProperties(url);
            Object result = properties.get("key");
            if (result != null) {
                results.add((String) result);
            }
        }
        System.out.println(results);
    }

}
