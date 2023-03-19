package com.steafan.cangzhu.controller.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

/**
 * @author AnselYuki
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @Email(message = "邮箱格式错误")
    private String email;
    @Length(min = 8, max = 32, message = "密码长度必须在8-32位之间")
    private String password;
}
