package jtc.UserRegistration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jtc.Configuration.DBConfig;

public class UseRegistrationDAOImpls implements UserRegistrationDAO {

	// User Registration
	@Override
	public int addUser(User user) throws Exception {
		Connection connection = DBConfig.getConnection();
		String sqls = "select * from Users";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sqls);
		while(resultSet.next()) {
			
			if(resultSet.getString(3).equals(user.getEmail())) {
				return 0;
			}
		}
		String sql = "insert into Users(uname,email,password,contact_number) values (?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, user.getUname());
		preparedStatement.setString(2, user.getEmail());
		preparedStatement.setString(3, user.getPassword());
		preparedStatement.setString(4, user.getContact_Number());
		int a = preparedStatement.executeUpdate();
		if (a == 1) {
			return 1;
		} else {			return -1;
		}
	}

}
