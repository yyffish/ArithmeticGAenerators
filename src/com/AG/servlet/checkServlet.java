package com.AG.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AG.service.CalculateService;
import com.AG.service.FileService;

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
		FileService fileService = new FileService();
		CalculateService calculateService = new CalculateService();
		String exercisesFilePath = "C:/Users/Fish/Desktop/Exercises.txt";
		String answersFilePath = "C:/Users/Fish/Desktop/Answers.txt";
		List<String> exercises = fileService.readTxtFile(exercisesFilePath);
		List<String> answers = fileService.readTxtFile(answersFilePath);
		List<Integer> rightList = new ArrayList<Integer>();
		List<Integer> mistakeList = new ArrayList<Integer>();
		for (int i = 0; i < exercises.size(); i++) {
			System.out.println(calculateService.calcluate(exercises.get(i)));
			System.out.println("answers.get(i)" + answers.get(i));
			if (calculateService.calcluate(exercises.get(i)).equals(answers.get(i))) {
				rightList.add(i);
				// System.out.println("r:" + i);
			} else {
				mistakeList.add(i);
				// System.out.println("w:" + i);
			}
		}
		request.setAttribute("right", rightList.size());
		request.setAttribute("mistake", mistakeList.size());
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
