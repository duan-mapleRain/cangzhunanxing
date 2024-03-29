package com.steafan.cangzhu.api.controller.response.device;

import lombok.Data;

@Data
public class DeviceResponse {
    private Integer id;
    private int type;

    private String name;

    private String address;

    private int uuid;

    private boolean needBindPerson;

    private int personId;

    private String register_time;

    private String last_time;

}
