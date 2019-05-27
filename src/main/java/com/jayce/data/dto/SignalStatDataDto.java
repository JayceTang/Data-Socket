package com.jayce.data.dto;

import lombok.Data;

import javax.persistence.Id;

@Data
public class SignalStatDataDto {
    /** 城市区号，例如：020 */
    @Id
    private String areaId;
    /** 城市名字 */
    private String areaName;

    /** 信令总数 */
    private long totalCount;

    /** 有效信令数量 */
    private long validCount;

    /** 本市号码数量 */
    private int localCityCount;

    /** 本省漫入号码数量 */
    private int localProvinceCount;

    /** 外省漫入号码数量 */
    private int otherProvinceCount;
}
