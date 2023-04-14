package com.steafan.cangzhu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.steafan.cangzhu.controller.request.DeleteDTO;
import com.steafan.cangzhu.controller.request.QueryByIdDTO;
import com.steafan.cangzhu.controller.request.QueryPageDTO;
import com.steafan.cangzhu.controller.request.device.AddDeviceDTO;
import com.steafan.cangzhu.controller.request.device.UpdateDeviceDTO;
import com.steafan.cangzhu.controller.response.CZResultException;
import com.steafan.cangzhu.controller.response.device.DeviceResponse;
import com.steafan.cangzhu.enums.CZHttpStatus;
import com.steafan.cangzhu.mapper.DeviceMapper;
import com.steafan.cangzhu.repository.entity.DeviceDAO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Setter
@Slf4j
public class DeviceService {

    @Resource
    DeviceMapper deviceMapper;

    public void add(@NotNull AddDeviceDTO deviceDTO) {
        QueryWrapper<DeviceDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", deviceDTO.getUuid());
        if (deviceMapper.exists(queryWrapper)) {
            throw new CZResultException(CZHttpStatus.DEVICE_EXIST);
        }
        int insert = deviceMapper.insert(DeviceDAO.DTO2DAO(deviceDTO));
        if (insert < 1) {
            throw new CZResultException(CZHttpStatus.DEVICE_INFO_UPDATE_FAILED);
        }
    }

    public void update(@NotNull UpdateDeviceDTO updateDeviceDTO) {
        QueryWrapper<DeviceDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", updateDeviceDTO.getId());
        if (!deviceMapper.exists(queryWrapper)) {
            throw new CZResultException(CZHttpStatus.DEVICE_NO_EXIST);
        }
        int insert = deviceMapper.updateById(DeviceDAO.DTO2DAO(updateDeviceDTO));
        if (insert < 1) {
            throw new CZResultException(CZHttpStatus.DEVICE_INFO_UPDATE_FAILED);
        }
    }

    public void delete(@NotNull DeleteDTO deleteDTO) {
        int delete = deviceMapper.deleteBatchIds(deleteDTO.getIds());
        if (delete < 1) {
            throw new CZResultException(CZHttpStatus.DEVICE_INFO_UPDATE_FAILED);
        }
    }


    public DeviceResponse query(@NotNull QueryByIdDTO queryByIdDTO) {
        DeviceDAO deviceDAO = deviceMapper.selectById(queryByIdDTO.getId());
        return DeviceDAO.DAO2RES(deviceDAO);
    }

    public Page<Map<String, Object>> queryList(QueryPageDTO<DeviceDAO> queryPageDTO) {
        Page<Map<String, Object>> queryDevicePageResult = deviceMapper.selectMapsPage(new Page<>(queryPageDTO.getCurrent(), queryPageDTO.getSize()),
                queryPageDTO.getQueryWrapper());

        return queryDevicePageResult;
    }
}
