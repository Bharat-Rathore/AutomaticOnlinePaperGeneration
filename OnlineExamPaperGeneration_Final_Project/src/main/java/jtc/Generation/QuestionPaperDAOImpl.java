package jtc.Generation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jtc.Configuration.DBConfig;
import jtc.Questions.Question;

public class QuestionPaperDAOImpl implements QuestionPaperDAO {

	@Override
	public List<Question> showAllQuestions(QuestionPaper paper) throws Exception {
		Connection connection = DBConfig.getConnection();
		String sql = "select * from Questions where Subject = ? AND Semester = ? AND Branch = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, paper.getSubject());
		preparedStatement.setString(2, paper.getSemester());
		preparedStatement.setString(3, paper.getBranch());

		ResultSet resultSet = preparedStatement.executeQuery();
		List<Question> list = new ArrayList<Question>();
		while (resultSet.next()) {
			Question question = new Question();
			question.setQuestion(resultSet.getString(1));
			question.setDifficulty(resultSet.getString(2));
			question.setModule(resultSet.getString(3));
			question.setSubject(resultSet.getString(4));
			question.setSemester(resultSet.getString(5));
			question.setBranch(resultSet.getString(6));
			list.add(question);
		}

		if (list.size() != 0) {
			return list;
		} else {
			return null;
		}

	}

}
