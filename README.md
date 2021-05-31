#  双亲委派机制,与Javac打包命令的学习
  *  此项目将完全不依赖于maven 进行依赖打包 若要执行这个项目可以使用以下命令进行打包
  ## 关于Javac的打包命令
  
  * support的生成
  首先进入support项目
  因为项目需要 要分别打包一个0.0.1的版本与0.0.2的版本的jar包
  修改完后执行
  1.  编译： javac -encoding UTF-8  -d target src/main/java/cn/piao888/support/service/*.java    
  
   将源码编译为字节码，然后导出到 target目录下
  2. 然后进入 target目录 将target目录下的字节码执行下面的命令变为一个名为support-1.0.jar的jar包
  3. 打包：jar -cvf support-0.0.1-SNAPSHOT.jar cn
  
  此时在target目录下生成了 support-0.0.1-SNAPSHOT.jar  support-0.0.1-SNAPSHOT.jar 修改Java文件后 执行相同的命令可得到

  制作middleware1 （中间件1）与middleware2 （中间件2）的jar包   （中间件会依赖support包，所以编译时需要cp引入依赖）
  
  * 制作middleware1

   1. 首先执行编译 参数 cp 指向项目依赖的support的jar包所在位置
   2. 编译：javac -encoding UTF-8 -cp ../support/target/support-0.0.1-SNAPSHOT.jar  -d target src/main/java/cn/piao888/middleware1/*.java src/main/java/cn/piao888/middleware1/classLoader/*.java
    通过上个命令源码会出现在middleware的target目录下
    
   3.  然后进入 target目录 将target目录下的字节码执行下面的命令变为一个名为middleware-1.0.jar的jar包
   4.  打包:jar -cvf middleware-1.0.jar cn
   5.  此时在target目录下生成了middleware-1.0.jar
    
   6.  添加Main-Class: Main-Class: cn.piao888.middleware1.Middleware1Application
   7.  添加resources 下的配置文件application.properties到jar包根目录
   8.  运行这个jar包
   java -jar middleware-1.0.jar
  
  * 制作middleware2
   1. 编译：javac -encoding UTF-8 -cp ../support/target/support-0.0.2-SNAPSHOT.jar  -d target src/main/java/cn/piao888/middleware2/*.java src/main/java/cn/piao888/middleware2/classLoader/*.java
   2. 进入target目录：cd target
   3. 打包： jar -cvf middleware-2.0.jar cn
   4. 添加Main-Class: Main-Class: cn.piao888.middleware2.Middleware2Application
   5. 添加resources 下的配置文件application.properties到jar包根目录
   6. 执行: java -jar middleware-2.0.jar

  ## 双亲委派机制
  middleware1 与 middleware2 分别依赖于support-0.0.1-SNAPSHOT 与 support-0.0.1-SNAPSHOT
  support 0.0.1 与 support 0.0.2 区别在于PersonImpl 类是否存在singe 方法
 
  * 第一步：
    分别将support 0.0.1 与 support 0.0.2 打包 安装  
  * 第二步：
    分别将 middleware1 与 middleware2 打包 安装 
  * 第三步：
    1. 执行命令 将platform 打包
     javac -encoding UTF-8 -cp ../middleware1/target/middleware-1.0.jar;../middleware2/target/middleware-2.0.jar   -d target  src/main/java/cn/piao888/platform/PlatformApplication.java
    2. cd target
    3. jar -cvf platform.jar cn
    4. 添加Main-Class: Main-Class: cn.piao888.platform.PlatformApplication
    5. 添加resources 下的配置文件application.properties到jar包根目录
    6. 执行: java -cp platform.jar;../../middleware1/target/middleware-1.0.jar;../../middleware2/target/middleware-2.0.jar cn.piao888.platform.PlatformApplication
    ### 控制台将打印：
      - - - - - - -     - - - - - - -     - - - - - - -      - - - - - - -     - - - - - - -     - - - - - - -      - - - - - - -     - - - - - - -     - - - - - - -      - - - - - - -     - - - - - - -  
  
    *  sun.misc.Launcher$AppClassLoader@4e25154f
    *  [我是运行平台, 我是中间件1, 我是中间件2]
    *  cn.piao888.middleware1.classLoader.CustomClassLoader@12a3a380
    *  [我是服务支持者，我只通过自定义的类加载器CustomClassLoader加载，所以只有中间件1 与中间件2 的加载器可以加载到我]
    *  我是说界巨星
    *  [我是运行平台, 我是中间件1, 我是中间件2]
    *  cn.piao888.middleware2.classLoader.CustomClassLoader@7440e464
    *  [我是服务支持者，我只通过自定义的类加载器CustomClassLoader加载，所以只有中间件1 与中间件2 的加载器可以加载到我]
    *  我是说界巨星
    *  我是唱界巨星
    *  [我是运行平台, 我是中间件1, 我是中间件2]
       - - - - - - -     - - - - - - -     - - - - - - -      - - - - - - -     - - - - - - -     - - - - - - -      - - - - - - -     - - - - - - -     - - - - - - -      - - - - - - -     - - - - - - -  
 
   ## 总结
  之所以jvm可以同时加载两个相同的cn.piao888.support.service.PersonImpl (不同版本)
  是因为 middleware1 与 middleware2 同时拥有平级的类加载器 然后他们分别将两个不同版本的PersonImpl装载进了jvm中
  
  关于配置文件的读取，也是哪个类加载器加载才可以由哪个类加载器读取配置文件