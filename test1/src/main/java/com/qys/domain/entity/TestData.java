package com.qys.domain.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (TestData)表实体类
 *
 * @author makejava
 * @since 2022-11-24 13:52:05
 */
@SuppressWarnings("serial")
//@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("test1_data")
@ApiModel(value = "testData测试对象", description = "测试对象")
public class TestData {
    //主键
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键ID")
    private Long id;
    //车辆识别号
    @ApiModelProperty("车辆识别号")
    private String vin;
    //城市
    @ApiModelProperty("城市")
    private String city;
    //州
    @ApiModelProperty("州")
    private String state;
    //制造商
    @ApiModelProperty("制造商")
    private String make;
    //型号
    @ApiModelProperty("型号")
    private String model;
    //电动车类型
    @ApiModelProperty("电动车类型")
    private String electricVehicleType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make.toUpperCase();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model.toUpperCase();
    }

    public String getElectricVehicleType() {
        return electricVehicleType;
    }

    public void setElectricVehicleType(String electricVehicleType) {
        this.electricVehicleType = electricVehicleType;
    }
}

