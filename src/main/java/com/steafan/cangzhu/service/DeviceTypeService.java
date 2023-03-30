package com.steafan.cangzhu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.steafan.cangzhu.controller.request.DeleteDTO;
import com.steafan.cangzhu.controller.request.type.AddDeviceTypeDTO;
import com.steafan.cangzhu.controller.request.type.QueryDeviceTypeDTO;
import com.steafan.cangzhu.controller.request.type.UpdateDeviceTypeDTO;
import com.steafan.cangzhu.controller.response.CZResultException;
import com.steafan.cangzhu.controller.response.device.DeviceTypeResponse;
import com.steafan.cangzhu.enums.HttpStatus;
import com.steafan.cangzhu.mapper.DeviceTypeMapper;
import com.steafan.cangzhu.repository.entity.DeviceTypeDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeviceTypeService {
    @Resource
    private DeviceTypeMapper deviceTypeMapper;

    public void add(AddDeviceTypeDTO addDeviceTypeDTO) {

        if (deviceTypeMapper.exists(new QueryWrapper<DeviceTypeDAO>().eq("name", addDeviceTypeDTO.getName()))) {
            throw new CZResultException(HttpStatus.DEVICE_TYPE_EXIST);
        }
        int insert = deviceTypeMapper.insert(DeviceTypeDAO.DTO2DAO(addDeviceTypeDTO));
        if (insert < 1) {
            throw new CZResultException(HttpStatus.DEVICE_TYPE_INFO_UPDATE_FAILED);
        }
    }

    public void update(UpdateDeviceTypeDTO updateDeviceTypeDTO) {
        //todo
        if (deviceTypeMapper.exists(new QueryWrapper<DeviceTypeDAO>().eq("name", updateDeviceTypeDTO.getName()))) {
            throw new CZResultException(HttpStatus.DEVICE_TYPE_EXIST);
        }

        int insert = deviceTypeMapper.updateById(DeviceTypeDAO.DTO2DAO(updateDeviceTypeDTO));
        if (insert < 1) {
            throw new CZResultException(HttpStatus.DEVICE_TYPE_INFO_UPDATE_FAILED);
        }
    }

    public void delete(DeleteDTO deleteDTO) {
        if (deviceTypeMapper.deleteBatchIds(deleteDTO.getIds()) < 1) {
            throw new CZResultException(HttpStatus.DEVICE_TYPE_INFO_UPDATE_FAILED);
        }
    }

    public DeviceTypeResponse query(QueryDeviceTypeDTO queryDeviceTypeDTO) {
        QueryWrapper<DeviceTypeDAO> queryWrapper=new QueryWrapper<>();

        DeviceTypeDAO deviceTypeDAO = deviceTypeMapper.selectOne(queryWrapper);
        return DeviceTypeDAO.DAO2RES(deviceTypeDAO);
    }
}
