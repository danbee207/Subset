package substa.model;

public class ModelTester {

	static final String DB_URL = "jdbc:mysql://mysql2.cs.stonybrook.edu:3306/danpark";
	// Database credentials
	static final String USER = "danpark";
	static final String PASS = "110142214";
	private static DBManager db;

	public static void main(String[] args) {
		db = new DBManager(DB_URL,USER,PASS);
		Boolean s = db.insertIntoPerson(109104096, "Cao", "Carrie",
				"fegegre", 11219, 718216155, "csgsgre@grg.com", "fgfewgtwg");
			
		if(s)
			System.out.println("insertion success!");
			
			
			
		
		System.out.println("Goodbye!");
	}// end main
}
