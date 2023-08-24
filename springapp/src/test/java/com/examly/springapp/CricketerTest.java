package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.examly.springapp.configurations.ApplSecurityConfig;
import com.examly.springapp.entities.Player;
import com.examly.springapp.entities.Team;

@SpringBootTest
@AutoConfigureMockMvc
public class CricketerTest {
	
	private void assertFieldExists(Class<?> clazz, String fieldName) {
		try {
			Field field = clazz.getDeclaredField(fieldName);
			assertNotNull(field);
		} catch (NoSuchFieldException e) {
			fail("Field '" + fieldName + "' does not exist in the " + clazz.getSimpleName() + " class.");
		}
	}

	@Test
	public void Week1_Day1_testFieldExistence() {
		Class<Cricketer> personClass = Cricketer.class;

		assertFieldExists(personClass, "name");
		assertFieldExists(personClass, "age");
		assertFieldExists(personClass, "country");
	}

	@Test
	void Week1_Day1_testCricketerNameGetterAndSetter() {

		Cricketer cricketer = new Cricketer();

		cricketer.setName("Virat Kohli");

		assertEquals("Virat Kohli", cricketer.getName());

	}

	@Test

	void Week1_Day1_testCricketerAgeGetterAndSetter() {

		Cricketer cricketer = new Cricketer();

		cricketer.setAge(25);

		assertEquals(25, cricketer.getAge());

	}

	@Test
	void Week1_Day1_testCricketerCountryGetterAndSetter() {

		Cricketer cricketer = new Cricketer();

		cricketer.setCountry("India");

		assertEquals("India", cricketer.getCountry());

	}

	@Test
	public void Week1_Day2_Clientinterface() {

		String filePath = "src/main/java/com/examly/springapp/Client.java";

		File file = new File(filePath);

		assertTrue(file.exists() && file.isFile());

	}

	@Test

	public void Week1_Day2_serviceinterface() {

		String filePath = "src/main/java/com/examly/springapp/Service.java";

		File file = new File(filePath);

		assertTrue(file.exists() && file.isFile());

	}

	@Test
	public void Week1_Day3_testAddCricketers() {
		ArrayList<Cricketer> cricketersList = new ArrayList<>();

		cricketersList.add(new Cricketer("Virat Kohli", 32, "India"));
		cricketersList.add(new Cricketer("Steve Smith", 31, "Australia"));

		assertEquals(2, cricketersList.size(), "Number of cricketers added should be 2");
	}

	@Test
	public void Week1_Day4_testSortCricketersByName() {
		ArrayList<Cricketer> cricketersList = new ArrayList<>();
		cricketersList.add(new Cricketer("Virat Kohli", 32, "India"));
		cricketersList.add(new Cricketer("Steve Smith", 31, "Australia"));
		cricketersList.add(new Cricketer("Kane Williamson", 30, "New Zealand"));

		Collections.sort(cricketersList);

		assertEquals("Kane Williamson", cricketersList.get(0).getName(), "First cricketer should be Kane Williamson");
		assertEquals("Steve Smith", cricketersList.get(1).getName(), "Second cricketer should be Steve Smith");
		assertEquals("Virat Kohli", cricketersList.get(2).getName(), "Third cricketer should be Virat Kohli");
	}

	@Test
	public void Week1_Day4_testSortCricketersByAge() {
		ArrayList<Cricketer> cricketersList = new ArrayList<>();
		cricketersList.add(new Cricketer("Virat Kohli", 32, "India"));
		cricketersList.add(new Cricketer("Steve Smith", 31, "Australia"));
		cricketersList.add(new Cricketer("Kane Williamson", 30, "New Zealand"));

		Collections.sort(cricketersList, new CricketerAgeComparator());

		assertEquals("Kane Williamson", cricketersList.get(0).getName(),
				"Youngest cricketer should be Kane Williamson");
		assertEquals("Steve Smith", cricketersList.get(1).getName(), "Second youngest cricketer should be Steve Smith");
		assertEquals("Virat Kohli", cricketersList.get(2).getName(), "Oldest cricketer should be Virat Kohli");
	}

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/appdb";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "examly";

	private Connection connection;

	@Test
	public void Week1_Day5_testConnection() throws Exception {
		connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		assertNotNull(connection);
		connection.close();
	}

	@After
	public void tearDown() throws Exception {
		if (connection != null) {
			connection.close();
		}
	}

