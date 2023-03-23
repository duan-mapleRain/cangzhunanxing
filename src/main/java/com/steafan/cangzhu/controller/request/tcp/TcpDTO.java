package com.steafan.cangzhu.controller.request.tcp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TcpDTO {
    private String address;
    private String msg;
}
