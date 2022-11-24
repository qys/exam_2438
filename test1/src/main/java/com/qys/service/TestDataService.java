package com.qys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qys.domain.entity.TestData;
import com.qys.utils.ResponseResult;

/**
 * (TestData)表服务接口
 *
 * @author makejava
 * @since 2022-11-24 13:52:07
 */
public interface TestDataService extends IService<TestData> {

    ResponseResult getEvpdStat(String make, String model);
}

