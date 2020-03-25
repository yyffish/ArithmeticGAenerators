package com.AG.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.AG.entity.Expression;

/**
 * @version 1.0
 * @date 2020/3/19 18:39
 */
public class ExpressionDao {

	Random random = new Random();// 用来生成随机数
	CalculateUtils calculateUtils = new CalculateUtils();

	/**
	 * 比较express表达式是否存在于examList表达式列表中
	 *
	 * @param examList 表达式列表
	 * @param express  表达式
	 * @return 结果 存在返回true 不存在返回false
	 */
	public Boolean isExistList(List<Expression> examList, Expression express) {
		int len = examList.size();
		for (int i = 0; i < len; i++) {
			if (isSame(express, examList.get(i)))
				return true;
		}
		return false;
	}

	/**
	 * 比较两条表示式是否相同
	 * 
	 * @param express1 表达式1
	 * @param express2 表达式2
	 * @return 相同返回true，否则返回false
	 */
	private Boolean isSame(Expression express1, Expression express2) {
		// 运算符数目不相等，直接返回
		if (express1.getOperatorList().size() != express2.getOperatorList().size())
			return false;
		Set<Map<Integer, Integer>> parameterSet = new HashSet<Map<Integer, Integer>>();
		Set<String> operatorSet = new HashSet<String>();
		List<Map<Integer, Integer>> parameterList = express1.getParameterList();// 获取表达式1的参数列表
		List<String> operatorList = express1.getOperatorList();// 获取表达式1的操作符列表
		int len = operatorList.size();
		// 将参数和操作符列表分别加进Set
		for (int i = 0; i < len; i++) {
			operatorSet.add(operatorList.get(i));
			parameterSet.add(parameterList.get(i));
		}
		parameterSet.add(parameterList.get(len));
		parameterList = express2.getParameterList();// 获取表达式2的参数列表
		operatorList = express2.getOperatorList();// 获取表达式2的操作符列表
		for (int i = 0; i < len; i++) {
			if (!operatorSet.contains(operatorList.get(i)) || !parameterSet.contains(parameterList.get(i))) {
				return false;
			}
		}
		if (!parameterSet.contains(parameterList.get(len)))
			return false;
		return true;
	}

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
		int a = random.nextInt(max - 1) + 1;
		parameterMap.put(0, a);
		parameterMap.put(1, 1);
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
		Integer denominator = random.nextInt(max - 1) + 1;
		Integer numerator = random.nextInt(denominator * max - 1) + 1;
		Integer gcd = calculateUtils.gcd(numerator, denominator);
		numerator /= gcd;
		denominator /= gcd;
		parameterMap.put(0, numerator);
		parameterMap.put(1, denominator);
		return parameterMap;
	}

	/**
	 * 生成运算符
	 *
	 * @return operator
	 */
	private String getOperator() {
		String operator = null;
		int flag = random.nextInt(4);
		switch (flag) {
		case 0:
			operator = "+";
			break;
		case 1:
			operator = "-";
			break;
		case 2:
			operator = "*";
			break;
		case 3:
			operator = "÷";
			break;
		}
		return operator;
	}

	/**
	 * 生成运算符个数(1-3个)
	 *
	 * @return operator
	 */
	public Integer getOperatorNum() {
		return random.nextInt(3) + 1;
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

	/**
	 * 返回括号类型
	 * 
	 * @param operatorNum 操作符数量
	 * @return 返回括号类型
	 */
	public Integer getBracketType(Integer operatorNum) {
		if (Math.random() > 0.5) {
			if (operatorNum == 2) {
				// 返回1~2
				return random.nextInt(2) + 1;
			} else if (operatorNum == 3) {
				// 返回3~12
				return random.nextInt(10) + 3;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	/**
	 * expression转为String
	 * 
	 * @param map 分数
	 * @return String
	 */
	public String expressionToString(Expression expression) {
		StringBuffer stringBuffer = new StringBuffer();
//		int length = expression.getOperatorList().size();
//		for (int i = 0; i < length; i++) {
//			stringBuffer.append(
//					expression.getParameterList().get(i).get(0) + "/" + expression.getParameterList().get(i).get(1));
//			stringBuffer.append(" " + expression.getOperatorList().get(i) + " ");
//		}
//		stringBuffer.append(expression.getParameterList().get(length).get(0) + "/"
//				+ expression.getParameterList().get(length).get(1));
//		return stringBuffer.toString();
		switch (expression.getBracketType()) {
		case 1:
			// ( 1/1 + 2/1 ) + 3/1
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(0).get(0) + "/" + expression.getParameterList().get(0).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(0));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(1).get(0) + "/" + expression.getParameterList().get(1).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(")");
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(1));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(2).get(0) + "/" + expression.getParameterList().get(2).get(1));
			break;
		case 2:
			// 1/1 + ( 2/1 + 3/1 )
			stringBuffer.append(
					expression.getParameterList().get(0).get(0) + "/" + expression.getParameterList().get(0).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(0));
			stringBuffer.append(" ");
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(1).get(0) + "/" + expression.getParameterList().get(1).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(1));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(2).get(0) + "/" + expression.getParameterList().get(2).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(")");
			break;
		case 3:
			// ( 1/1 + 2/1 ) + 3/1 + 4/1
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(0).get(0) + "/" + expression.getParameterList().get(0).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(0));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(1).get(0) + "/" + expression.getParameterList().get(1).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(")");
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(1));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(2).get(0) + "/" + expression.getParameterList().get(2).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(2));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(3).get(0) + "/" + expression.getParameterList().get(3).get(1));
			break;
		case 4:
			// 1/1 + ( 2/1 + 3/1 ) + 4/1
			stringBuffer.append(
					expression.getParameterList().get(0).get(0) + "/" + expression.getParameterList().get(0).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(0));
			stringBuffer.append(" ");
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(1).get(0) + "/" + expression.getParameterList().get(1).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(1));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(2).get(0) + "/" + expression.getParameterList().get(2).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(")");
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(2));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(3).get(0) + "/" + expression.getParameterList().get(3).get(1));
			break;
		case 5:
			// 1/1 + 2/1 + ( 3/1 + 4/1 )
			stringBuffer.append(
					expression.getParameterList().get(0).get(0) + "/" + expression.getParameterList().get(0).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(0));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(1).get(0) + "/" + expression.getParameterList().get(1).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(1));
			stringBuffer.append(" ");
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(2).get(0) + "/" + expression.getParameterList().get(2).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(2));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(3).get(0) + "/" + expression.getParameterList().get(3).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(")");
			break;
		case 6:
			// ( 1/1 + 2/1 ) + ( 3/1 + 4/1 )
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(0).get(0) + "/" + expression.getParameterList().get(0).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(0));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(1).get(0) + "/" + expression.getParameterList().get(1).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(")");
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(1));
			stringBuffer.append(" ");
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(2).get(0) + "/" + expression.getParameterList().get(2).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(2));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(3).get(0) + "/" + expression.getParameterList().get(3).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(")");
			break;
		case 7:
			// ( 1/1 + 2/1 + 3/1 ) + 4/1
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(0).get(0) + "/" + expression.getParameterList().get(0).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(0));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(1).get(0) + "/" + expression.getParameterList().get(1).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(1));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(2).get(0) + "/" + expression.getParameterList().get(2).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(")");
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(2));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(3).get(0) + "/" + expression.getParameterList().get(3).get(1));
			break;
		case 8:
			// ( ( 1/1 + 2/1 ) + 3/1 + 4/1 )
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(0).get(0) + "/" + expression.getParameterList().get(0).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(0));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(1).get(0) + "/" + expression.getParameterList().get(1).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(")");
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(1));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(2).get(0) + "/" + expression.getParameterList().get(2).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(2));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(3).get(0) + "/" + expression.getParameterList().get(3).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(")");
			break;
		case 9:
			// ( 1/1 + ( 2/1 + 3/1 ) ) + 4/1
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(0).get(0) + "/" + expression.getParameterList().get(0).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(0));
			stringBuffer.append(" ");
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(1).get(0) + "/" + expression.getParameterList().get(1).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(1));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(2).get(0) + "/" + expression.getParameterList().get(2).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(")");
			stringBuffer.append(" ");
			stringBuffer.append(")");
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(2));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(3).get(0) + "/" + expression.getParameterList().get(3).get(1));
			break;
		case 10:
			// 1/1 + ( 2/1 + 3/1 + 4/1 )
			stringBuffer.append(
					expression.getParameterList().get(0).get(0) + "/" + expression.getParameterList().get(0).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(0));
			stringBuffer.append(" ");
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(1).get(0) + "/" + expression.getParameterList().get(1).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(1));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(2).get(0) + "/" + expression.getParameterList().get(2).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(2));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(3).get(0) + "/" + expression.getParameterList().get(3).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(")");
			break;
		case 11:
			// 1/1 + ( ( 2/1 + 3/1 ) + 4/1 )
			stringBuffer.append(
					expression.getParameterList().get(0).get(0) + "/" + expression.getParameterList().get(0).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(0));
			stringBuffer.append(" ");
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(1).get(0) + "/" + expression.getParameterList().get(1).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(1));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(2).get(0) + "/" + expression.getParameterList().get(2).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(")");
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(2));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(3).get(0) + "/" + expression.getParameterList().get(3).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(")");
			break;
		case 12:
			// 1/1 + ( 2/1 + ( 3/1 + 4/1 ) )
			stringBuffer.append(
					expression.getParameterList().get(0).get(0) + "/" + expression.getParameterList().get(0).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(0));
			stringBuffer.append(" ");
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(1).get(0) + "/" + expression.getParameterList().get(1).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(1));
			stringBuffer.append(" ");
			stringBuffer.append("(");
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(2).get(0) + "/" + expression.getParameterList().get(2).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(expression.getOperatorList().get(2));
			stringBuffer.append(" ");
			stringBuffer.append(
					expression.getParameterList().get(3).get(0) + "/" + expression.getParameterList().get(3).get(1));
			stringBuffer.append(" ");
			stringBuffer.append(")");
			stringBuffer.append(" ");
			stringBuffer.append(")");
			break;
		default:
			// 1/1 + 2/1 + 3/1
			int length = expression.getOperatorList().size();
			for (int i = 0; i < length; i++) {
				stringBuffer.append(expression.getParameterList().get(i).get(0) + "/"
						+ expression.getParameterList().get(i).get(1));
				stringBuffer.append(" " + expression.getOperatorList().get(i) + " ");
			}
			stringBuffer.append(expression.getParameterList().get(length).get(0) + "/"
					+ expression.getParameterList().get(length).get(1));
			break;
		}
		return stringBuffer.toString();
	}

}
