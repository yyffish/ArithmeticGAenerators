package com.AG.entity;

import java.util.List;
import java.util.Map;

public class Expression {
	// 参数列表
	private List<Map<Integer, Integer>> parameterList;
	// 操作符列表
	private List<String> operatorList;
	// 运算结果
	private Map<Integer, Integer> result;

	public Expression() {
		super();
	}

	public Expression(List<Map<Integer, Integer>> parameterList, List<String> operatorList,
			Map<Integer, Integer> result) {
		super();
		this.parameterList = parameterList;
		this.operatorList = operatorList;
		this.result = result;
	}

	public List<Map<Integer, Integer>> getParameterList() {
		return parameterList;
	}

	public void setParameterList(List<Map<Integer, Integer>> parameterList) {
		this.parameterList = parameterList;
	}

	public List<String> getOperatorList() {
		return operatorList;
	}

	public void setOperatorList(List<String> operatorList) {
		this.operatorList = operatorList;
	}

	public Map<Integer, Integer> getResult() {
		return result;
	}

	public void setResult(Map<Integer, Integer> result) {
		this.result = result;
	}

}
