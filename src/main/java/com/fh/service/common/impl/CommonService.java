package com.fh.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.CommonDao;
import com.fh.plugin.GeneralMapperReflectUtil;
import com.fh.plugin.GeneralQueryParam;
import com.fh.service.common.CommonManager;

@Service("commonService")
public class CommonService implements CommonManager{
	
	@Resource(name ="commonDao")
	private CommonDao commonDao;
	
	public <T> T selectByPrimaryKey(Class<T> clazz, Integer primaryValue)
			throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String tableName = GeneralMapperReflectUtil.getTableName(clazz);
		String primaryKey = GeneralMapperReflectUtil.getPrimaryKey(clazz);
		List<String> queryColumn = GeneralMapperReflectUtil.getAllColumns(clazz);

		param.put("tableName", tableName);
		param.put("primaryKey", primaryKey);
		param.put("primaryValue", primaryValue);
		param.put("queryColumn", queryColumn);

		return GeneralMapperReflectUtil.parseToBean(commonDao.selectByPrimaryKey(param), clazz);
	}

	public <T> int insert(T t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> int insertSelective(T t) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		Class<?> clazz = t.getClass();

		String tableName = GeneralMapperReflectUtil.getTableName(clazz);
		Map<String, String> mapping = GeneralMapperReflectUtil.getFieldValueMappingExceptNull(t);

		param.put("tableName", tableName);
		param.put("columnValueMapping", mapping);

		return commonDao.insertSelective(param);
	}

	public <T> int deleteByPrimaryKey(Class<T> clazz, String primaryValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> int updateByPrimaryKey(T t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> int updateByPrimaryKeySelective(T t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> int insertBatch(List<T> list) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> int deleteByCondition(Class<T> clazz, String conditionExp,
			Map<String, Object> conditionParam) {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> int updateByConditionSelective(Class<T> clazz,
			Map<String, Object> columnValueMapping, String conditionExp,
			Map<String, Object> conditionParam) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> List<T> selectAdvanced(Class<T> clazz,
			GeneralQueryParam queryParam) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> List<Map<String, Object>> selectAdvancedByColumn(Class<T> clazz,
			GeneralQueryParam queryParam) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
