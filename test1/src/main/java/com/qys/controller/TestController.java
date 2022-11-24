package com.qys.controller;

import com.qys.domain.dto.EvpdDto;
import com.qys.domain.entity.TestData;
import com.qys.domain.vo.EvpdStatVo;
import com.qys.enums.AppHttpCodeEnum;
import com.qys.execption.SystemException;
import com.qys.service.TestDataService;
import com.qys.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qys
 * @description
 * @create 2022-11-24-13:50
 */
@RestController
@RequestMapping("api")
@Api(description = "测试接口")
public class TestController {

    @Autowired
    private TestDataService testDataService;

    @ApiOperation("根据厂商和型号获取相关统计数据")
    @GetMapping("/evpd/stat")
    public ResponseResult getEvpdStat(
            @ApiParam(required = true, name = "make", value = "厂商") @RequestParam String make,
            @ApiParam(required = true, name = "model", value = "型号") @RequestParam String model
    ) {
        return testDataService.getEvpdStat(make, model);
    }

    @ApiOperation("新增电动汽车注册信息")
    @PostMapping("/evpd")
    public ResponseResult addEvpd(@Validated @RequestBody EvpdDto evpdDto) {
        if (!"test3".equals(evpdDto.getClientId()) || !"1234567890ABCDE".equals(evpdDto.getClientKey())) {
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        }

        byte[] sha256 = DigestUtils.sha256(evpdDto.getClientId() + evpdDto.getData().getCity() + evpdDto.getData().getElectricVehicleType()
                + evpdDto.getData().getMake() + evpdDto.getData().getState() + evpdDto.getData().getVin() + evpdDto.getClientKey());
        String signStr = new String(sha256);
        if (!evpdDto.getSign().equals(signStr)) {
            throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        }

        TestData testData = evpdDto.getData();
        testDataService.save(testData);
        Map<String, Object> result = new HashMap<>();
        result.put("id", testData.getId());
        return ResponseResult.okResult(result);
    }
}
