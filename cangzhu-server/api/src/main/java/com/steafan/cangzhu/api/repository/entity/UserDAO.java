package com.steafan.cangzhu.api.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.steafan.cangzhu.api.controller.request.user.AddUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("p_user")
public class UserDAO {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String account;
    private String password;
    /**
     * -1 已删除
     * 0  刚注册
     * 1 正常使用
     * 2 管理员
     */
    private Integer status;

    public static UserDAO DTO2DAO(AddUserDTO addUserDTO){
        UserDAO userDAO = new UserDAO();
        copyProperties(addUserDTO, userDAO);
        return userDAO;
    }
}
