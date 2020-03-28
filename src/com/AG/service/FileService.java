package com.AG.service;

import java.util.ArrayList;
import java.util.List;

import com.AG.dao.FileUtils;

public class FileService {
	FileUtils fileUtils = new FileUtils();

	/**
	 * 添加序号->转整数->写入到txt中
	 * 
	 * @param exercisesList 题目List
	 * @param filePath      文件路径
	 * @return 是否成功
	 */
	public boolean writeTxtFile(List<String> exercisesList, String filePath) {
		List<String> list = new ArrayList<>();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < exercisesList.size(); i++) {
			stringBuffer.append(i + 1);
			stringBuffer.append(": ");
			stringBuffer.append(exercisesList.get(i));
			// 将 9/1 转为整数 9
			while (stringBuffer.indexOf("/1 ") != -1) {
				int a = stringBuffer.indexOf("/1 ");
				stringBuffer.delete(a, a + 2);
			}
			// 保证将最后一个 /1 也删掉
			int last = stringBuffer.lastIndexOf("/1");
			if (last + 2 == stringBuffer.length()) {
				stringBuffer.delete(last, last + 2);
			}
			// 添加到List中
			list.add(stringBuffer.toString());
			stringBuffer.delete(0, stringBuffer.length());
		}
		// 写入
		if (fileUtils.writeTxtFile(list, filePath)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将含有 9/1 的表达式List转为 9 的List 用于前端展示
	 * 
	 * @param exercisesList 题目List
	 * @return 含有整数的题目List
	 */
	public List<String> toInt(List<String> exercisesList) {
		List<String> list = new ArrayList<>();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < exercisesList.size(); i++) {
			stringBuffer.append(exercisesList.get(i));
			// 将 9/1 转为整数 9
			while (stringBuffer.indexOf("/1 ") != -1) {
				int a = stringBuffer.indexOf("/1 ");
				stringBuffer.delete(a, a + 2);
			}
			// 保证将最后一个 /1 也删掉
			int last = stringBuffer.lastIndexOf("/1");
			if (last + 2 == stringBuffer.length()) {
				stringBuffer.delete(last, last + 2);
			}
			// 添加到List中
			list.add(stringBuffer.toString());
			stringBuffer.delete(0, stringBuffer.length());
		}
		return list;
	}

	/**
	 * 读取文件后删除序号
	 * 
	 * @param filePath 文件路径
	 * @return
	 */
	public List<String> readTxtFile(String filePath) {
		List<String> txtList = fileUtils.readTxtFile(filePath);
		List<String> list = new ArrayList<>();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < txtList.size(); i++) {
			stringBuffer.append(txtList.get(i));
			stringBuffer.delete(0, stringBuffer.indexOf(":") + 2);
			String[] expressArr = stringBuffer.toString().split(" ");
			stringBuffer.delete(0, stringBuffer.length());
			int length = expressArr.length;
			for (int j = 0; j < length; j++) {
				if (expressArr[j].matches("^-?\\d+$")) {
					expressArr[j] = expressArr[j] + "/1";
				}
				stringBuffer.append(expressArr[j]);
				if (j != (length - 1)) {
					stringBuffer.append(" ");
				}
			}
			list.add(stringBuffer.toString());
			stringBuffer.delete(0, stringBuffer.length());
		}
		return list;
	}
}
