package com.DoAnJavaWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DoAnJavaWeb.models.NghiPhep;

@Repository
public interface NghiPhepRepository extends JpaRepository<NghiPhep, Integer>{

}
