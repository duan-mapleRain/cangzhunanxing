package com.steafan.cangzhu.api.controller.request;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;

@Data
public class QueryPageDTO<T> {
    private long size;
    private long current;
    private QueryWrapper<T> queryWrapper;
}
