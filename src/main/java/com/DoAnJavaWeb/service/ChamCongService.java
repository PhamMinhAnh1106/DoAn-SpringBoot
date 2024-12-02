package com.DoAnJavaWeb.service;

import java.util.List;

import com.DoAnJavaWeb.models.ChamCong;

public interface ChamCongService {
	List<ChamCong> getAll();
	Boolean create(ChamCong chamCong);
	ChamCong findById(Integer id);
	Boolean update(ChamCong chamCong);
	Boolean delete(Integer id);
}
