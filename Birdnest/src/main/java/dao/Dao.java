package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import conn.Connections;
import model.Violations;

public class Dao {

	private static EntityManagerFactory emf;

	/**
	 * Function to connect to database
	 */
	private Connection conn;

	// When new instance is created, also DB-connection is created
	public Dao() {
		try {
			// get the connection to db form the Connections class
			conn = Connections.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Manually close DB-connection for releasing resource
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static EntityManager getEntityManager() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("drone_database");
		}
		return emf.createEntityManager();
	}

	// Getting and parsing data from Xml URLs
	public List<Violations> get_response() {
		// TODO Auto-generated method stub

		List<Violations> violations = new ArrayList<>();

		String currentTimestamp = Instant.now().toString();

		String url = "https://assignments.reaktor.com/birdnest/drones?timestamp=" + currentTimestamp;
		System.out.println("url: " + url);
		Violations v2 = new Violations();
		String snapshotTimestamp = null;

		try {

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			int responseCode = con.getResponseCode();
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// System.out.println(response.toString());
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(response.toString())));

			doc.getDocumentElement().normalize();
			// Getting the time stamp for each drone
			NodeList report = doc.getElementsByTagName("report");
			for (int i = 0; i < report.getLength(); i++) {
				Element device = (Element) report.item(i);
				try {
					Client client = ClientBuilder.newClient();
					Response res = client.target(url).request().get();
					String xmlData = res.readEntity(String.class);
//					System.out.println(xmlData);
					// Use a secure way to parse the XML data
					XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
					xmlInputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
					xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
					XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new StringReader(xmlData));

					// Parse the XML data
					while (xmlStreamReader.hasNext()) {
						int eventType = xmlStreamReader.next();
						if (eventType == XMLStreamReader.START_ELEMENT) {
//						System.out.println("localName: " + xmlStreamReader.getLocalName() + "\n");

							if (xmlStreamReader.getLocalName().equals("capture")) {

								snapshotTimestamp = v2.setSnapshotTimestamp(
										xmlStreamReader.getAttributeValue(null, "snapshotTimestamp"));
//								violations.add(v);
							}
						}
					}
					xmlStreamReader.close();
				} catch (XMLStreamException e) {
					e.printStackTrace();
					return new ArrayList<>();
				}

				// Getting the drones
				NodeList droneNodeList = device.getElementsByTagName("drone");
				for (int j = 0; j < droneNodeList.getLength(); j++) {
					Element droneElement = (Element) droneNodeList.item(j);
					String serialNumber = droneElement.getElementsByTagName("serialNumber").item(0).getTextContent();

					// Construct the URL for the pilot data
					URL url2 = new URL("https://assignments.reaktor.com/birdnest/pilots/" + serialNumber);
					// Open an HTTPS connection to the URL
					HttpsURLConnection con1 = (HttpsURLConnection) url2.openConnection();
					con1.setRequestMethod("GET");

					// Read the response
					BufferedReader in1 = new BufferedReader(new InputStreamReader(con1.getInputStream()));
					String inputLine1;
					StringBuilder content = new StringBuilder();
					while ((inputLine1 = in1.readLine()) != null) {
						content.append(inputLine1);
					}
					in1.close();

					// Parse the JSON response using Jackson
					ObjectMapper mapper = new ObjectMapper();
					JsonNode root = mapper.readTree(content.toString());

					// Extract the pilot data from the JSON object
					String pilotId = root.get("pilotId").asText();
					String firstName = root.get("firstName").asText();
					String lastName = root.get("lastName").asText();
					String phoneNumber = root.get("phoneNumber").asText();
//					String createdDt = root.get("createdDt").asText();
					String email = root.get("email").asText();

					String model = droneElement.getElementsByTagName("model").item(0).getTextContent();
//					String manufacturer = droneElement.getElementsByTagName("manufacturer").item(0).getTextContent();
//					String mac = droneElement.getElementsByTagName("mac").item(0).getTextContent();
//					String ipv4 = droneElement.getElementsByTagName("ipv4").item(0).getTextContent();
//					String ipv6 = droneElement.getElementsByTagName("ipv6").item(0).getTextContent();
//					String firmware = droneElement.getElementsByTagName("firmware").item(0).getTextContent();
					double positionY = Double
							.parseDouble(droneElement.getElementsByTagName("positionY").item(0).getTextContent());
					double positionX = Double
							.parseDouble(droneElement.getElementsByTagName("positionX").item(0).getTextContent());
//					double altitude = Double
//							.parseDouble(droneElement.getElementsByTagName("altitude").item(0).getTextContent());

					double distance = v2.setDistance2(positionY, positionX);
					System.out.println("Checking Distance: " + distance + " " + firstName + " " + lastName + "\n");

					Violations v = new Violations();

					// violating drones
					if (distance <= 100) {
						String NDZStatus = v.setNDZStatus1("Violating!");

						v.setSnapshotTimestamp(snapshotTimestamp);
						v.setSerialNumber(serialNumber);
						v.setModel(model);
						v.setPositionY(positionY);
						v.setPositionX(positionX);
						v.setDistance(distance);
						v.setPilotId(pilotId);
						v.setFirstName(firstName);
						v.setLastName(lastName);
						v.setPhoneNumber(phoneNumber);
						v.setEmail(email);
						v.setNDZStatus(NDZStatus);
						violations.add(v);
					} else {
						String NDZStatus = v.setNDZStatus2("Not Violating!");

						v.setSnapshotTimestamp(snapshotTimestamp);
						v.setSerialNumber(serialNumber);
						v.setModel(model);
						v.setPositionY(positionY);
						v.setPositionX(positionX);
						v.setDistance(distance);
						v.setPilotId(pilotId);
						v.setFirstName(firstName);
						v.setLastName(lastName);
						v.setPhoneNumber(phoneNumber);
						v.setEmail(email);
						v.setNDZStatus(NDZStatus);
						violations.add(v);
					}

				}

			}

		} catch (Exception e) {
			System.out.println(e);
		}
