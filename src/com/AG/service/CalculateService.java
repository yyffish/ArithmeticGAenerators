package com.AG.service;

import com.AG.dao.CalculateUtils;

public class CalculateService {
	CalculateUtils calculateUtils = new CalculateUtils();

	/**
	 * 封装dao层
	 * 
	 * @param express 用空格间隔的表达式
	 * @return String类型的计算结果
	 */
	public String calcluate(String express) {
		return calculateUtils.calculate(express);
	}
}
