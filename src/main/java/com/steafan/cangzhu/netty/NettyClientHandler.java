package com.steafan.cangzhu.netty;

import com.steafan.cangzhu.repository.RedisCache;
import com.steafan.cangzhu.utils.TcpInfoDetail;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Map;

@Component
@Getter
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@AllArgsConstructor
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

    private Map<String, ChannelHandlerContext> ctxMap;

    private final RedisCache redisCache;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctxMap.put(getContextName(ctx), ctx);
        System.out.println(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 当接收到服务器的响应消息时，打印响应内容
        ByteBuf buffer = (ByteBuf) msg;
        String request = buffer.toString(CharsetUtil.UTF_8);
        // 创建响应消息
        String response = TcpInfoDetail.parseData(request);
        ByteBuf newResponse = Unpooled.copiedBuffer(response.getBytes());
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
        logger.debug("tcp服务器异常");
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String info = getContextName(ctx);
        if (ctxMap.get(getContextName(ctx)) != null) {
            ctxMap.remove(info);
            System.out.println("从hashmap和redis中删除了" + info);
        }
    }

    private String getContextName(ChannelHandlerContext ctx) {
        return ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress() + ":" + ((InetSocketAddress) ctx.channel().remoteAddress()).getPort();
    }
}
