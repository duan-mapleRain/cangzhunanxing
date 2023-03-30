package com.steafan.cangzhu.controller;

import com.steafan.cangzhu.controller.request.DeleteDTO;
import com.steafan.cangzhu.controller.request.type.AddDeviceTypeDTO;
import com.steafan.cangzhu.controller.request.type.QueryDeviceTypeDTO;
import com.steafan.cangzhu.controller.request.type.UpdateDeviceTypeDTO;
import com.steafan.cangzhu.controller.response.BaseResponse;
import com.steafan.cangzhu.controller.response.device.DeviceTypeResponse;
import com.steafan.cangzhu.service.DeviceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(("/device/type"))
@Validated
@RequiredArgsConstructor
public class DeviceTypeController {
    @Resource
    DeviceTypeService deviceTypeService;

    @PostMapping("/add")
    public BaseResponse<Void> add(@Validated @RequestBody AddDeviceTypeDTO deviceDTO){
        deviceTypeService.add(deviceDTO);
        return BaseResponse.success();
    }

    @PostMapping("/update")
    public BaseResponse<Void> update(@Validated @RequestBody UpdateDeviceTypeDTO deviceDTO){
        deviceTypeService.update(deviceDTO);
        return BaseResponse.success();
    }

    @PostMapping("/delete")
    public BaseResponse<Void> delete(@Validated @RequestBody DeleteDTO deviceDTO){
        deviceTypeService.delete(deviceDTO);
        return BaseResponse.success();
    }

    @PostMapping("/query")
    public BaseResponse<DeviceTypeResponse> query(@Validated @RequestBody QueryDeviceTypeDTO deviceDTO){
        return BaseResponse.success(deviceTypeService.query(deviceDTO));
    }


}
