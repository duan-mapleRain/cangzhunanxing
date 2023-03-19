package com.steafan.cangzhu.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.steafan.cangzhu.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * @author AnselYuki
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo {
    private String id;
    private String userName;
    private boolean activated;
    private long uploadCount;

    public UserInfo(User save) {
        BeanUtils.copyProperties(save, this);
    }
}
