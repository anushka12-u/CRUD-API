package com.example.RESTAPI.service;

import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.example.RESTAPI.dto.AddStudentDto;
import com.example.RESTAPI.dto.StudentDto;

public interface StudentService {
    List<StudentDto> getallstudent();
    StudentDto getstudentbyId(Long id);
    StudentDto createStudentDto(AddStudentDto addstudentreqdto);
    void deleteStudentById(Long id);
    @Nullable
    StudentDto updateStudent(Long id, AddStudentDto aStudentDto);
    StudentDto updatePartialStudent(Long id, Map<String,Object> updates);
}
