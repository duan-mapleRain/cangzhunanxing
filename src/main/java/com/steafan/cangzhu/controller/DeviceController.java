package com.steafan.cangzhu.controller;

import com.steafan.cangzhu.controller.request.DeleteDTO;
import com.steafan.cangzhu.controller.request.QueryByIdDTO;
import com.steafan.cangzhu.controller.request.device.AddDeviceDTO;
import com.steafan.cangzhu.controller.request.device.UpdateDeviceDTO;
import com.steafan.cangzhu.controller.response.BaseResponse;
import com.steafan.cangzhu.controller.response.device.DeviceResponse;
import com.steafan.cangzhu.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public BaseResponse<List<DeviceResponse>> query(@Validated QueryByIdDTO queryByIdDTO) {
        return BaseResponse.success("", deviceService.query(queryByIdDTO));
    }

}
