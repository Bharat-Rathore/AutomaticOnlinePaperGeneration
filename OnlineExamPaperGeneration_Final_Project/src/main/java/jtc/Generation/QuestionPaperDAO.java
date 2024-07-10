package jtc.Generation;

import java.util.List;

import jtc.Questions.Question;

public interface QuestionPaperDAO {
	List<Question> showAllQuestions(QuestionPaper paper) throws Exception;
	
}
