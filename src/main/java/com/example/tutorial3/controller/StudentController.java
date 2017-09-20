package com.example.tutorial3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tutorial3.model.StudentModel;
import com.example.tutorial3.service.InMemoryStudentService;
import com.example.tutorial3.service.StudentService;

@Controller
public class StudentController {
	
	private StudentService studentService;
	
	public StudentController() {
		studentService = new InMemoryStudentService();
	}
	
	@RequestMapping("/student/add")
	public String add(
			@RequestParam(value = "npm", required = true) String npm,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "gpa", required = true) double gpa) {
		StudentModel student = new StudentModel(name, npm, gpa);
		studentService.addStudent(student);
		return "add";
	}
	
	@RequestMapping("/student/view")
	public String view(Model model, @RequestParam(value = "npm" , required = true) String npm) {
		StudentModel student = studentService.selectStudent(npm);
		model.addAttribute("student",student);
		return "view";
	}
	
	@RequestMapping("/student/viewall")
	public String view(Model model) {
		List<StudentModel> students = studentService.selectAllStudents();
		model.addAttribute("students", students);
		return "viewall";
	}
	
	@RequestMapping("/student/view/{npm}")
	public String viewStudent(Model model, @PathVariable(value = "npm", required = true) String npm) {
		StudentModel student = studentService.selectStudent(npm);
		if(student != null) {
			model.addAttribute("student",student);
			return "view";
		}else {
			student = new StudentModel();
			student.setNpm(npm);
			model.addAttribute("student",student);
			return "student_not_found";
		}
	}
	
	@RequestMapping("/student/delete/{npm}")
	public String deleteStudent(Model model, @PathVariable(value = "npm", required = true) String npm) {
		StudentModel student = studentService.deleteStudent(npm);
		if(student != null) {
			model.addAttribute("student",student);
			return "delete";
		}else {
			student = new StudentModel();
			student.setNpm(npm);
			model.addAttribute("student",student);
			return "student_not_found";
		}
	}
	
}
