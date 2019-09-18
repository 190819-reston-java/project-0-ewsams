import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BankingCustomerDAO {

	/**
	 * Return a player found via their id, or null if no player is found.
	 * 
	 * This is a good example of how your DAO methods should actually look
	 */
	@Override
	public BankingCustomer getBankingCustomer(long id) {
		BankingCustomer player = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM players WHERE id = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, id);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next()) {
							player = createBankingCustomerFromRS(resultSet);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return player;
	}

	/**
	 * Return a player found via their name, or null if no player is found
	 * 
	 * Uses both try-with-resources and StreamCloser for demo reasons
	 */
	@Override
	public BankingCustomer getBankingCustomer(String name) {
		ResultSet resultSet = null;
		//PreparedStatements are better than simple ones
		PreparedStatement statement =  null;
		
		BankingCustomer player = null;
		
		//try-with-resources:
		// any resource that is AutoClosable (an interface)
		// can be used with try (resource) {} and it will close itself
		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement(
					"SELECT * FROM players WHERE name = ?;");
			//in our PreparedStatement, we set
			// values to be filled in later with ?
			// We'll set those values using the "index" 
			// of the ?, starting at 1.
			
			//fill in the ? with name argument
			statement.setString(1, name);
			
			//try to execute SQL query
			if(statement.execute()) {
				//get the ResultSet
				resultSet =  statement.getResultSet();
				//check for a single row and use it
				if(resultSet.next()) {
					player = createBankingCustomerFromRS(resultSet);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(resultSet);
			StreamCloser.close(statement);
		}
		
		return player;
	}

	/**
	 * Returns a list of all players in the players table
	 * 
	 * Written with simple statements to demo them.
	 */
	@Override
	public List<BankingCustomer> getBankingCustomers() {
		// Statement and ResultSet (and Connection) interfaces
		Statement statement = null;
		ResultSet resultSet = null;
		Connection conn = null;

		// List to return
		List<BankingCustomer> players = new ArrayList<BankingCustomer>();

		try {
			// get connection from ConnectionUtil:
			conn = ConnectionUtil.getConnection();

			// create statement from connection
			statement = conn.createStatement();

			// Statements can execute sql queries:
			// ResultSet stores the results of a query
			resultSet = statement.executeQuery("SELECT * FROM players;");

			// loop through ResultSet
			while (resultSet.next()) {
				// At each row in the ResultSet, do the following:
				players.add(createBankingCustomerFromRS(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close all open resources to prevent memory leak
			StreamCloser.close(resultSet);
			StreamCloser.close(statement);
			StreamCloser.close(conn);
		}

		return players;
	}

	/**
	 * Creates a new record in the players table from argument p.
	 * Note that id is generated, so p.id isn't used.
	 */
	@Override
	public boolean createBankingCustomer(BankingCustomer p) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String query = "INSERT INTO players VALUES (DEFAULT, ?, ?, ?, ?);";
		
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, p.getName());
			stmt.setLong(2, p.getNum());
			stmt.setString(3, p.getPosition());
			stmt.setDouble(4, p.getBattingAverage());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(stmt);
			StreamCloser.close(conn);
		}
		
		return true;
	}

	/**
	 * Updates information in the players table using the fields on
	 * argument p.  The id determines which record to update.
	 */
	@Override
	public boolean updateBankingCustomer(BankingCustomer p) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		final String query = "UPDATE players SET name=?, num=?, position=?," + 
		" batting_average=? WHERE id = ?;";
		
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, p.getName());
			stmt.setLong(2, p.getNum());
			stmt.setString(3, p.getPosition());
			stmt.setDouble(4, p.getBattingAverage());
			stmt.setLong(5, p.getId());
			
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(stmt);
			StreamCloser.close(conn);
		}
		
		return true;
	}

	/**
	 * Returns a BankingCustomer object created using a single valid ResultSet row
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private BankingCustomer createBankingCustomerFromRS(ResultSet resultSet) throws SQLException {
		return new BankingCustomer(
				resultSet.getString("firstName"),
				resultSet.getString("lastName"),
				resultSet.getString("email"),
				resultSet.getString("sSN"),
				resultSet.getString("password"), null);
	}
	

}
