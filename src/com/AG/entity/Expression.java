package com.AG.entity;

import java.util.List;
import java.util.Map;

public class Expression {
	// 参数列表
	private List<Map<Integer, Integer>> parameterList;
	// 操作符列表
	private List<String> operatorList;
	// 运算结果
	private String result;
	// 括号类型
	private int bracketType;

	public Expression() {
		super();
	}

	public Expression(List<Map<Integer, Integer>> parameterList, List<String> operatorList, String result,
			int bracketType) {
		super();
		this.parameterList = parameterList;
		this.operatorList = operatorList;
		this.result = result;
		this.bracketType = bracketType;
	}

	public int getBracketType() {
		return bracketType;
	}

	public void setBracketType(int bracketType) {
		this.bracketType = bracketType;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
