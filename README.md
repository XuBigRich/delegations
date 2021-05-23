# delegations
  双亲委派机制的学习
  middleware1 与 middleware2 分别依赖于support 0.0.1 与 support 0.0.2
  support 0.0.1 与 support 0.0.2 区别在于PersonImpl 类是否存在singe 方法
 
  第一步：
    分别将support 0.0.1 与 support 0.0.2 打包 安装   (放入.m2仓库)
  第二步：
    分别将 middleware1 与 middleware2 打包 安装  (放入.m2仓库)
  第三步：
    执行命令
    java -classpath /Users/xuhongzhi/Workspacess/delegation/platform/target/classes:/Users/xuhongzhi/.m2/repository/cn/piao888/middleware1/0.0.1-SNAPSHOT/middleware1-0.0.1-SNAPSHOT.jar:/Users/xuhongzhi/.m2/repository/cn/piao888/support/0.0.1-SNAPSHOT/support-0.0.1-SNAPSHOT.jar:/Users/xuhongzhi/.m2/repository/cn/piao888/support/0.0.2-SNAPSHOT/support-0.0.2-SNAPSHOT.jar:/Users/xuhongzhi/.m2/repository/cn/piao888/middleware2/0.0.1-SNAPSHOT/middleware2-0.0.1-SNAPSHOT.jar cn.piao888.platform.PlatformApplication
 
   控制台将打印：
  sun.misc.Launcher$AppClassLoader@73d16e93
  我是说界巨星
  cn.piao888.middleware1.classLoader.CustomClassLoader@45ee12a7
  我是说界巨星
  我是唱界巨星
  cn.piao888.middleware2.classLoader.CustomClassLoader@d716361
  我是说界巨星
 
  之所以jvm可以同时加载两个相同的cn.piao888.support.service.PersonImpl (不同版本)
  是因为 middleware1 与 middleware2 同时拥有平级的类加载器 然后他们分别将两个不同版本的PersonImpl装载进了jvm中