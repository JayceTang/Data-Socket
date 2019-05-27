package com.jayce.data.enums;

import lombok.Getter;

/**
 * 没必要，先留着吧
 */
@Getter
public enum AreaEnums {
    GUANGZHOU("020", "广州"),
    SHENZHEN("0755", "深圳 "),
    ZHUHAI("0756","珠海"),
    SHANTOU("0754","汕头"),
    FOSHAN("0757","佛山"),
    SHAOGUAN("0751","韶关"),
    ZHANJIANG("0759","湛江"),
    ZHAOQING("0758","肇庆");




    private String areaId;
    private String areaName;

    AreaEnums(String areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }
}
