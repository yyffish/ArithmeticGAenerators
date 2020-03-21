package com.AG.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @date 2020/3/19 18:39
 */
public class ExpressionDao {

	/**
	 * 调用本dao层的方法生成整数或随机数
	 *
	 * @param max 用户定义的最大值
	 * @return 生成的随机整数或分数
	 */
	private Map<Integer, Integer> getIntegerOrFraction(Integer max) {
		// 生成随机数判断生成整数或分数
		if (Math.random() > 0.5) {
			// 生成整数
			return getIntegerParameters(max);
		} else {
			// 生成分数
			return getFractionParameters(max);
		}
	}

	/**
	 * 随机生成整数参数 parameterMap(0)是分子 parameterMap(1)是分母
	 *
	 * @param max 用户定义的最大值
	 * @return 生成的随机整数
	 */
	private Map<Integer, Integer> getIntegerParameters(Integer max) {
		Map<Integer, Integer> parameterMap = new HashMap<>();
		return parameterMap;
	}

	/**
	 * 随机生成分数参数 parameterMap(0)是分子 parameterMap(1)是分母
	 *
	 * @param max 用户定义的最大值
	 * @return 生成的随机整数
	 */
	private Map<Integer, Integer> getFractionParameters(Integer max) {
		Map<Integer, Integer> parameterMap = new HashMap<>();
		return parameterMap;
	}

	/**
	 * 生成运算符
	 *
	 * @return operator
	 */
	private String getOperator() {
		String operator = null;
		return operator;
	}

	/**
	 * 生成运算符个数(1-3个)
	 *
	 * @return operator
	 */
	private Integer getOperatorNum() {
		return 0;
	}

	/**
	 * 生成操作符列表
	 *
	 * @param operatorNum 操作符数量
	 * @return 操作符列表
	 */
	public List<String> getOperatorList(Integer operatorNum) {
		// 建立操作符List
		List<String> operatorList = new ArrayList<>();
		// 随机生成操作符，并添加到其中
		for (int i = 0; i < operatorNum; i++) {
			operatorList.add(getOperator());
		}
		// 返回List
		return operatorList;
	}

	/**
	 * 生成参数列表
	 *
	 * @param max         用户定义的最大值
	 * @param operatorNum 操作符数量
	 * @return 参数列表
	 */
	public List<Map<Integer, Integer>> getParameterList(Integer max, Integer operatorNum) {
		// 建立操作符List
		List<Map<Integer, Integer>> parameterList = new ArrayList<>();
		// 随机生成操作符，并添加到其中
		for (int i = 0; i < operatorNum + 1; i++) {
			parameterList.add(getIntegerOrFraction(max));
		}
		// 返回List
		return parameterList;
	}

//	/**
//	 * 根据parameterList和operatorList计算结果
//	 *
//	 * @param parameterList 参数列表
//	 * @param operatorList  操作符列表
//	 * @return 运算结果
//	 */
//	public Map<Integer, Integer> getResult(List<Map<Integer, Integer>> parameterList, List<String> operatorList) {
//		Map<Integer, Integer> result = new HashMap<>();
//		return result;
//	}
}