	@Test
	public void Week1_Day5_testRetrieveData() throws SQLException {
		connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		String query = "SELECT * FROM cricketer";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();

			// Assuming that the ResultSet contains multiple rows, loop through them
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String country = resultSet.getString("country");

				assertNotNull(name);
				assertNotNull(age); // Update the age threshold as needed
				assertNotNull(country);
				// System.out.println("Display the cricketername"+name);
			}
		}
	}

	@Test
	public void Week1_Day6_JDBCUtils() {

		String filePath = "src/main/java/com/examly/springapp/JDBCUtils.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	@Test
	public void Week1_Day6_testJDBCUtils() {
		Class<JDBCUtils> jdbc = JDBCUtils.class;

		assertFieldExists(jdbc, "URL");
		assertFieldExists(jdbc, "USERNAME");
		assertFieldExists(jdbc, "PASSWORD");

		try {
			Method method1 = jdbc.getDeclaredMethod("getConnection");
			Method method2 = jdbc.getDeclaredMethod("closeConnection", Connection.class);

		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void Week1_Day6_CricketerDAO() {

		String filePath = "src/main/java/com/examly/springapp/CricketDAO.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
		Class<CricketDAO> dao = CricketDAO.class;

		try {
			Method method1 = dao.getDeclaredMethod("getAllCricketer");
			Method method2 = dao.getDeclaredMethod("addCricketer",Cricketer.class);

		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	@Test
    public void Week2_Day1_testRetrieveDataFromTables() throws SQLException {

		connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

        String query = "SELECT c.name AS cricketer_name, c.country AS cricketer_country,t.name AS team_name, t.country AS team_country FROM cricketer c INNER JOIN teams t ON c.country = t.country";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            // Assuming that the ResultSet contains multiple rows, loop through them
            while (resultSet.next()) {
                String name = resultSet.getString("cricketer_name");
                String country = resultSet.getString("cricketer_country");
                String teamname = resultSet.getString("team_name");
                String teamcountry = resultSet.getString("team_country");
                assertNotNull(name);
                assertNotNull(country);
                assertNotNull(teamname);
                assertNotNull(teamcountry);
            }
}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Autowired
	private MockMvc mockMvc;

	@Test
	public void Week2_Day2_testWelcome() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/Welcome"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Welcome to SpringProject"));
	}

	@Test
	void Week2_Day3_getAllTeamsList() throws Exception {
		mockMvc.perform(get("/team/getAllList").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andReturn();
	}
	
	
	@Test
	public void Week2_Day4_TeamClass() {

		String filePath = "src/main/java/com/examly/springapp/entities/Team.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
		
		Class<Team> teamclass = Team.class;

		assertFieldExists(teamclass, "id");
		assertFieldExists(teamclass, "name");
		assertFieldExists(teamclass, "maximumBudget");
	}
	
	@Test
	public void Week2_Day4_TeamClassRepo() {

		String filePath = "src/main/java/com/examly/springapp/repositories/TeamRepository.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
	
	@Test
	void Week2_Day5_Teamadd() throws Exception {
		String st = "{\"id\": 1000,\"name\": \"Demo\",\"maximumBudget\":15000}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/teams").contentType(MediaType.APPLICATION_JSON).content(st)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				// .andExpect(MockMvcResultMatchers.content().string("true")) // Correct way to
				// validate boolean response
				.andReturn();
	}

	@Test
	void Week2_Day6_getallTeam() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/teams").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andReturn();
	}
	

	@Test
	void Week3_Day2_playeradd() throws Exception {
		String st = "{\"id\": 1000,\"name\": \"Demo\", \"age\": 24,\"category\": \"Seniorteam\",\"biddingPrice\":15000,\"sold\":false,\"email\": \"Viratdemo@gmail.com\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/players").contentType(MediaType.APPLICATION_JSON).content(st)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				// .andExpect(MockMvcResultMatchers.content().string("true")) // Correct way to
				// validate boolean response
				.andReturn();
	}

	
	@Test
	void Week3_Day2_getallPlayerWithTeam() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/players").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andReturn();
	}

	@Test
	public void Week3_Day3_testFieldExistence() {
		Class<Player> player = Player.class;

		assertFieldExists(player, "team");
	}
	
	@Test
	void Week3_Day3_getallPlayerList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/organizer/player-list").param("teamId", "1000").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andReturn();
	}

	
	@Test
	void Week3_Day4_getallUnsoldPlayer() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/organizer/unsold-players").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andReturn();
	}
	
	@Test
	void Week3_Day4_getallSoldPlayer() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/organizer/sold-players").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andReturn();
	}
	
	
	@Test
	public void Week3_Day5_configfolder() {

		String directoryPath = "src/main/java/com/examly/springapp/configurations"; // Replace with the path to your
		File directory = new File(directoryPath);
		assertTrue(directory.exists() && directory.isDirectory());
	}

	@Test
	public void Week3_Day5_SpringSecurity() {
		String filePath = "src/main/java/com/examly/springapp/configurations/ApplSecurityConfig.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
		Class<ApplSecurityConfig> config = ApplSecurityConfig.class;

		try {
			Method method1 = config.getDeclaredMethod("securityFilterChain",HttpSecurity.class);
			Method method2 = config.getDeclaredMethod("userDetailsService");

		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void Week3_Day6_Springsecurity() throws Exception {
		String st = "{\"username\": \"admin\",\"password\": \"admin123\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
				.content(st).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				// .andExpect(MockMvcResultMatchers.content().string("true")) // Correct way to
				// validate boolean response
				.andReturn();
	}

	@Test
	void Week3_Day6_SpringRegisterUser() throws Exception {
		String st = "{\"id\": 1000,\"username\": \"admin\",\"password\": \"admin123\",\"role\": \"ADMIN\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/register").contentType(MediaType.APPLICATION_JSON).content(st)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated())
				// .andExpect(MockMvcResultMatchers.content().string("true")) // Correct way to
				// validate boolean response
				.andReturn();
	}

	// @Test
	// public void testHelloSpring() throws Exception {
	// mockMvc.perform(MockMvcRequestBuilders.get("myapp/Welcome"))
	// .andExpect(MockMvcResultMatchers.status().isOk())
	// .andExpect(MockMvcResultMatchers.content().string("Welcome to Spring"));
	//  }

}
