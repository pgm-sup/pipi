package com.wyc.mapper;

import java.util.List;

import com.wyc.entity.LogEntity;

public interface LogMapper {
	void saveLog(LogEntity log);

	List<LogEntity> getAllLogs();

}
