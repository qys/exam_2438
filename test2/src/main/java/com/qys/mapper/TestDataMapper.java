package com.qys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qys.domain.entity.TestData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * (TestData)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-24 13:52:08
 */
@Mapper
public interface TestDataMapper extends BaseMapper<TestData> {

    // 获取 该车型数量最多的5个州,按数量排序
    List<Map<String, Object>> getstateTop5GroupByState(String model);

}
