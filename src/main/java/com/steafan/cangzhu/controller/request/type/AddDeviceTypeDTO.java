package com.steafan.cangzhu.controller.request.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class AddDeviceTypeDTO {
    private String name;
    private String info;
}
