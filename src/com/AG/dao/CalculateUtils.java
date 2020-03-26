package com.AG.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalculateUtils {

	/**
	 * 唯一能调用的方法
	 * 
	 * @param express 用空格间隔的表达式
	 * @return String类型的计算结果
	 */
	public String calculate(String express) {
		return calcRevPolishNotation(transfer(express));
	}

	/**
	 * 辗转相除法求公约数
	 *
	 * @param numerator   分子
	 * @param denominator 分母
	 * @return 最大公约数
	 */
	public Integer gcd(Integer numerator, Integer denominator) {
		int temp;
		while (denominator > 0) {
			temp = numerator % denominator;
			numerator = denominator;
			denominator = temp;
		}
		return numerator;
	}

	/**
	 * 通过逆波兰表达式计算结果
	 *
	 * @param 表达式
	 * @return Stringl类型的计算结果
	 */
	private String calcRevPolishNotation(String express) {
		Stack<String> stack = new Stack<>();
		String[] expressArr = express.split(" ");
		for (int i = 0; i < expressArr.length; i++) {
			if (expressArr[i].matches("[0-9]+/[0-9]+")) {
				stack.push(expressArr[i]);
				// + - * / 运算符的处理
			} else if (expressArr[i].matches("[\\+\\-\\*\\÷]")) {
				String k1 = stack.pop();
				String k2 = stack.pop();
				// 计算结果
				String res = calcValue(k1, k2, expressArr[i]);
				stack.push(res);
			}

		}
		return stack.pop();
	}

	/**
	 * 调用计算函数
	 * 
	 * @param k1 参数1
	 * @param k2 参数2
	 * @param c  运算符
	 * @return Stringl类型的计算结果
	 */
	private String calcValue(String k1, String k2, String c) {
		switch (c) {
		case "+":
			return addition(k1, k2);
		case "-":
			return subtraction(k1, k2);
		case "*":
			return multiplication(k1, k2);
		case "÷":
			return division(k1, k2);
		default:
			throw new RuntimeException("没有该类型的运算符！");
		}
	}

	/**
	 * 加法
	 *
	 * @param k1 分子
	 * @param k2 分母
	 * @return String类型的结果
	 */
	private String addition(String k1, String k2) {
		if (k1.contains("/0") || k1.contains("-") || k2.contains("/0") || k2.contains("-")) {
			return "-1/1";
		}
		// 将字符串转为数组
		String[] parameter1 = k1.split("/");
		String[] parameter2 = k2.split("/");
		// 新建整型数组和 分子1 分子2 公约数3
		int numerator1, numerator2;
		Integer[] parameterArrTemp = new Integer[3];
		// 为整形数组1赋值
		numerator1 = Integer.parseInt(parameter1[0]) * Integer.parseInt(parameter2[1]);
		// 为整形数组2赋值
		numerator2 = Integer.parseInt(parameter2[0]) * Integer.parseInt(parameter1[1]);
		// 为临时整形数组赋值
		parameterArrTemp[0] = numerator1 + numerator2;
		parameterArrTemp[1] = Integer.parseInt(parameter2[1]) * Integer.parseInt(parameter1[1]);
		parameterArrTemp[2] = gcd(parameterArrTemp[0], parameterArrTemp[1]);
		// 返回结果
		return parameterArrTemp[0] + "/" + parameterArrTemp[1];
	}

	/**
	 * 减法
	 *
	 * @param k1 分子
	 * @param k2 分母
	 * @return String类型的结果
	 */
	private String subtraction(String k1, String k2) {
		if (k1.contains("/0") || k1.contains("-") || k2.contains("/0") || k2.contains("-")) {
			return "-1/1";
		}
		// 将字符串转为数组
		String[] parameter1 = k1.split("/");
		String[] parameter2 = k2.split("/");
		// 新建整型数组和 分子1 分子2 公约数3
		int numerator1, numerator2;
		Integer[] parameterArrTemp = new Integer[3];
		// 为整形数组1赋值
		numerator1 = Integer.parseInt(parameter1[0]) * Integer.parseInt(parameter2[1]);
		// 为整形数组2赋值
		numerator2 = Integer.parseInt(parameter2[0]) * Integer.parseInt(parameter1[1]);
		// 为临时整形数组赋值
		parameterArrTemp[0] = numerator2 - numerator1;
		parameterArrTemp[1] = Integer.parseInt(parameter2[1]) * Integer.parseInt(parameter1[1]);
		parameterArrTemp[2] = gcd(parameterArrTemp[0], parameterArrTemp[1]);
		// 返回结果
		if (parameterArrTemp[0] < 0) {
			return "-1/1";
		} else {
			return parameterArrTemp[0] + "/" + parameterArrTemp[1];
		}
	}

	/**
	 * 乘法
	 *
	 * @param k1 分子
	 * @param k2 分母
	 * @return String类型的结果
	 */
	private String multiplication(String k1, String k2) {
		if (k1.contains("/0") || k1.contains("-") || k2.contains("/0") || k2.contains("-")) {
			return "-1/1";
		}
		// 将字符串转为数组
		String[] parameter1 = k1.split("/");
		String[] parameter2 = k2.split("/");
		// 新建整型数组和 index-0-分子 index-1-分母 index-2-公约数
		Integer[] parameterArrTemp = new Integer[3];
		// 为临时整形数组赋值
		parameterArrTemp[0] = Integer.parseInt(parameter1[0]) * Integer.parseInt(parameter2[0]);
		parameterArrTemp[1] = Integer.parseInt(parameter1[1]) * Integer.parseInt(parameter2[1]);
		parameterArrTemp[2] = gcd(parameterArrTemp[0], parameterArrTemp[1]);
		// 返回结果
		return parameterArrTemp[0] / parameterArrTemp[2] + "/" + parameterArrTemp[1] / parameterArrTemp[2];
	}

	/**
	 * 除法
	 *
	 * @param k1 分子
	 * @param k2 分母
	 * @return String类型的结果
	 */
	private String division(String k1, String k2) {
		if (k1.contains("/0") || k1.contains("-") || k2.contains("/0") || k2.contains("-")) {
			return "-1/1";
		}
		// 将字符串转为数组
		String[] parameter1 = k1.split("/");
		String[] parameter2 = k2.split("/");
		// 新建整型数组和 index-0-分子 index-1-分母 index-2-公约数
		Integer[] parameterArrTemp = new Integer[3];
		// 为临时整形数组赋值
		parameterArrTemp[0] = Integer.parseInt(parameter1[0]) * Integer.parseInt(parameter2[1]);
		parameterArrTemp[1] = Integer.parseInt(parameter1[1]) * Integer.parseInt(parameter2[0]);
		parameterArrTemp[2] = gcd(parameterArrTemp[0], parameterArrTemp[1]);
		// 返回结果
		if (parameterArrTemp[1] > (parameterArrTemp[0])) {
			return "-1/1";
		} else {
			return parameterArrTemp[1] / parameterArrTemp[2] + "/" + parameterArrTemp[0] / parameterArrTemp[2];
		}
	}

	/**
	 * 将中缀表达式转换为后缀表达式（逆波兰表达式）
	 *
	 * @param express
	 * @return
	 */
	private String transfer(String express) {
		Stack<String> stack = new Stack<>();
		List<String> list = new ArrayList<>();
		String[] expressArr = express.split(" ");
		for (int i = 0; i < expressArr.length; i++) {
			if (expressArr[i].matches("[0-9]+/[0-9]+")) {
				list.add(expressArr[i]);
			} else if (expressArr[i].matches("[\\+\\-\\*\\÷]")) {
				// 如果stack为空
				if (stack.isEmpty()) {
					stack.push(expressArr[i]);
					continue;
				}
				// 不为空

				// 上一个元素不为（，且当前运算符优先级小于上一个元素则，将比这个运算符优先级大的元素全部加入到队列中
				while (!stack.isEmpty() && !stack.lastElement().equals("(")
						&& !comparePriority(expressArr[i], stack.lastElement())) {
					list.add(stack.pop());
				}
				stack.push(expressArr[i]);
			} else if ("(".equals(expressArr[i])) {
				// 遇到左小括号无条件加入
				stack.push(expressArr[i]);
			} else if (")".equals(expressArr[i])) {
				// 遇到右小括号，则寻找上一堆小括号，然后把中间的值全部放入队列中
				while (!("(").equals(stack.lastElement())) {
					list.add(stack.pop());
				}
				// 上述循环停止，这栈顶元素必为"("
				stack.pop();
			}
		}
		// 将栈中剩余元素加入到队列中
		while (!stack.isEmpty()) {
			list.add(stack.pop());
		}
		StringBuffer stringBuffer = new StringBuffer();
		// 变成字符串
		for (String s : list) {
			stringBuffer.append(s + " ");
		}
		return stringBuffer.toString();
	}

	/**
	 * 比较运算符的优先级
	 *
	 * @param o1
	 * @param o2
	 * @return
	 */
	private boolean comparePriority(String o1, String o2) {
		return getPriorityValue(o1) > getPriorityValue(o2);
	}

	/**
	 * 获得运算符的优先级
	 *
	 * @param str
	 * @return
	 */
	private int getPriorityValue(String str) {
		switch (str) {
		case "+":
			return 1;
		case "-":
			return 1;
		case "*":
			return 2;
		case "÷":
			return 2;
		default:
			throw new RuntimeException("没有该类型的运算符！");
		}
	}
}
