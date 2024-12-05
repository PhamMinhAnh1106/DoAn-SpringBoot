package com.DoAnJavaWeb.service;

import java.util.List;

import com.DoAnJavaWeb.models.Luong;

public interface LuongService {
	    List<Luong> getAll();
	    Boolean create(Luong luong);
	    Luong findById(Integer id);
	    Boolean update(Luong luong);
	    Boolean delete(Integer id);
	}
