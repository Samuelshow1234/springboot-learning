package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {

		return runner ->{
			// createStudent(studentDAO);

			createMultipleStudents(studentDAO);

			//readStudent(studentDAO);

			//queryForStudents(studentDAO);

			//queryForStudentByLastName(studentDAO);

			//updateStudent(studentDAO);

			//deleteStudent (studentDAO);

			//deleteAllStudent(studentDAO);
		};
	}

	private void deleteAllStudent(StudentDAO studentDAO) {

		System.out.println("Deleting all students");

		int numRowsDeleted = studentDAO.deleteAll();

		System.out.println("The total number of students deleted are " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {

		// delete the student

		int studentId=3;

		System.out.println("Deleting student id " + studentId);

		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {

		// retrieve student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student id: " + studentId);

		Student myStudent = studentDAO.findById(studentId);

		System.out.println("Updating student...");

		// change the first name to "Sam"
		myStudent.setFirstName("Sam");
		studentDAO.update(myStudent);

		// display updated student
		System.out.println("Updated student: " + myStudent);

	}

	private void queryForStudentByLastName(StudentDAO studentDAO) {

		// get a list of students

		List<Student> theStudents = studentDAO.findByLastName("Showunmi");

		// display list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {

		// get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// display list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {

		// create a student object

		System.out.println("Creating new student object");
		Student tempStudent = new Student("Show", "Same", "ayoshows482@gmail.com");

		// save the student
		System.out.println("Saving the student object");
		studentDAO.save(tempStudent);

		// display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// retrieve student based on the id primary key
		System.out.println("Retrieving the student with the id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		// display student
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		// create multiple students
		System.out.println("Creating 3 new student object");
		Student tempStudent1 = new Student("Samuel", "Showunmi", "showunmiayomide4@gmail.com");
		Student tempStudent2 = new Student("Israel", "Ogunwuyi", "israelogunwuyi@gmail.com");
		Student tempStudent3 = new Student("Esther", "Adewuyi", "estheradewuyi@gmail.com");

		// save the student object

		System.out.println("Saving the students");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object

		System.out.println("Creating new student object");
		Student tempStudent = new Student("Samuel", "Showunmi", "showunmiayomide4@gmail.com");

		// save the student object

		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display the id of the saved student
		System.out.println("Saved student. Generated is: "  + tempStudent.getId());
	}

}









