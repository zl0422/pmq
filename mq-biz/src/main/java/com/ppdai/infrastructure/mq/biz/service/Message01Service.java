package com.ppdai.infrastructure.mq.biz.service;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.ppdai.infrastructure.mq.biz.entity.Message01Entity;

/**
 * @author dal-generator
 */
public interface Message01Service {
	// 重试次数超过这个数的表示重试成功
	int failMsgRetryCountSuc = 100000;

	void insertBatchDy(String topic, String tbName, List<Message01Entity> entities);

	List<Message01Entity> getListDy(String topic, String tbName, long start, long end);

	List<Message01Entity> getListByPage(Map<String, Object> parameterMap);

	Long getTableMinId(String tbName);

	long countByPage(Map<String, Object> parameterMap);

	Message01Entity getMessageById(String tbName, long id);

	List<Message01Entity> getMessageByIds(String tbName, List<Long> ids);

	Message01Entity getNearByMessageById(String tbName, long id);

	void deleteDy(String tbName, long start, long end);
	
	void deleteByIds(String tbName, List<Long> ids);

	/*
	 * 注意此最大值为下一次的插入的id
	 */
	Long getMaxId(String tbName);

	DataSource getDataSource();

	void setDbId(long dbNodeId);

	void clearDbId();

	/*
	 * key为dbname,里面的key为tbname value为id ,注意此id为下一次的插入的id
	 */
	Map<String, Map<String, Long>> getMaxId();

	void truncate(String tbName);

	int getTableQuantityByDbName(String dbName);

	List<String> getTableNamesByDbName(String dbName);

	//boolean health();

	/**
	 * 专门用来创建 message_x 表
	 * 
	 * @param tbName
	 */
	void createMessageTable(String tbName);

	List<Message01Entity> getListByTime(String tbName, String insertTime);

	String getDbName();

	String getMaxConnectionsCount();

	Integer getConnectionsCount();

	void updateFailMsgResult(String tbName, List<Long> ids, int retryCount);

	int deleteOldFailMsg(String tbName, long id, int retryCount);
}
