//package app;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.concurrent.ScheduledThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import dao.Dao;
//import model.Violations;
//
//@WebServlet(name = "readData", urlPatterns = { "/readData" })
//public class GetData extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
////		HttpSession session = request.getSession();
//		Dao dao = new Dao();
//		try {
//			List<Violations> dataList = dao.readAllData();
//			System.out.println("FinctionList: " + dao.readAllData());
//			request.setAttribute("dataList", dataList);
//			RequestDispatcher rd = request.getRequestDispatcher("./jsp/drones.jsp");
//			try {
//				rd.forward(request, response);
//			} catch (ServletException | IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			response.setContentType("application/json");
//			response.setCharacterEncoding("UTF-8");
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		dao.close();
//
//	}
//
//	@Override
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//
//	}
//}
