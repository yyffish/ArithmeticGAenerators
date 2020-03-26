package com.AG.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AG.dao.FileUtils;
import com.AG.service.CalculateService;

/**
 * Servlet implementation class checkServlet
 */
@WebServlet("/check")
public class checkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public checkServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		FileUtils fileUtils = new FileUtils();
		CalculateService calculateService = new CalculateService();
		String exercisesFilePath = "C:/Users/Fish/Desktop/Exercises.txt";
		String answersFilePath = "C:/Users/Fish/Desktop/Answers.txt";
		List<String> exercises = fileUtils.readTxtFile(exercisesFilePath);
		List<String> answers = fileUtils.readTxtFile(answersFilePath);
		List<Integer> rightList = new ArrayList<Integer>();
		List<Integer> mistakeList = new ArrayList<Integer>();
		int right = 0;
		int mistake = 0;
		for (int i = 0; i < exercises.size(); i++) {
			if (answers.get(i).equals(calculateService.calcluate(exercises.get(i)))) {
				right++;
				rightList.add(i);
			} else {
				mistake++;
				mistakeList.add(i);
			}
		}
		request.setAttribute("right", right);
		request.setAttribute("mistake", mistake);
		request.setAttribute("rightList", rightList);
		request.setAttribute("mistakeList", mistakeList);
		request.getRequestDispatcher("/chart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
