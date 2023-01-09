package app;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

import model.Violations;

@WebServlet(name = "obs", urlPatterns = { "/obs" })
public class Observation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// This servlet retrieves the data from the xml URLs abd append them to the db
	// with dao functions
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao = new Dao();
		try {
			List<Violations> violations = new ArrayList<>();
			violations = dao.get_response();
//			System.out.println("show: " + violations + '\n');
			if (violations != null && !violations.isEmpty()) {
				// Iterate through the list of capturesA
				for (Violations v : violations) {
					// Print the snapshot timestamp of each capture
//					System.out.println("Time Stamp " + v.getSnapshotTimestamp());
					dao.addViolations(v);
					// Print the attributes of each drone
//					System.out.println(v.getSnapshotTimestamp() + " " + v.getSerialNumber() + " " + v.getModel() + " "
//							+ v.getFirstName() + " " + v.getLastName() + " " + v.getPhoneNumber() + " " + v.getEmail()
//							+ " " + v.getPositionY() + " " + v.getPositionX() + " " + v.getDistance() + " "
//							+ v.getNDZStatus());
				}
//				response.sendRedirect("./jsp/drones.jsp");
				try {
					request.setAttribute("violations", violations);

					RequestDispatcher rd = request.getRequestDispatcher("./jsp/drones.jsp");
					rd.include(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				System.out.println("Error");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.close();
	}
}
