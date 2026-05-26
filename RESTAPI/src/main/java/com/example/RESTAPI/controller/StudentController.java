package com.example.RESTAPI.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.example.RESTAPI.dto.AddStudentDto;
import com.example.RESTAPI.dto.StudentDto;
import com.example.RESTAPI.entity.Student;
import com.example.RESTAPI.repository.StudentRepository;
import com.example.RESTAPI.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentservice;
    
    @GetMapping
    public ResponseEntity<List<StudentDto>> getstudent(){
        return ResponseEntity.ok(studentservice.getallstudent());
    }
  @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getstudentbyId(@PathVariable long id){
        return ResponseEntity.ok(studentservice.getstudentbyId(id));
    }
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody AddStudentDto addstudentreqdto ){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentservice.createStudentDto(addstudentreqdto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id)
    {
        studentservice.deleteStudentById(id);
        return ResponseEntity.noContent().build();
}
@PutMapping("/{id}")
public ResponseEntity<StudentDto> update(@PathVariable Long id,@RequestBody AddStudentDto aStudentDto)
{
  return ResponseEntity.ok(studentservice.updateStudent(id,aStudentDto));
}
   @PatchMapping("/{id}")
   public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable Long id,@RequestBody Map<String,Object> updates)
{
  return ResponseEntity.ok(studentservice.updatePartialStudent(id,updates));
}
}
