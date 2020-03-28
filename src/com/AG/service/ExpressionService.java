package com.AG.service;

import java.util.ArrayList;
import java.util.List;

import com.AG.dao.CalculateUtils;
import com.AG.dao.ExpressionDao;
import com.AG.entity.Expression;

public class ExpressionService {
	ExpressionDao expressionDao = new ExpressionDao();
	CalculateUtils calculateUtils = new CalculateUtils();

	/**
	 * 生成题目列表
	 * 
	 * @param max     用户输入的最大值
	 * @param examNum 题目数量
	 * @return 题目列表
	 */
	public List<String> getExamList(int max, int examNum) {
		List<Expression> examList = new ArrayList<>();
		for (int i = 0; i < examNum; i++) {
			// 新建Expression对象
			Expression expression = new Expression();
			// 获取操作数数量
			Integer operatorNum = expressionDao.getOperatorNum();
			// 设置参数列表
			expression.setParameterList(expressionDao.getParameterList(max, operatorNum));
			// 设置操作符列表
			expression.setOperatorList(expressionDao.getOperatorList(operatorNum));
			// 设置括号类型
			expression.setBracketType(expressionDao.getBracketType(operatorNum));
			// 判重
			if (expressionDao.isExistList(examList, expression)) {
				continue;
			}
			// Expression对象转为String
			String express = expressionDao.expressionToString(expression);
			// 通过String计算结果判断是否符合要求
			String res = calculateUtils.calculate(express);
			if (res.contains("-") || res.contains("/0")) {
				i--;
				continue;
			}
			// 添加到List中
			examList.add(expression);
		}
		// 讲包装expression的List转为包装String的List
		List<String> examStringList = new ArrayList<>();
		for (int i = 0; i < examList.size(); i++) {
			examStringList.add(expressionDao.expressionToString(examList.get(i)));
		}
		return examStringList;
	}

	/**
	 * 根据算式list计算resList
	 * 
	 * @param list 表达式列表
	 * @return 结果列表
	 */
	public List<String> getResultList(List<String> list) {
		CalculateService calculateService = new CalculateService();
		List<String> resultList = new ArrayList<>();
		for (String string : list) {
			resultList.add(calculateService.calcluate(string));
		}
		return resultList;
	}

	/**
	 * 比较express表达式是否存在于examList表达式列表中
	 *
	 * @param examList 表达式列表
	 * @param express  表达式
	 * @return 结果 存在返回true 不存在返回false
	 */
	public Boolean isExistList(List<Expression> examList, Expression express) {
		return expressionDao.isExistList(examList, express);
	}
}
