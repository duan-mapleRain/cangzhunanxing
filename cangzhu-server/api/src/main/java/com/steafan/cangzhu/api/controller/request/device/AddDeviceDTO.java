package com.steafan.cangzhu.api.controller.request.device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class AddDeviceDTO {
    @NotNull
    private int type;

    @Length(max = 20)
    private String name;
    @Length(max = 20)
    private String address;
    @NotNull
    private int uuid;
    @NotNull
    private int deviceModelId;
}
