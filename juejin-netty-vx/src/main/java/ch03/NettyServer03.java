package ch03;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-11-07 08:33
 **/
public class NettyServer03 {

    public static void main(String[] args) {
        // 指定线程模型
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                });
//        serverBootstrap.bind(8000);
//        serverBootstrap.bind(8000)
//                .addListener(new GenericFutureListener<Future<? super Void>>() {
//                    @Override
//                    public void operationComplete(Future<? super Void> future) throws Exception {
//                        if (future.isSuccess()) {
//                            System.out.println("bind port success.");
//                        } else {
//                            System.out.println("bind port failing.s");
//                        }
//                    }
//                });


//        serverBootstrap.b
        bind(serverBootstrap, 8000);
    }

    private  static  void bind(ServerBootstrap bootstrap, int port) {
        bootstrap.bind(port)
                .addListener(future -> {
                   if (future.isSuccess()) {
                       System.out.println("bind port success.");
                   } else {
                       System.out.println("bind port failing.");
                       bind(bootstrap, port + 1);
                   }
                });
    }
}
