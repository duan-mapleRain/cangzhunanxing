package com.steafan.cangzhu.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.steafan.cangzhu.controller.request.device.AddDeviceDTO;
import com.steafan.cangzhu.controller.request.device.UpdateDeviceDTO;
import com.steafan.cangzhu.controller.response.device.DeviceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("p_device")
public class DeviceDAO {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private int type;

    private String name;

    private String address;

    private int uuid;

    private boolean needBindPerson;

    private int personId;

    private String register_time;

    private String last_time;


    public static DeviceDAO DTO2DAO(AddDeviceDTO deviceDTO) {
        DeviceDAO deviceDAO = new DeviceDAO();
        copyProperties(deviceDTO, deviceDAO);
        return deviceDAO;
    }

    public static DeviceDAO DTO2DAO(UpdateDeviceDTO updateDeviceDTO) {
        DeviceDAO deviceDAO = new DeviceDAO();
        copyProperties(updateDeviceDTO, deviceDAO);
        return deviceDAO;
    }

    public static DeviceResponse DAO2RES(DeviceDAO deviceDAO) {
        DeviceResponse deviceResponse = new DeviceResponse();
        copyProperties(deviceDAO, deviceResponse);
        return deviceResponse;
    }


    public static List<DeviceResponse> DAO2RES(List<DeviceDAO> deviceDAOList) {
        List<DeviceResponse> list = new ArrayList<>();
        DeviceResponse deviceResponse = new DeviceResponse();
        for (DeviceDAO deviceDAO : deviceDAOList) {
            copyProperties(deviceDAO, deviceResponse);
            list.add(deviceResponse);
        }
        return list;
    }
}
