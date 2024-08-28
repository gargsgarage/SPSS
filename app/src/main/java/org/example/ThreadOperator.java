package org.example;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/*This class is the class of the thread objects, which allows the readSubmissionsConcurrentlty() 
 * method in the SPSS class to concurrently process the multiple files in the parameter of that 
 * method. The threadsOperator objects are created, and when the start() method is called, the 
 * run() method is called, which calls another method to process the data in a singular file
 * into the SPSS object.*/
public class ThreadOperator extends Thread {
	
	private SPSS server;
	private String fileName;
	
	//the constructor takes in the SPSS object and the file it needs to read
	public ThreadOperator(SPSS s, String f) {
		server = s;
		fileName = f;
	}
	
	//this method is called by the start() method, which is called by the ThreadOperator
	//object in the SPSS class. It calls processSubmissionsFromFile(), which reads
	//the file's contents and processes them into the SPSS object.
	@Override
	public void run() {
		processSubmissionsFromFile(fileName);
	}
	
	 /*This method is a helper method for the method above. It simplifies
     * the process of reading in and creating a submission for each line of
     * each file. It does this by using a scanner to extract the name, along with
     * each result the student had. Then, it puts all the results in an ArrayList, 
     * and calls the addSubmission method to make the submission.*/
    public void processSubmissionsFromFile(String file) {
    	if (file != null && !file.equals("")) {
			try {
				
				//read the file
				File f = new File(file);
    			Scanner sc = new Scanner(f);
    			
    			while (sc.hasNextLine()) {
    				//extract the data from each line of each file
    				String data = sc.nextLine();
    				data = data.trim();
    				
    				//extract the name from the line
    				String name = data.substring(0, data.indexOf(" "));
    				data = data.substring(data.indexOf(" ")+1);
    				
    				//extract the submission from the line
    				List<Integer>submission = new ArrayList<Integer>();
    				
    				//scan each test result and add it to the ArrayList
    				Scanner sc1 = new Scanner(data);
    				while (sc1.hasNext()) {
    					if (sc1.hasNextInt()) {
    						int score = sc1.nextInt();
    						submission.add(score);
    					}
    					else {
    						sc1.next();
    					}
    				}
    				
    				//add the submission, make it synchronized 
    				synchronized(server){
        				server.addSubmission(name, submission);

    				}
                    sc1.close();
    				
    			}
                sc.close();
    			
			}
			//if the file isn't found, throw the exception
			catch(FileNotFoundException e) {
				System.out.println("file not found: " + file);
			}
		}
    }
}

