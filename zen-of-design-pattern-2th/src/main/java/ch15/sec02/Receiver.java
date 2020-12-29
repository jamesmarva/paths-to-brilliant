package ch15.sec02;

/**
 * 代码清单15-13 通用Receiver类
 *
 * 很奇怪，为什么Receiver是一个抽象类？那是因为接收者可以有多个，
 * 有多个就需要定义一个所有特性的抽象集合——抽象的接收者，
 * 其具体的接收者如代码清单15-14所示
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-11 01:39
 **/
public abstract class Receiver {

    /**
     *
     */
    public abstract void doSomething();
}
