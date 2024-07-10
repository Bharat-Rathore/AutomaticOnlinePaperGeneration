package jtc.Generation;

import java.util.List;

import jtc.Questions.Question;

public class SelectedQuestion {
	private String nameOfInstitution;
	private String branch;
	private String semester;
	private String subject;
	private String questionPaperCode;
	private String examTime;
	private String examDate;
	private List<String> easy;
	private List<String> medium;
	private List<String> difficulty;
	public String getNameOfInstitution() {
		return nameOfInstitution;
	}
	public void setNameOfInstitution(String nameOfInstitution) {
		this.nameOfInstitution = nameOfInstitution;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getQuestionPaperCode() {
		return questionPaperCode;
	}
	public void setQuestionPaperCode(String questionPaperCode) {
		this.questionPaperCode = questionPaperCode;
	}
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public List<String> getEasy() {
		return easy;
	}
	public void setEasy(List<String> easy) {
		this.easy = easy;
	}
	public List<String> getMedium() {
		return medium;
	}
	public void setMedium(List<String> medium) {
		this.medium = medium;
	}
	public List<String> getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(List<String> difficulty) {
		this.difficulty = difficulty;
	}
	
	
	
	
	
}
