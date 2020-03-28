package com.AG.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	/**
	 * 创建一个txt文件
	 * 
	 * @param filePath 文件路径
	 * @return 创建成功返回true，已存在返回false
	 */
	public boolean creatFile(String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		if (file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			flag = true;
		}
		return flag;
	}

	/**
	 * 读取题目文件或答案文件 都是一行一行读取
	 * 
	 * @param filePath 文件路径
	 * @return 返回题目或答案List
	 */
	public List<String> readTxtFile(String filePath) {
		List<String> list = new ArrayList<String>();
		String thisLine = null;
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				while ((thisLine = bufferedReader.readLine()) != null) {
					list.add(thisLine);
				}
				bufferedReader.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 读txt文件
	 * 
	 * @param list
	 * @param filePath
	 * @return
	 */
	public boolean writeTxtFile(List<String> list, String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
			for (String string : list) {
				bufferedWriter.write(string);
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		flag = true;
		return flag;
	}

}
