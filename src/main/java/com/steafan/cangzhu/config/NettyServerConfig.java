package com.steafan.cangzhu.config;

import com.steafan.cangzhu.netty.NettyClientHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

@SpringBootConfiguration
public class NettyServerConfig {

    @Value("${netty.server.host}")
    private String host;

    @Value("${netty.server.port}")
    private int port;

    @Bean(destroyMethod = "shutdownGracefully")
    public EventLoopGroup bossGroup() {
        return new NioEventLoopGroup(1);
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public EventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }

    @Resource
    private NettyClientHandler nettyClientHandler;

    @Bean
    public ServerBootstrap serverBootstrap() {
        return new ServerBootstrap().group(bossGroup(), workerGroup()).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(nettyClientHandler);
            }
        });
    }

    @Bean(destroyMethod = "close")
    public Channel serverChannelFuture() {
        InetSocketAddress address = new InetSocketAddress(host, port);
        ChannelFuture channelFuture = serverBootstrap().bind(address);
        channelFuture.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                System.out.println("111");
            } else {
                System.out.println("222");
            }
        });
        return channelFuture.channel();
    }

}