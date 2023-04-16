package com.steafan.cangzhu.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BaseListResponse<T> {
    //总数
    private long total;
    //每页显示条数，默认 10
    private long size;
    //当前页
    private long current = 1;

    private List<T> list;
}
