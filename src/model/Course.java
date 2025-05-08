package model;

import customExceptions.GradesExceedException;
import customExceptions.OutOfRangeGradeException;
import customExceptions.QuotaEnrollExceedException;
import customExceptions.WeeksExceedException;
import customExceptions.ExistStudentException;
import customExceptions.NoExistStudentsException;
import customExceptions.DiferentException;

public class Course {
	private double maxGrade;
	private double minGrade;
	private int currentWeek;
	private int totalGradesAmount;
	private int maxQuota;

	private Student[] studentsEnrolled;

	public Course(int tga, double mx, double mn, int mq) throws DiferentException {
		if (mn > mx){
			throw new DiferentException(mn, mx);
		}
		currentWeek = 1;
		maxGrade = mx;
		minGrade = mn;
		totalGradesAmount = tga;
		maxQuota = mq;

		studentsEnrolled = new Student[mq];
	}

	public void enroll(String id) throws QuotaEnrollExceedException, WeeksExceedException, ExistStudentException {
		int posNewStudent = searchFirstAvailable();
		int existStudent = searchStudent(id);
		if (currentWeek > 2){
			throw new WeeksExceedException(currentWeek);
		} else {
			if (posNewStudent == -1) {
				throw new QuotaEnrollExceedException(maxQuota);
			} else {
				if (existStudent == -1){
					studentsEnrolled[posNewStudent] = new Student(id, totalGradesAmount);
				} else {
					throw new ExistStudentException(id);
				}
			}
		}
	}

	public void cancelEnrollment(String id) throws GradesExceedException {
		int posStudent = searchStudent(id);
		int contGrade = 0;
		for (int i = 1; i < totalGradesAmount+1; i++){
			if (studentsEnrolled[posStudent].getGrade(i) != 0.0){
				contGrade += 1;
			}
		}
		double porcentageGrade = (contGrade * 100)/totalGradesAmount;
		if (porcentageGrade > 50){
			throw new GradesExceedException(porcentageGrade);
		} else {
			studentsEnrolled[posStudent] = null;
		}
	}

	public void setStudentGrade(String id, int gradeNumber, double grade)
			throws ArrayIndexOutOfBoundsException, OutOfRangeGradeException {
		if (grade < minGrade || grade > maxGrade) {
			throw new OutOfRangeGradeException(grade, maxGrade, minGrade);
		}

		int posStudent = searchStudent(id);
		studentsEnrolled[posStudent].setGrade(gradeNumber, grade);
	}

	public void advanceWeek() {
		currentWeek++;
	}

	private int searchFirstAvailable() {
		int pos = -1;
		for (int i = 0; i < studentsEnrolled.length && pos == -1; i++) {
			Student current = studentsEnrolled[i];
			if (current == null) {
				pos = i;
			}
		}

		return pos;
	}

	private int searchStudent(String id) {
		int pos = -1;

		for (int i = 0; i < studentsEnrolled.length && pos == -1; i++) {
			Student current = studentsEnrolled[i];
			if (current != null) {
				if (id.contentEquals(current.getId())) {
					pos = i;
				}
			}
		}

		return pos;
	}

	public String showEnrolledStudents() throws NoExistStudentsException{

		String msg = "";
		int cont = 0;
		for (int i = 0; i < studentsEnrolled.length; i++) {

			if (studentsEnrolled[i] != null) {

				msg += studentsEnrolled[i].getId()+"\n";
			} else {
				cont += 1;
			}

		}
		if (cont == studentsEnrolled.length){
			throw new NoExistStudentsException();
		}

		return msg;

	}

	public String showStudentGrades(String id) {

		int pos = searchStudent(id);

		if (pos != -1) {

			return studentsEnrolled[pos].showGrades();

		}

		return "Error";

	}
}
