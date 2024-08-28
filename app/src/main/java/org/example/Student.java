package org.example;


import java.util.List;

/*This class's objects represents Students that would be added to a course's submission server. 
 * The student has a name, number of submissions that they've made, and an array of the tests
 * of their best submission. This class also has a couple methods, some of which can check if 
 * the parameter name equals the current object's name, get the test scores summed up for the 
 * student's best submission, replace the best submission of a student, get the number of 
 * tests the student passes, and much more.*/
public class Student {

	private String name;
	private int currentNumSubmissions = 0;
	private int[] bestSubmission;
	
	public Student(String name, int numTests) {
		this.name = name;
		bestSubmission = new int[numTests];
	}
	
	/*This method checks if the current object's name field is equal to 
	 * the paramater. If it is, then it returns true.*/
	public boolean hasName(String n) {
		//check if name = param, return true if it does
		if (name.equals(n)) {
			return true;
		}
		return false;
	}
	
	/*This method returns the sum of the test scores for the student's best
	 * submsission.*/
	public int getBestTestScore() {
		//sum up the different test scores for the student
		int sum = 0;
		for (int i = 0; i < bestSubmission.length; i++) {
			sum += bestSubmission[i];
		}
		return sum;
	}
	
	/*This method returns the sum of the test scores in @param testResults.*/
	public int getTestScore(List<Integer> testResults) {
		//sum up the different test scores for the param list
		int sum = 0;
		for (int i = 0; i < testResults.size(); i++) {
			sum += testResults.get(i);
		}
		return sum;
	}
	
	/*this method replaces the bestSubmission of the student with
	 * @param testResults. It does this only if the test score of 
	 * @param testResults is greater than the test score of the best
	 * submission.*/
	public void replaceBestSubmission(List<Integer> testResults) {
		//check if the score of the param list is higher than 
		//that of the students current submission
		if (getTestScore(testResults) > getBestTestScore()) {
			for (int i = 0; i < bestSubmission.length; i++) {
				bestSubmission[i] = testResults.get(i);
			}
		}
	}
	
	/*This method returns the number of submissions that the student has made.*/
	public int getNumSubmissions() {
		return currentNumSubmissions;
	}
	
	/*This method increments the number of submissions a student has made.*/
	public void incrementNumSubmissions() {
		currentNumSubmissions++;
	}
	
	/*This method returns the number of tests that the student's best
	 * submission has passed. A tests is passed if the score received is
	 * greater than 0.*/
	public int getNumPassedTests() {
		//count the number of tests in the best submission that are > 0
		int numPassedTests = 0;
		for (int i = 0; i < bestSubmission.length; i++) {
    		if (bestSubmission[i] > 0) {
    			numPassedTests++;
    		}
    	}
		return numPassedTests;
	}
}
