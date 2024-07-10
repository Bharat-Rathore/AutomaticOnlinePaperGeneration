package jtc.Questions;

import java.util.List;

public interface QuestionDAO {
	public boolean addQuestion(Question question) throws Exception;

	public List<Question> getQuestion(Question question) throws Exception;

	public void deleteQuestion(Question question) throws Exception;
}
