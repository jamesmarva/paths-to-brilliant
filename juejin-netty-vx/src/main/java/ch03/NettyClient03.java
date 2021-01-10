package ch03;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-11-07 09:15
 **/
public class NettyClient03 {

    public static void main(String[] args) {

        //指定线程模型
        NioEventLoopGroup worderGroup = new NioEventLoopGroup();

        //创建一个引导类
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                //指定线程模型
                .group(worderGroup)

                //指定 IO 类型为 NIO, 指定IO 模型
                .channel(NioSocketChannel.class)

                // IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });
        bootstrap.connect("127.0.0.1", 8000).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("connect successfully.");
            } else {
                System.out.println("connect fail.");
            }
        });
    }

    private static void connect(Bootstrap bootstrap, String host, int port) {
        bootstrap.connect(host, port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("connect successfully.");
                    } else {
                        System.out.println("connect failing.");
                        connect(bootstrap, host,port + 1);
                    }
                });
    }
}
