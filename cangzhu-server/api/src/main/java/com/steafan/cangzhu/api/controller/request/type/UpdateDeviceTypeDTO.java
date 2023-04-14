package com.steafan.cangzhu.api.controller.request.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDeviceTypeDTO {
    private String name;
    private String info;
    private int id;
}