//		System.out.println("testi " +violations +"\n");
		return violations;
	}

	// Add data to the db
	public void addViolations(Violations v) throws ParseException {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		Dao dao = new Dao();

		dao.deleteOldRecords();

		em.getTransaction().begin();
		em.persist(v);
		em.getTransaction().commit();
		// print for test purposes
		System.out.println(v);

		// delete non violating drones
		if (v.getDistance() > 100) {
			dao.deleteNonViolators(v);

		}
		dao.close();
		em.close();
	}

	public void deleteNonViolators(Violations v) {
		// TODO Auto-generated method stub

		int violations_id = v.getViolations_id();
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		v = em.find(Violations.class, violations_id);
		if (v != null) {
			em.remove(v);
		}
		em.getTransaction().commit();
		em.close();

	}

	public void deleteOldRecords() throws ParseException {
		// Calculate the time 10 minutes ago
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -10);
		Timestamp time10MinutesAgo = new Timestamp(cal.getTimeInMillis());

		// Adjust the snapshotTimestamp to be in the same timezone as time10MinutesAgo
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(time10MinutesAgo);
		cal2.add(Calendar.HOUR, -2);
		Timestamp adjustedTime = new Timestamp(cal2.getTimeInMillis());
		System.out.println("adjustedTime: " + adjustedTime);// for testing
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		String time10MinutesAgoString = sdf.format(adjustedTime);
//		System.out.println("adjustedTime: " + time10MinutesAgoString);// for testing
		// Delete all records with a snapshotTimestamp earlier than 10 minutes ago
		String query = "DELETE FROM violations WHERE snapshotTimestamp < ?";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, time10MinutesAgoString);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void keepSmallestDistance(Violations v) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		// Find the smallest distance for the pilot with the given ID
		String queryString = "SELECT MIN(v.distance) FROM Violations v WHERE v.pilotId = :pilotId AND v.firstName = :firstName AND v.lastName = :lastName";
		TypedQuery<Double> query = em.createQuery(queryString, Double.class);
		query.setParameter("pilotId", v.getPilotId());
		query.setParameter("firstName", v.getFirstName());
		query.setParameter("lastName", v.getLastName());
		Double smallestDistance = query.getSingleResult();

		// Delete all rows for the given pilot with a distance other than the smallest
		// distance or with the same smallest distance as another row for the same pilot
		queryString = "DELETE FROM Violations v WHERE  v.pilotId = :pilotId AND v.firstName = :firstName AND v.lastName = :lastName AND (v.distance > :smallestDistance OR (v.distance = :smallestDistance AND v.violations_id NOT IN (SELECT MIN(vv.violations_id) FROM Violations vv WHERE vv.pilotId = :pilotId AND vv.firstName = :firstName AND vv.lastName = :lastName AND vv.distance = :smallestDistance)))";
		Query deleteQuery = em.createQuery(queryString);
		deleteQuery.setParameter("pilotId", v.getPilotId());
		deleteQuery.setParameter("firstName", v.getFirstName());
		deleteQuery.setParameter("lastName", v.getLastName());
		deleteQuery.setParameter("smallestDistance", smallestDistance);
		deleteQuery.executeUpdate();

		em.getTransaction().commit();
		em.close();
	}

	// reading and keeping the closest distance only
	public List<Violations> readAllTheData() {
		// TODO Auto-generated method stub

		List<Violations> violations = new ArrayList<>();
		String sql = "SELECT * FROM violations ORDER BY snapshotTimestamp";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setString(1, pilotId);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {

				// Set the attributes of the Violations object
				int id = resultSet.getInt("violations_id");
				String snapshotTimestamp = resultSet.getString("snapshotTimestamp");
				String serialNumber = resultSet.getString("serialNumber");
				String model = resultSet.getString("model");
				double positionY = resultSet.getDouble("positionY");
				double positionX = resultSet.getDouble("positionX");
				double distance = resultSet.getDouble("distance");
				String pilotId = resultSet.getString("pilotId");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String phoneNumber = resultSet.getString("phoneNumber");
				String email = resultSet.getString("email");
				String NDZStatus = resultSet.getString("NDZStatus");

				Violations violation = new Violations(id, snapshotTimestamp, serialNumber, model, positionY, positionX,
						distance, pilotId, firstName, lastName, phoneNumber, email, NDZStatus);

				keepSmallestDistance(violation);
				// Add the Violations object to the list
				violations.add(violation);
				// Close the ResultSet, Prepared Statement, and Connection objects

			}
			resultSet.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return violations;
	}

	// Reading all the data with the closest distance and only show the closest distance
	public List<Violations> readAllData() throws Exception {
		List<Violations> violations = new ArrayList<>();

		readAllTheData();
		// Reading the final data
		String query = "SELECT * FROM violations ORDER BY snapshotTimestamp";
//		String sql = "SELECT * FROM violations WHERE distance = (SELECT MIN(distance) FROM violations WHERE pilotId = ?) ORDER BY snapshotTimestamp";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
//			stmt.setString(1, pilotId);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {

				// Set the attributes of the Violations object
				int id = resultSet.getInt("violations_id");
				String snapshotTimestamp1 = resultSet.getString("snapshotTimestamp");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				Date snapshotDate = df.parse(snapshotTimestamp1);
				// Adjust the snapshotTimestamp to be in the same timezone as in Helsinki
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(snapshotDate);
				cal2.add(Calendar.HOUR, +2);
				Timestamp adjustedTime = new Timestamp(cal2.getTimeInMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy, hh:mm:ss a");
				String snapshotTimestamp = sdf.format(adjustedTime);
				String serialNumber = resultSet.getString("serialNumber");
				String model = resultSet.getString("model");
				double positionY = resultSet.getDouble("positionY");
				double positionX = resultSet.getDouble("positionX");
				double distance = resultSet.getDouble("distance");
				distance = Math.round(distance * 100.0) / 100.0; // round to two decimals
				String pilotId = resultSet.getString("pilotId");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String phoneNumber = resultSet.getString("phoneNumber");
				String email = resultSet.getString("email");
				String NDZStatus = resultSet.getString("NDZStatus");

				Violations violation = new Violations(id, snapshotTimestamp, serialNumber, model, positionY, positionX,
						distance, pilotId, firstName, lastName, phoneNumber, email, NDZStatus);
				// Add the Violations object to the list
				violations.add(violation);

			}
			// Close the ResultSet, Prepared Statement, and Connection objects
			resultSet.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("test Final: " + violations);
		return violations;
	}

}
