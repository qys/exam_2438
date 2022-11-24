package com.qys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qys.domain.entity.TestData;
import com.qys.domain.vo.EvpdStatVo;
import com.qys.mapper.TestDataMapper;
import com.qys.service.TestDataService;
import com.qys.utils.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * (TestData)表服务实现类
 *
 * @author makejava
 * @since 2022-11-24 13:52:07
 */
@Service("testDataService")
public class TestDataServiceImpl extends ServiceImpl<TestDataMapper, TestData> implements TestDataService {

    @Override
    public ResponseResult getEvpdStat(String make, String model) {
        LambdaQueryWrapper<TestData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TestData::getMake, make);
        wrapper.eq(TestData::getModel, model);

        List<TestData> testDataList = list(wrapper);
        int total = testDataList.size();    //该车型总数
        String electricVehicleType = testDataList.get(1).getElectricVehicleType();  //电动车类型

        EvpdStatVo evpdStatVo = new EvpdStatVo();
        evpdStatVo.setMake(make);
        evpdStatVo.setModel(model);
        evpdStatVo.setTotal(total);
        evpdStatVo.setInternalShare((double) total / getTotalByMake(make));
        evpdStatVo.setMarketShare((double) total / count());
        evpdStatVo.setEvTypeShare((double) total / getTotalByElectricVehicleType(electricVehicleType));
        List<Map<String, Object>> stateTop5 = baseMapper.getstateTop5GroupByState(model);
        evpdStatVo.setStateTop5(stateTop5);

        return ResponseResult.okResult(evpdStatVo);
    }

    // 获取全部车辆数量
    private Integer getPopTotal() {
        return count();
    }

    //根据电动车类型获取总数
    private Integer getTotalByElectricVehicleType(String electricVehicleType) {
        LambdaQueryWrapper<TestData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TestData::getElectricVehicleType, electricVehicleType);
        return count(wrapper);
    }

    //获取本厂商车辆总数
    private Integer getTotalByMake(String make) {
        LambdaQueryWrapper<TestData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TestData::getMake, make);
        return count(wrapper);
    }

}

