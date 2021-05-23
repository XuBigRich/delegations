package cn.piao888.support.service;

/**
 * @Author： hongzhi.xu
 * @Date: 2021/5/21 上午6:49
 * @Version 1.0
 */
public class PersonImpl implements Person{
    {
        ClassLoader classLoader=this.getClass().getClassLoader();
        System.out.println(classLoader);
    }
    @Override
    public void say() {
        System.out.println("我是说界巨星");
    }

//    @Override
//    public void singe() {
//        System.out.println("我是唱界巨星");
//    }
}
