package com.steafan.cangzhu.controller.request.device;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class UpdateDeviceDTO {
    @NotNull
    private int id;
    private int type;

    @Length(max = 20)
    private String name;
    @Length(max = 20)
    private String address;

    private int uuid;
}
