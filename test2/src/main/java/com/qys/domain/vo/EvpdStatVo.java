package com.qys.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qys.utils.Double2Serializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author qys
 * @description
 * @create 2022-11-24-14:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvpdStatVo {

    @ApiModelProperty("制造商")
    private String make;
    //型号
    @ApiModelProperty("型号")
    private String model;
    //该车型总量
    private Integer total;
    //该车型在本厂商内的占有比率 (0~1之间)
    @JsonSerialize(using = Double2Serializer.class)
    private double internalShare;
    //该车型总体占有比率 (0~1之间)
    @JsonSerialize(using = Double2Serializer.class)
    private double marketShare;
    //该车型在所有同类型车辆中的市场占有比率 (0~1之间)
    @JsonSerialize(using = Double2Serializer.class)
    private double evTypeShare;
    //拥有该车型数量最多的5个州,按数量排序
    private List<Map<String, Object>> stateTop5;
}
