package com.fh.service.common;

import java.util.List;
import java.util.Map;

import com.fh.plugin.GeneralQueryParam;

public interface CommonManager {

	/**
	 * 根据主键查询
	 * @param <T> pojo类
	 * @param clazz pojo类-class对象
	 * @param primaryValue  主键值
	 * @return pojo对象
	 */
	<T> T selectByPrimaryKey(Class<T> clazz, String primaryValue) throws Exception;

	/**
	 * 插入或保存数据
	 * @param <T> pojo类
	 * @param t  pojo对象
	 * @return 数据条数
	 */
	<T> int saveOrUpdate(T t) throws Exception;

	/**
	 * 插入数据
	 * @param <T>  pojo类
	 * @param t  pojo对象
	 * @return 数据条数
	 */
	<T> int insertSelective(T t) throws Exception;

	/**
	 * 删除
	 * <p>根据主键删除 </p>
	 * @param <T> pojo类
	 * @param clazz  pojo类-class对象
	 * @param primaryValue 主键值
	 * @return 数据条数
	 */
	<T> int deleteByPrimaryKey(Class<T> clazz, String primaryValue);

	/**
	 * 更新
	 * <p> 根据主键更新 </p>
	 * <p> 更新pojo的所有字段，包括空值(null值)字段 </p>
	 * @param <T>  pojo类
	 * @param t  pojo对象
	 * @return 数据条数
	 */
	<T> int updateByPrimaryKey(T t) throws Exception;

	/**
	 * 更新
	 * <p>根据主键更新</p>
	 * <p> 更新pojo的非空字段 </p>
	 * @param <T> pojo类
	 * @param t  pojo对象
	 * @return 数据条数
	 */
	<T> int updateByPrimaryKeySelective(T t) throws Exception;

   //2016-11-13更新
   /**
	 * 批量插入
	 *@param list 数据集
	 *@return 数据条数
	 *@throws Exception
	 */
	<T> int insertBatch(List<T> list) throws Exception;

	/**
	 * 删除
	 * <p> 根据条件删除</p>
	 * @param <T> pojo类
	 * @param clazz pojo类-class对象
	 * @param conditionExp 查询条件 where 表达式 @see GeneralQueryParam
	 * @param conditionParam 查询条件 where 表达式中的参数集 @see GeneralQueryParam
	 * @return 数据条数
	 */
	<T> int deleteByCondition(Class<T> clazz, String conditionExp, Map<String, Object> conditionParam);
   
   /**
	 * 更新
	 * <p>根据条件更新 </p>
	 * <p> 更新pojo的指定字段集</p>
	 * @param clazz  pojo类-class对象
	 * @param columnValueMapping   需要更新的列名-值,注意列名必须与数据库中列名一致
	 * @param conditionExp  查询条件 where 表达式 @see GeneralQueryParam
	 * @param conditionParam  查询条件 where 表达式中的参数集 @see GeneralQueryParam
	 * @return 数据条数
	 */
	<T> int updateByConditionSelective(Class<T> clazz, Map<String, Object> columnValueMapping, String conditionExp,
			Map<String, Object> conditionParam) throws Exception;

   /**
	 * 高级查询
	 * @param clazz pojo类-class对象
	 * @param queryParam 查询参数
	 */
	<T> List<T> selectAdvanced(Class<T> clazz, GeneralQueryParam queryParam) throws Exception;

	/**
	 * 高级查询,指定返回列
	 * 
	 * @param clazz pojo类-class对象
	 * @param queryParam 查询参数
	 */
	<T> List<Map<String, Object>> selectAdvancedByColumn(Class<T> clazz, GeneralQueryParam queryParam) throws Exception;
	
	
   /**
	 * 多个and条件查询
	 */
	<T> List<T> selectByEqCon(Class<T> clazz, Map<String, Object> conMapping) throws Exception;

   /**
	 * 批量删除
	 */
	<T> int deleteBatch(Class<T> clazz, List<String> list)throws Exception;
}

