package com.jayce.data.service;

import com.jayce.data.dto.SignalStatDataDto;

import java.util.List;
import java.util.Map;

public interface StatServiceI {
    List<SignalStatDataDto> findAll();
}
