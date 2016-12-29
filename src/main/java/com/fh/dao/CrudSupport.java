package com.fh.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.fh.entity.Page;
import com.fh.plugin.GeneralMapperReflectUtil;
import com.fh.plugin.GeneralQueryParam;
import com.fh.util.ReflectHelper;
import com.fh.util.UuidUtil;


@Repository("crudSupport")
public class CrudSupport {

	@Resource(name ="commonDao")
	private CommonDao commonDao;
	
	public <T> T selectByPrimaryKey(Class<T> clazz, String primaryValue)
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



	public <T> int insertSelective(T t) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		Class<?> clazz = t.getClass();

		String tableName = GeneralMapperReflectUtil.getTableName(clazz);
		Map<String, String> mapping = GeneralMapperReflectUtil.getAllFieldValueMapping(t);

		param.put("tableName", tableName);
		param.put("columnValueMapping", mapping);
		return commonDao.insertSelective(param);
	}

	public <T> int deleteByPrimaryKey(Class<T> clazz, String primaryValue) {
		Map<String, Object> param = new HashMap<String, Object>();

		String tableName = GeneralMapperReflectUtil.getTableName(clazz);
		String primaryKey = GeneralMapperReflectUtil.getPrimaryKey(clazz);

		param.put("tableName", tableName);
		param.put("primaryKey", primaryKey);
		param.put("primaryValue", primaryValue);

		return commonDao.deleteByPrimaryKey(param);
	}

	public <T> int updateByPrimaryKey(T t) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		Class<?> clazz = t.getClass();

		String tableName = GeneralMapperReflectUtil.getTableName(clazz);
		String primaryKey = GeneralMapperReflectUtil.getPrimaryKey(clazz);
		Map<String, String> mapping = GeneralMapperReflectUtil.getAllFieldValueMapping(t);
		String primaryValue = mapping.get(primaryKey);
		mapping.remove(primaryKey);
		param.put("tableName", tableName);
		param.put("primaryKey", primaryKey);
		param.put("primaryValue", primaryValue);
		param.put("columnValueMapping", mapping);

		return commonDao.updateByPrimaryKey(param);
	}

	public <T> int updateByPrimaryKeySelective(T t) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		Class<?> clazz = t.getClass();

		String tableName = GeneralMapperReflectUtil.getTableName(clazz);
		String primaryKey = GeneralMapperReflectUtil.getPrimaryKey(clazz);

		Map<String, String> mapping = GeneralMapperReflectUtil.getFieldValueMappingExceptNull(t);
		String primaryValue = mapping.get(primaryKey);
		mapping.remove(primaryKey);
		param.put("tableName", tableName);
		param.put("primaryKey", primaryKey);
		param.put("primaryValue", primaryValue);
		param.put("columnValueMapping", mapping);

		return commonDao.updateByPrimaryKey(param);
	}

	public <T> int insertBatch(List<T> list) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		String tableName = "";
		List<String> columns = new ArrayList<String>();

		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

		for (T t : list) {
			if (tableName.equals("")) {
				Class<?> clazz = t.getClass();
				tableName = GeneralMapperReflectUtil.getTableName(clazz);
			}
			if (columns.size() == 0) {
				Class<?> clazz = t.getClass();
				columns = GeneralMapperReflectUtil.getAllColumns(clazz);
			}
			Map<String, String> mapping = GeneralMapperReflectUtil.getAllFieldValueMapping(t);
			dataList.add(mapping);
		}

		param.put("tableName", tableName);
		param.put("columns", columns);
		param.put("dataList", dataList);

		return commonDao.insertBatch(param);
	}

	public <T> int deleteByCondition(Class<T> clazz, String conditionExp,
			Map<String, Object> conditionParam) {
		Map<String, Object> param = new HashMap<String, Object>();

		String tableName = GeneralMapperReflectUtil.getTableName(clazz);

		param.put("tableName", tableName);
		param.put("conditionExp", conditionExp);
		param.put("conditionParam", conditionParam);

		return commonDao.deleteByCondition(param);
	}

	public <T> int updateByConditionSelective(Class<T> clazz,
			Map<String, Object> columnValueMapping, String conditionExp,
			Map<String, Object> conditionParam) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		String tableName = GeneralMapperReflectUtil.getTableName(clazz);

		param.put("tableName", tableName);
		param.put("columnValueMapping", columnValueMapping);
		param.put("conditionExp", conditionExp);
		param.put("conditionParam", conditionParam);

		return commonDao.updateByConditionSelective(param);
	}

	public <T> List<T> selectAdvanced(Class<T> clazz,
			GeneralQueryParam queryParam) throws Exception {
		List<T> result = new ArrayList<T>();

		queryParam.setQueryColumn(GeneralMapperReflectUtil.getAllColumns(clazz));

		List<Map<String, Object>> list = selectAdvancedByColumn(clazz, queryParam);

		if (list != null && list.size() != 0) {
			for (Map<String, Object> mapping : list) {
				result.add(GeneralMapperReflectUtil.parseToBean(mapping, clazz));
			}
		}
		return result;
	}

	public <T> List<Map<String, Object>> selectAdvancedByColumn(Class<T> clazz,
			GeneralQueryParam queryParam) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		String tableName = GeneralMapperReflectUtil.getTableName(clazz);

		param.put("tableName", tableName);
		param.put("queryColumn", queryParam.getQueryColumn());
		param.put("conditionExp", queryParam.getConditionExp());
		param.put("conditionParam", queryParam.getConditionParam());
		param.put("orderExp", queryParam.getOrderExp());

		if (queryParam.getPageSize() != null && queryParam.getPageNo() != null) {
			Map<String, Integer> page = new HashMap<String, Integer>();
			page.put("pageSize", queryParam.getPageSize());
			page.put("startRowNo", (queryParam.getPageNo() - 1) * queryParam.getPageSize());
			param.put("page", page);
		}

		return commonDao.selectAdvanced(param);
	}



	public <T> int saveOrUpdate(T t) throws Exception {
		Class<?> clazz = t.getClass();
		String primaryKey = GeneralMapperReflectUtil.getPrimaryField(clazz).getName();
		String primaryKeyValue=(String)ReflectHelper.getValueByFieldName(t, primaryKey);
		if(StringUtils.isBlank(primaryKeyValue)){
			ReflectHelper.setValueByFieldName(t, primaryKey, UuidUtil.get32UUID());
			return insertSelective(t);
		}else{
			return updateByPrimaryKey(t);
		}
	}
	
	public <T> List<T> listPage(Class<T> clazz,Page page) throws Exception {
		
		List<T> result = new ArrayList<T>();
		String tableName = GeneralMapperReflectUtil.getTableName(clazz);
		page.setTableName(tableName);
		page.setQueryColumn(GeneralMapperReflectUtil.getAllColumns(clazz));
		StringBuffer sb=new StringBuffer();
		for (Map.Entry<String, Object> m :page.getEqParam().entrySet())  {
			sb.append(" and "+m.getKey()+ " = #{conditionParam."+m.getKey()+"} ");
		}
		//like情况下where条件
		for (Map.Entry<String, Object> m :page.getLkParam().entrySet())  {
			sb.append(" and "+m.getKey()+ " like #{conditionParam."+m.getKey()+"} ");
		}
		if(org.apache.commons.lang3.StringUtils.isNoneBlank(sb.toString())){
			page.setConditionExp(sb.toString());
			page.getConditionParam().putAll(page.getEqParam());
			page.getConditionParam().putAll(page.getLkParam());
		}

		List<Map<String, Object>> list =commonDao.listPage(page);
		if (list != null && list.size() != 0) {
			for (Map<String, Object> mapping : list) {
				result.add(GeneralMapperReflectUtil.parseToBean(mapping, clazz));
			}
		}
		return result;
	}




	public <T> List<T> selectByEqCon(Class<T> clazz,
			Map<String, Object> conMapping) throws Exception {
		
		List<T> result = new ArrayList<T>();
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer sb=new StringBuffer();
		String tableName = GeneralMapperReflectUtil.getTableName(clazz);


		int i=0;
		for (Map.Entry<String, Object> m :conMapping.entrySet())  {
		    if(i>0) sb.append(" and "); 
			sb.append(m.getKey()+ " = #{conditionParam."+m.getKey()+"} ");
			i++;
		}
		param.put("tableName", tableName);
		param.put("queryColumn", GeneralMapperReflectUtil.getAllColumns(clazz));
		param.put("conditionExp", sb.toString());
		param.put("conditionParam", conMapping);
		List<Map<String, Object>> list=commonDao.selectAdvanced(param);
		if (list != null && list.size() != 0) {
			for (Map<String, Object> mapping : list) {
				result.add(GeneralMapperReflectUtil.parseToBean(mapping, clazz));
			}
		}
		return result;
	}




	public <T> int deleteBatch(Class<T> clazz, List<String> list) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		String tableName = GeneralMapperReflectUtil.getTableName(clazz);
		String primaryKey = GeneralMapperReflectUtil.getPrimaryKey(clazz);
		param.put("tableName", tableName);
		param.put("primaryKey", primaryKey);
		param.put("idList", list);
		return commonDao.deleteBatch(param);
	}
}
