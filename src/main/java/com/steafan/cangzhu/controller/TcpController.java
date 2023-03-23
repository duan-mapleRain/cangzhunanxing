package com.steafan.cangzhu.controller;

import com.steafan.cangzhu.controller.request.tcp.TcpDTO;
import com.steafan.cangzhu.controller.response.BaseResponse;
import com.steafan.cangzhu.service.TcpService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tcp")
@RequiredArgsConstructor
@Validated

public class TcpController {
    private final TcpService tcpService;

    @GetMapping("/send")
    public BaseResponse<Integer> send(@RequestBody TcpDTO tcpDTO) {
        return tcpService.send(tcpDTO);
    }



}
