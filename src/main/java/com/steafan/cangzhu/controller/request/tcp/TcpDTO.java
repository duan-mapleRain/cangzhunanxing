package com.steafan.cangzhu.controller.request.tcp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TcpDTO {
    @NotNull
    private String address;
    @NotNull
    private String msg;
}
