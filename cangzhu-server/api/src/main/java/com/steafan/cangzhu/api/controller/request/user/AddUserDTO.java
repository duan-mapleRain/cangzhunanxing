package com.steafan.cangzhu.api.controller.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 32, max = 32, message = "加密后需要为32位")
    private String password;
    @NotNull
    private String account;
    @NotNull
    private int status;
}
