package threads;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 2020/6/29 17:54
 */
public class ThreadPoolExecutorByBuilder {


    public static void main(String[] args) {

    }

    private static ThreadFactory newThreadFactory() {
        ThreadFactory tf = new ThreadFactoryBuilder()
                                .setNameFormat("")
                                .setUncaughtExceptionHandler((thread, throwable)-> System.out.println("ThreadPool {} got exception" + thread + throwable))
                                .build();
        return tf;
    }

}
