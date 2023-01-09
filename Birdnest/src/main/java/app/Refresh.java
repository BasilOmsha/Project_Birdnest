package app;

import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import model.Violations;

@WebServlet(name = "refreshData", urlPatterns = { "/refreshData" })
public class Refresh extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		HttpSession session = request.getSession();
		Dao dao = new Dao();
		try {
			List<Violations> dataList = dao.readAllData();
//			System.out.println("FunctionList: " + dataList);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			// Convert the data to a JSON object and write it to the response
			Gson gson = new Gson();
			String json = gson.toJson(dataList);
			response.getWriter().write(json);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.close();

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

	}
}
