package cn.piao888.support.service;

import java.io.IOException;
import java.util.List;

/**
 * @Author： hongzhi.xu
 * @Date: 2021/5/21 上午6:48
 * @Version 1.0
 */
public interface Person {
    //说
    void say();

    //唱
//    void singe();

    void getProperties() throws IOException;
}
