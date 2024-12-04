package com.DoAnJavaWeb.service;

import java.util.List;

import com.DoAnJavaWeb.models.NghiPhep;

public interface NghiPhepService {
	List<NghiPhep> getAll();

	Boolean create(NghiPhep nghiPhep);

	NghiPhep findById(Integer idLeave);

	Boolean update(NghiPhep nghiPhep);

	Boolean delete(Integer idLeave);
}
