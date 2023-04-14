package com.steafan.cangzhu.api.controller.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswdDTO {
    @Length(min = 6, max = 32, message = "密码长度必须在8-32位之间")
    private String oldPassword;
    @Length(min = 6, max = 32, message = "密码长度必须在8-32位之间")
    private String newPassword;
}
