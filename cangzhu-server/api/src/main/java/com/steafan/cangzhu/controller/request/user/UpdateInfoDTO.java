package com.steafan.cangzhu.controller.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInfoDTO {
    @Email(message = "邮箱格式错误")
    private String email;
    @Length(min = 4, max = 24, message = "用户名长度应在2-24位之间")
    private String nickName;
}
