package jtc.Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jtc.Generation.ChooseQuestion;
import jtc.Generation.QuestionPaper;
import jtc.Generation.QuestionPaperDAO;
import jtc.Generation.QuestionPaperDAOImpl;
import jtc.Generation.SelectedQuestion;
import jtc.Questions.Question;
import jtc.Questions.QuestionDAO;
import jtc.Questions.QuestionDAOImple;
import jtc.UserLogin.UserLogin;
import jtc.UserLogin.UserLoginDAO;
import jtc.UserLogin.UserLoginDAOImpl;
import jtc.UserRegistration.UseRegistrationDAOImpls;
import jtc.UserRegistration.User;
import jtc.UserRegistration.UserRegistrationDAO;

@WebServlet("/UserController")
public class MainController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("actionRequest");    //actionRequest => user-Login
//		System.out.println(action);      // actionRequest => user-Login
		if (action.equals("user-registration")) {

			User user = new User();
			user.setUname(request.getParameter("uname"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setContact_Number(request.getParameter("contactno"));

			UserRegistrationDAO dao = new UseRegistrationDAOImpls();
			try {
				int status = dao.addUser(user);
				if (status == 1) {
					// Forward control as per requirement
					RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
					rs.forward(request, response); 
				} else if (status == 0) {

					request.setAttribute("err_msg", "THIS MAIL ID IS ALREADY IN USE.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
					dispatcher.forward(request, response);
				} else {

					request.setAttribute("err_msg", "Sorry There is a Technical Glitch CONTACT TO ADMIN");
					RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("user-login")) {
			UserLogin login = new UserLogin();		//Java class => UserLogin
			login.setUser_email(request.getParameter("email"));
			login.setUser_password(request.getParameter("password"));

			UserLoginDAO dao = new UserLoginDAOImpl();		//Java class => UserLoginDAO

			try {
				if (dao.UserLogin(login)) {
					System.out.println("Login Sucess");
					HttpSession session = request.getSession();
					session.setAttribute("Name", request.getParameter("email"));
					RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
					dispatcher.forward(request, response);
				} else {

					request.setAttribute("Err_Msg", "YOUR USERNAME OR PASSWORD ARE INCORRECT.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("addQuestion")) {
			Question question = new Question();			//Java class => Question
			question.setQuestion(request.getParameter("question"));
			question.setDifficulty(request.getParameter("doq"));
			question.setModule(request.getParameter("module"));
			question.setSubject(request.getParameter("subject"));
			question.setSemester(request.getParameter("semester"));
			question.setBranch(request.getParameter("branch"));
			try {
				QuestionDAO dao = new QuestionDAOImple();
				if (dao.addQuestion(question)) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("addQuestion.jsp");
					dispatcher.forward(request, response);
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("Err_Msg", "Sorry There is a Technical Glitch CONTACT TO ADMIN");
					RequestDispatcher dispatcher = request.getRequestDispatcher("addQuestion.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("showQuestion")) {
			Question question = new Question();
			question.setBranch(request.getParameter("branch"));
			question.setSemester(request.getParameter("semester"));
			question.setSubject(request.getParameter("subject"));

			QuestionDAO dao = new QuestionDAOImple();
			try {
				List<Question> ques = dao.getQuestion(question);
				if (ques == null) {

					request.setAttribute("Err_Msg", "QUESTIONS NOT FOUND.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("viewQuestion.jsp");
					dispatcher.forward(request, response);
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("Questions", ques);
					RequestDispatcher dispatcher = request.getRequestDispatcher("viewQuestion.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("deleteQuestion")) {
			Question question = new Question();
			question.setQuestion(request.getParameter("question"));
			QuestionDAO dao = new QuestionDAOImple();
			try {
				dao.deleteQuestion(question);
				request.setAttribute("info", "QUESTION DELETED");
				RequestDispatcher dispatcher = request.getRequestDispatcher("delete_question.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("createPaper")) {
			QuestionPaper paper = new QuestionPaper();
			paper.setNameOfInstitution(request.getParameter("institute"));
			paper.setBranch(request.getParameter("branch"));
			paper.setSemester(request.getParameter("semester"));
			paper.setSubject(request.getParameter("subject"));
			paper.setQuestionPaperCode(request.getParameter("paperCode"));
			paper.setExamTime(request.getParameter("time"));
			paper.setExamDate(request.getParameter("date"));

			QuestionPaperDAO paperDAO = new QuestionPaperDAOImpl();
			try {
				List<Question> list = paperDAO.showAllQuestions(paper);

				if (list == null) {
					request.setAttribute("info", "QUESTION NOT FOUND");
					RequestDispatcher dispatcher = request.getRequestDispatcher("selectExamQuestion.jsp");
					dispatcher.forward(request, response);
				} else {
					ChooseQuestion chooseQuestion = new ChooseQuestion();
					chooseQuestion.setNameOfInstitution(request.getParameter("institute"));
					chooseQuestion.setBranch(request.getParameter("branch"));
					chooseQuestion.setSemester(request.getParameter("semester"));
					chooseQuestion.setSubject(request.getParameter("subject"));
					chooseQuestion.setQuestionPaperCode(request.getParameter("paperCode"));
					chooseQuestion.setExamTime(request.getParameter("time"));
					chooseQuestion.setExamDate(request.getParameter("date"));
					chooseQuestion.setList(list);

					request.setAttribute("Details", chooseQuestion);
					RequestDispatcher dispatcher = request.getRequestDispatcher("selectExamQuestion.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("exam_question_selected")) {
			SelectedQuestion question = new SelectedQuestion();
			question.setNameOfInstitution(request.getParameter("institute"));
			question.setBranch(request.getParameter("branch"));
			question.setSemester(request.getParameter("semester"));
			question.setSubject(request.getParameter("subject"));
			question.setQuestionPaperCode(request.getParameter("papercode"));
			question.setExamTime(request.getParameter("time"));
			question.setExamDate(request.getParameter("date"));

			String easy[] = request.getParameterValues("easy_question");
			List<String> easyQuestion = Arrays.asList(easy);
			question.setEasy(easyQuestion);

			String medium[] = request.getParameterValues("medium_question");
			List<String> mediumQuestion = Arrays.asList(medium);
			question.setMedium(mediumQuestion);

			String difficult[] = request.getParameterValues("defficult_question");
			List<String> difficultQuestion = Arrays.asList(difficult);
			question.setDifficulty(difficultQuestion);
			request.setAttribute("questions_Details", question);
			RequestDispatcher dispatcher = request.getRequestDispatcher("paper.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			RequestDispatcher dispatcher =request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			
		}
	}
}
