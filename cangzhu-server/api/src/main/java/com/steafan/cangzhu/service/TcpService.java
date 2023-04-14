package com.steafan.cangzhu.service;

import com.steafan.cangzhu.controller.request.tcp.TcpDTO;
import com.steafan.cangzhu.netty.NettyClientHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TcpService {

    @Resource
    private NettyClientHandler nettyClientHandler;

    public String send(TcpDTO tcpDTO) {
        nettyClientHandler.sendMessage(nettyClientHandler.getCtxMap().get(tcpDTO.getAddress()), tcpDTO.getMsg());
        return "";
    }
}
