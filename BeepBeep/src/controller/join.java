package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.memberDAO;
import model.memberDTO;


@WebServlet("/join")
public class join extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("EUC-kr");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		int tel = Integer.parseInt(request.getParameter("tel"));
		float height = Float.parseFloat(request.getParameter("height"));
		float weight = Float.parseFloat(request.getParameter("weight"));
		
		
		memberDAO memberdao = new memberDAO();
		
		int cnt = memberdao.join(id, pw, name, age, gender, email, tel, height, weight);
		
		if(cnt>0) {
			System.out.println("ȸ������ ����");
			response.sendRedirect("main.jsp");
		}else {
			System.out.println("ȸ������ ����");
			response.sendRedirect("Join.jsp");
		}
		
	
		
	}

}