package cn.piao888.platform;

import cn.piao888.middleware1.Middleware1Application;
import cn.piao888.middleware2.Middleware2Application;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * 双亲委派机制的学习
 * middleware1 与 middleware2 分别依赖于support 0.0.1 与 support 0.0.2
 * support 0.0.1 与 support 0.0.2 区别在于PersonImpl 类是否存在singe 方法
 * <p>
 * 第一步：
 * 分别将support 0.0.1 与 support 0.0.2 打包 安装   (放入.m2仓库)
 * 第二步：
 * 分别将 middleware1 与 middleware2 打包 安装  (放入.m2仓库)
 * 第三步：
 * 执行命令
 * java -classpath /Users/xuhongzhi/Workspacess/delegation/platform/target/classes:/Users/xuhongzhi/.m2/repository/cn/piao888/middleware1/0.0.1-SNAPSHOT/middleware1-0.0.1-SNAPSHOT.jar:/Users/xuhongzhi/.m2/repository/cn/piao888/support/0.0.1-SNAPSHOT/support-0.0.1-SNAPSHOT.jar:/Users/xuhongzhi/.m2/repository/cn/piao888/support/0.0.2-SNAPSHOT/support-0.0.2-SNAPSHOT.jar:/Users/xuhongzhi/.m2/repository/cn/piao888/middleware2/0.0.1-SNAPSHOT/middleware2-0.0.1-SNAPSHOT.jar cn.piao888.platform.PlatformApplication
 * <p>
 * 控制台将打印：
 * sun.misc.Launcher$AppClassLoader@73d16e93
 * 我是说界巨星
 * cn.piao888.middleware1.classLoader.CustomClassLoader@45ee12a7
 * 我是说界巨星
 * 我是唱界巨星
 * cn.piao888.middleware2.classLoader.CustomClassLoader@d716361
 * 我是说界巨星
 * <p>
 * 之所以jvm可以同时加载两个相同的cn.piao888.support.service.PersonImpl (不同版本)
 * 是因为 middleware1 与 middleware2 同时拥有平级的类加载器 然后他们分别将两个不同版本的PersonImpl装载进了jvm中
 */
public class PlatformApplication {
	{
        ClassLoader classLoader = this.getClass().getClassLoader();
        System.out.println(classLoader);
    }

   private Properties getProperties(URL url) throws IOException {
        InputStream inputStream = url.openStream();
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream, "utf-8"));
        return properties;
    }

    //读取路径下的key
    private void getProperties() throws IOException {
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
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IOException, InstantiationException, IllegalAccessException {

        PlatformApplication platformApplication = new PlatformApplication();
      	platformApplication.getProperties();
        Middleware1Application.main(new String[]{"1", "2"});
        platformApplication.getProperties();
        Middleware2Application.main(new String[]{"1", "2"});
        platformApplication.getProperties();
    }

}
