package com.example.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import com.example.tutorial3.model.StudentModel;

public class InMemoryStudentService implements StudentService{
	
	public static List<StudentModel> studentList = new ArrayList<>();

	@Override
	public StudentModel selectStudent(String npm) {
		StudentModel student = new StudentModel();
		student.setNpm(npm);
		int indexOfStudent = studentList.indexOf(student);
		if(indexOfStudent != -1)student = studentList.get(indexOfStudent); 
		else student = null;
		return student;
	}

	@Override
	public List<StudentModel> selectAllStudents() {
		return studentList;
	}

	@Override
	public void addStudent(StudentModel student) {
		studentList.add(student);		
	}

	@Override
	public StudentModel deleteStudent(String npm) {
		StudentModel student = selectStudent(npm);
		if(student != null && studentList.remove(student)) {
			return student;
		}
		return null;
	}

}
