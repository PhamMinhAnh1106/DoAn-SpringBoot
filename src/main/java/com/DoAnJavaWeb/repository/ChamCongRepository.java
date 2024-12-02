package com.DoAnJavaWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DoAnJavaWeb.models.ChamCong;

@Repository
public interface ChamCongRepository extends JpaRepository<ChamCong, Integer>{

}
