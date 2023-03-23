package com.steafan.cangzhu.netty;

import com.steafan.cangzhu.repository.RedisCache;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.Map;

@Component
@Getter
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@AllArgsConstructor
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private Map<String, ChannelHandlerContext> ctxMap;

    @Resource
    RedisCache redisCache;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当连接建立时，发送一条消息到服务器
        ctxMap.put(getInfo(ctx), ctx);
        System.out.println(ctx);
        String message = "Hello, Netty Server!";
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(message.getBytes());
        ctx.writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 当接收到服务器的响应消息时，打印响应内容

        ByteBuf buffer = (ByteBuf) msg;
        String response = buffer.toString(CharsetUtil.UTF_8);
        // 创建响应消息
        ByteBuf newResponse = Unpooled.copiedBuffer("Hello, client!".getBytes());
        // 发送响应消息
        ctx.writeAndFlush(newResponse);
    }

    public void sendMessage(ChannelHandlerContext ctx, String message) {
        // 向客户端发送消息
        ctx.writeAndFlush(Unpooled.copiedBuffer(message, CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当发生异常时，关闭连接
        System.err.println("Netty client encountered an exception: ");
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String info = getInfo(ctx);
        if (ctxMap.get(getInfo(ctx)) != null) {
            //todo 删除redis中的数据
            ctxMap.remove(info);
            System.out.println("从hashmap中删除了" + info);
        }
    }

    private String getInfo(ChannelHandlerContext ctx) {
        return ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress() + ":" + ((InetSocketAddress) ctx.channel().remoteAddress()).getPort();
    }
}
