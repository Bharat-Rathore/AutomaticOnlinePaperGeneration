package jtc.UserLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jtc.Configuration.DBConfig;

public class UserLoginDAOImpl implements UserLoginDAO {

	@Override
	public boolean UserLogin(UserLogin login) throws Exception {
		Connection connection = DBConfig.getConnection();
		String sql = "select * from Users where email = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, login.getUser_email());
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			if (resultSet.getString(4).equals(login.getUser_password())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
