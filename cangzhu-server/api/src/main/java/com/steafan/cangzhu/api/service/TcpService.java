package com.steafan.cangzhu.api.service;

import com.steafan.cangzhu.api.netty.NettyClientHandler;
import com.steafan.cangzhu.api.controller.request.tcp.TcpDTO;
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
