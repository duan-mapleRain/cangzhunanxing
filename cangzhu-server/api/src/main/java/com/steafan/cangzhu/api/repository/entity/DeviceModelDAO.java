package com.steafan.cangzhu.api.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("p_device_model")
public class DeviceModelDAO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String info;
    private String rule;
    private String register_time;
    private String update_time;

}
