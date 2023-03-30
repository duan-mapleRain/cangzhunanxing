package com.steafan.cangzhu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.steafan.cangzhu.controller.request.QueryByIdDTO;
import com.steafan.cangzhu.controller.request.DeleteDTO;
import com.steafan.cangzhu.controller.request.device.*;
import com.steafan.cangzhu.controller.response.CZResultException;
import com.steafan.cangzhu.controller.response.device.DeviceResponse;
import com.steafan.cangzhu.enums.HttpStatus;
import com.steafan.cangzhu.mapper.DeviceMapper;
import com.steafan.cangzhu.repository.entity.DeviceDAO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Setter
@Slf4j
public class DeviceService {

    @Resource
    DeviceMapper deviceMapper;

    public void add(AddDeviceDTO deviceDTO) {
        QueryWrapper<DeviceDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", deviceDTO.getUuid());
        if (deviceMapper.exists(queryWrapper)) {
            throw new CZResultException(HttpStatus.DEVICE_EXIST);
        }
        int insert = deviceMapper.insert(DeviceDAO.DTO2DAO(deviceDTO));
        if (insert < 1) {
            throw new CZResultException(HttpStatus.DEVICE_INFO_UPDATE_FAILED);
        }
    }

    public void update(UpdateDeviceDTO updateDeviceDTO) {
        QueryWrapper<DeviceDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", updateDeviceDTO.getId());
        if (!deviceMapper.exists(queryWrapper)) {
            throw new CZResultException(HttpStatus.DEVICE_NO_EXIST);
        }
        int insert = deviceMapper.updateById(DeviceDAO.DTO2DAO(updateDeviceDTO));
        if (insert < 1) {
            throw new CZResultException(HttpStatus.DEVICE_INFO_UPDATE_FAILED);
        }
    }

    public void delete(DeleteDTO deleteDTO) {
        int delete = deviceMapper.deleteBatchIds(deleteDTO.getIds());
        if (delete < 1) {
            throw new CZResultException(HttpStatus.DEVICE_INFO_UPDATE_FAILED);
        }
    }


    public List<DeviceResponse> query(QueryByIdDTO queryByIdDTO) {
        Map<String, Object> columnMap = new HashMap<>();
        List<DeviceDAO> deviceDAOS = deviceMapper.selectByMap(columnMap);
        return DeviceDAO.DAO2RES(deviceDAOS);
    }

    public List<DeviceResponse> queryList(QueryDeviceListDTO deviceDTO) {
        Map<String, Object> columnMap = new HashMap<>();
        List<DeviceDAO> deviceDAOS = deviceMapper.selectByMap(columnMap);
        return DeviceDAO.DAO2RES(deviceDAOS);
    }
}
