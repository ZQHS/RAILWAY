package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import Dao.RailwayDao;
import Entity.Plan;
import Entity.Railway;
import Exception.SearchException;
import Service.RailwayService;

@WebServlet("/RailwayServlet")
public class RailwayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	RailwayDao railwayDao=new RailwayDao();
	RailwayService railwayService = new RailwayService();
	
    public RailwayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("searchRailway".equals(method)) {
			searchRailway(request, response);
		}
		if ("searchPlan".equals(method)) {
			searchPlan(request, response);
		}
		if ("insertRailway".equals(method)) {
			insertRailway(request, response);
		}
		if ("deleteRailway".equals(method)) {
			deleteRailway(request, response);
		}
		if ("editRailway".equals(method)) {
			editRailway(request, response);
		}
		if ("insertPlan".equals(method)) {
			insertPlan(request, response);
		}
		if ("deletePlan".equals(method)) {
			deletePlan(request, response);
		}
		if ("editPlan".equals(method)) {
			editPlan(request, response);
		}
		if ("editCoordinate".equals(method)) {
			editCoordinate(request, response);
		}
	}
	
	private void searchRailway(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		try {
			List<Railway> Railway = railwayDao.searchRailway();
			response.setContentType("text/html;charset=UTF-8");   
			PrintWriter out = response.getWriter();
			String json_Railway = JSON.toJSONString(Railway);
			out.println(json_Railway);
			out.flush();
			out.close();
		} catch (SearchException e) {
			e.printStackTrace();
			request.getRequestDispatcher("http://localhost:8080/RAILWAY").forward(request, response);
		}
	}
	
	private void searchPlan(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		try {
			String railway_id = request.getParameter("railway_id");
			List<Plan> Plan= railwayDao.searchPlan(railway_id);
			response.setContentType("text/html;charset=UTF-8");   
			PrintWriter out = response.getWriter();
			String json_Plan = JSON.toJSONString(Plan, SerializerFeature.DisableCircularReferenceDetect);
			out.println(json_Plan);
			out.flush();
			out.close();
		} catch (SearchException e) {
			e.printStackTrace();
			request.getRequestDispatcher("http://localhost:8080/RAILWAY").forward(request, response);
		}
	}
	
	private void insertRailway(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		try {
			String railway_name = request.getParameter("railway_name");
			String grade = request.getParameter("grade");
			String speed = request.getParameter("speed");
			String lines = request.getParameter("lines");
			String track = request.getParameter("track");
			String min_radius = request.getParameter("min_radius");
			String slope = request.getParameter("slope");
			railwayDao.insertRailway( railway_name, grade, speed, lines, track, min_radius, slope);
			response.sendRedirect("http://localhost:8080/RAILWAY");
		} catch (SearchException e) {
			e.printStackTrace();
			request.getRequestDispatcher("http://localhost:8080/RAILWAY").forward(request, response);
		}
	}
	
	private void deleteRailway(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		try {
			String railway_id = request.getParameter("railway_id");
			String confirm = request.getParameter("confirm");
			if("我确定".equals(confirm)) {
				railwayDao.deleteRailway( railway_id);
			}
			response.sendRedirect("http://localhost:8080/RAILWAY");
		} catch (SearchException e) {
			e.printStackTrace();
			request.getRequestDispatcher("http://localhost:8080/RAILWAY").forward(request, response);
		}
	}

	private void editRailway(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		try {
			String railway_id = request.getParameter("railway_id");
			String railway_name = request.getParameter("railway_name");
			String grade = request.getParameter("grade");
			String speed = request.getParameter("speed");
			String lines = request.getParameter("lines");
			String track = request.getParameter("track");
			String min_radius = request.getParameter("min_radius");
			String slope = request.getParameter("slope");	
			railwayDao.editRailway( railway_id, railway_name, grade, speed, lines, track, min_radius, slope);
			response.sendRedirect("http://localhost:8080/RAILWAY");
		} catch (SearchException e) {
			e.printStackTrace();
			request.getRequestDispatcher("http://localhost:8080/RAILWAY").forward(request, response);
		}
	}
	
	private void insertPlan(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		try {
			String railway_id = request.getParameter("railway_id");
			String plan_name = request.getParameter("plan_name");
			String lenght = request.getParameter("lenght");
			String tunnel = request.getParameter("tunnel");
			String bridge = request.getParameter("bridge");
			String height = request.getParameter("height");
			String coordinate =request.getParameter("coordinate");
			railwayService.insertPlan( railway_id, plan_name, lenght, tunnel, bridge, height, coordinate);
			response.sendRedirect("http://localhost:8080/RAILWAY/#/"+railway_id);
		} catch (SearchException e) {
			e.printStackTrace();
			request.getRequestDispatcher("http://localhost:8080/RAILWAY").forward(request, response);
		}
	}
	
	private void editPlan(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		try {
			String railway_id = request.getParameter("railway_id");
			String plan_id = request.getParameter("plan_id");
			String plan_name = request.getParameter("plan_name");
			String lenght = request.getParameter("lenght");
			String tunnel = request.getParameter("tunnel");
			String bridge = request.getParameter("bridge");
			String height = request.getParameter("height");
			railwayDao.editPlan(  plan_id, plan_name, lenght, tunnel, bridge, height);
			response.sendRedirect("http://localhost:8080/RAILWAY/#/"+railway_id);
		} catch (SearchException e) {
			e.printStackTrace();
			request.getRequestDispatcher("http://localhost:8080/RAILWAY").forward(request, response);
		}
	}
	
	private void editCoordinate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		try {
			String plan_id = request.getParameter("plan_id");
			String railway_id = request.getParameter("railway_id");
			String coordinate =request.getParameter("coordinate");
			String line_id = request.getParameter("plan_id");
			String tunnel = request.getParameter("tunnel");
			String bridge = request.getParameter("bridge");
			railwayService.editCoordinate(plan_id, tunnel, bridge, line_id, coordinate, railway_id );
			response.sendRedirect("http://localhost:8080/RAILWAY/#/"+railway_id);
		} catch (SearchException e) {
			e.printStackTrace();
			request.getRequestDispatcher("http://localhost:8080/RAILWAY").forward(request, response);
		}
	}
	
	private void deletePlan(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		try {
			String railway_id = request.getParameter("railway_id");
			String plan_id = request.getParameter("plan_id");
			String line_id = request.getParameter("line_id");
			String confirm = request.getParameter("confirm");
			if("我确定".equals(confirm)){
				railwayDao.deletePlan( plan_id, line_id );
			}
			response.sendRedirect("http://localhost:8080/RAILWAY/#/"+railway_id);
		} catch (SearchException e) {
			e.printStackTrace();
			request.getRequestDispatcher("http://localhost:8080/RAILWAY").forward(request, response);
		}
	}
}
