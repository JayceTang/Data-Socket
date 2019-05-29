package com.jayce.data.service;

import com.jayce.data.dto.SignalStatDataDto;

import java.util.List;
import java.util.Map;

public interface StatService {
    List<SignalStatDataDto> findAll();
}
