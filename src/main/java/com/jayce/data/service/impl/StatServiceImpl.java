package com.jayce.data.service.impl;

import com.jayce.data.dto.SignalStatDataDto;
import com.jayce.data.entity.SignalStatData;
import com.jayce.data.repository.StatRepository;
import com.jayce.data.service.StatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatRepository repository;

    @Override
    public List<SignalStatDataDto> findAll() {
        List<SignalStatData> list = repository.findAll();
        if (list != null && list.size() > 0) {
            List<SignalStatDataDto> dtoList = new LinkedList<>();
            for (SignalStatData ssd : list) {
                SignalStatDataDto dto = new SignalStatDataDto();
                BeanUtils.copyProperties(ssd, dto);
                String areaName = getAreaName(dto.getAreaId());
                dto.setAreaName(areaName);
                dtoList.add(dto);
            }
            return dtoList;
        }
        return Collections.emptyList();
    }

    private String getAreaName(String areaId) {
        String areaName = null;
        switch(areaId) {
            case "020":
                areaName = "广州";
                break;
            case "0755":
                areaName = "深圳";
                break;
            case "0756":
                areaName = "珠海";
                break;
            case "0754":
                areaName = "汕头";
                break;
            case "0757":
                areaName = "佛山";
                break;
            case "0751":
                areaName = "韶关";
                break;
            case "0759":
                areaName = "湛江";
                break;
            case "0758":
                areaName = "肇庆";
                break;
            case "0750":
                areaName = "江门";
                break;
            case "0668":
                areaName = "茂名";
                break;
            case "0752":
                areaName = "惠州";
                break;
            case "0753":
                areaName = "梅州";
                break;
            case "0660":
                areaName = "汕尾";
                break;
            case "0762":
                areaName = "河源";
                break;
            case "0662":
                areaName = "阳江";
                break;
            case "0763":
                areaName = "清远";
                break;
            case "0769":
                areaName = "东莞";
                break;
            case "0760":
                areaName = "中山";
                break;
            case "0768":
                areaName = "潮州";
                break;
            case "0663":
                areaName = "揭阳";
                break;
            case "0766":
                areaName = "云浮";
                break;
            default:
                areaName = "未知区域";
                break;
        }
        return areaName;
    }

}
