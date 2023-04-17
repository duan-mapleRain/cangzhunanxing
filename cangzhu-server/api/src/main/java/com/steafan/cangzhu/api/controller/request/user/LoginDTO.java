package com.steafan.cangzhu.api.controller.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * @author AnselYuki
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String account;
    @Size(min = 32, max = 32, message = "32位")
    private String password;

}
