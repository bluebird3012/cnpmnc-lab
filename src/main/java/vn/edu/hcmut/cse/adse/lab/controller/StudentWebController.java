package vn.edu.hcmut.cse.adse.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Luu y: su dung @Controller, KHONG dung @RestController
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentWebController {

    @Autowired
    private StudentService service;

    // Route: GET http://localhost:8080/students
    @GetMapping
    public String getAllStudents(Model model) {
        // 1. Lay du lieu tu Service
        List<Student> students = service.getAll();

        // 2. Dong goi du lieu vao "Model" de chuyen sang View
        // Key "dsSinhVien" se duoc su dung ben file HTML
        model.addAttribute("dsSinhVien", students);

        // 3. Tra ve ten cua View (khong can duoi .html)
        // Spring Boot se tu dong tim file tai: src/main/resources/templates/students.html
        return "students";
    }
}
