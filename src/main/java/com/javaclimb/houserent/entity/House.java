package com.javaclimb.houserent.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.javaclimb.houserent.common.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_house")
public class House extends BaseEntity {

    private Long userId;
    private String rentType;
    private String title;
    private String content;
    private String city;
    private String address;
    private String thumbnailUrl;
    private String slideUrl;
    private Integer monthRent;
    private Integer status;
    private String cetificateNo;
    private Integer toiletNum;
    private Integer kitchenNum;
    private Integer livingRoomNum;
    private Integer bedroomNum;
    private String hasAirConditioner;
    private Double area;
    private Integer floor;
    private Integer maxFloor;
    private Integer hasElevator;
    private Integer buildYear;
    private String direction;
    private Date lastOrderStartTime;
    private String longitudeLatitude;
    private String contactName;
    private String contactPhone;
}
