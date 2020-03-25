package com.AG.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AG.dao.FileUtils;
import com.AG.service.ExpressionService;

/**
 * Servlet implementation class testServlet
 */
@WebServlet("/exam")
public class examServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public examServlet() {
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
		ExpressionService expressionService = new ExpressionService();
		FileUtils fileUtils = new FileUtils();
		int examNum = Integer.parseInt(request.getParameter("examNum"));
		int maxNum = Integer.parseInt(request.getParameter("maxNum"));
		List<String> examList = expressionService.getExamList(maxNum, examNum);
		List<String> ansList = expressionService.getResultList(examList);
		String exercisesFilePath = "C:/Users/Fish/Desktop/Exercises.txt";
		String answersFilePath = "C:/Users/Fish/Desktop/Answers.txt";
		fileUtils.writeTxtFile(examList, exercisesFilePath);
		fileUtils.writeTxtFile(ansList, answersFilePath);
		request.setAttribute("examList", examList);
		request.getRequestDispatcher("/table.jsp").forward(request, response);
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
