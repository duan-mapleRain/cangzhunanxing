package com.steafan.cangzhu.controller.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author AnselYuki
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDTO {
    @NotNull
    private String username;
    @Length(min = 6, max = 16, message = "密码长度必须在6-16位之间")
    private String password;
    @NotNull
    private String account;
}
