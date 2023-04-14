package com.steafan.cangzhu.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.steafan.cangzhu.controller.request.type.AddDeviceTypeDTO;
import com.steafan.cangzhu.controller.request.type.UpdateDeviceTypeDTO;
import com.steafan.cangzhu.controller.response.device.DeviceTypeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("p_device_type")
public class DeviceTypeDAO {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String info;
    private List<DeviceTypeDAO> deviceTypeList;
    private Timestamp register_time;
    private Timestamp update_time;

    public static DeviceTypeDAO DTO2DAO(AddDeviceTypeDTO addDeviceTypeDTO) {
        DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAO();
        copyProperties(addDeviceTypeDTO, deviceTypeDAO);
        deviceTypeDAO.setRegister_time(Timestamp.valueOf(LocalDateTime.now()));
        deviceTypeDAO.setUpdate_time(Timestamp.valueOf(LocalDateTime.now()));
        return deviceTypeDAO;
    }

    public static DeviceTypeDAO DTO2DAO(UpdateDeviceTypeDTO updateDeviceTypeDTO) {
        DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAO();
        copyProperties(updateDeviceTypeDTO, deviceTypeDAO);
        deviceTypeDAO.setUpdate_time(Timestamp.valueOf(LocalDateTime.now()));
        return deviceTypeDAO;
    }

    public static DeviceTypeResponse DAO2RES(DeviceTypeDAO deviceTypeDAO) {
        DeviceTypeResponse deviceTypeResponse = new DeviceTypeResponse();
        copyProperties(deviceTypeDAO, deviceTypeResponse);
        return deviceTypeResponse;
    }
}
