package com.wyc.service;

import java.util.List;

import com.wyc.entity.LogEntity;

public interface LogService {
	void saveLog(LogEntity log);

	List<LogEntity> getAllLogs();
}
