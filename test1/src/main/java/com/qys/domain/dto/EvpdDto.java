package com.qys.domain.dto;

import com.qys.domain.entity.TestData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author qys
 * @description
 * @create 2022-11-24-15:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "新增记录对象", description = "新增记录对象封装")
public class EvpdDto {

    @NotBlank(message = "clientId必填")
    @ApiModelProperty("clientId")
    private String clientId;

    @NotBlank(message = "clientKey必填")
    @ApiModelProperty("clientKey")
    private String clientKey;

    @ApiModelProperty("记录数据")
    private TestData data;

    @NotBlank(message = "sign必填")
    @ApiModelProperty("sign")
    private String sign;
}
