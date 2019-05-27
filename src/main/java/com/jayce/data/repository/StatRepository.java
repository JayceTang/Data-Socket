package com.jayce.data.repository;

import com.jayce.data.entity.SignalStatData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatRepository extends JpaRepository<SignalStatData, String> {
}
