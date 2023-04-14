package com.steafan.cangzhu.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.steafan.cangzhu.api.controller.request.DeleteDTO;
import com.steafan.cangzhu.api.controller.request.QueryByIdDTO;
import com.steafan.cangzhu.api.controller.request.QueryPageDTO;
import com.steafan.cangzhu.api.service.DeviceService;
import com.steafan.cangzhu.api.controller.request.device.AddDeviceDTO;
import com.steafan.cangzhu.api.controller.request.device.UpdateDeviceDTO;
import com.steafan.cangzhu.api.controller.response.BaseResponse;
import com.steafan.cangzhu.api.controller.response.device.DeviceResponse;
import com.steafan.cangzhu.api.repository.entity.DeviceDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(("/device"))
@Validated
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping("/add")
    public BaseResponse<Void> add(@Validated @RequestBody AddDeviceDTO deviceDTO) {
        deviceService.add(deviceDTO);
        return BaseResponse.success();
    }

    @PostMapping("/update")
    public BaseResponse<Void> update(@Validated @RequestBody UpdateDeviceDTO updateDeviceDTO) {
        deviceService.update(updateDeviceDTO);
        return BaseResponse.success();
    }

    @PostMapping("/delete")
    public BaseResponse<Void> delete(@Validated @RequestBody DeleteDTO deleteDTO) {
        deviceService.delete(deleteDTO);
        return BaseResponse.success();
    }

    @PostMapping("/query")
    public BaseResponse<DeviceResponse> query(@Validated @RequestBody QueryByIdDTO queryByIdDTO) {
        return BaseResponse.success("", deviceService.query(queryByIdDTO));
    }

    @PostMapping("/query/list")
    public BaseResponse<Page<Map<String, Object>>> queryList(@RequestBody QueryPageDTO<DeviceDAO> queryPageDTO) {
        return BaseResponse.success("", deviceService.queryList(queryPageDTO));
    }


}
