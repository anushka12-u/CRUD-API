package com.example.RESTAPI.service.impl;

import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.RESTAPI.dto.AddStudentDto;
import com.example.RESTAPI.dto.StudentDto;
import com.example.RESTAPI.entity.Student;
import com.example.RESTAPI.repository.StudentRepository;
import com.example.RESTAPI.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.val;
@Service
@RequiredArgsConstructor
public class StudentServiceImplentation implements StudentService{
    private final StudentRepository studentrepo;
    private final ModelMapper modelMapper;
    @Override
    public List<StudentDto> getallstudent() {
        List<Student> li=studentrepo.findAll();
        List<StudentDto> list= li.stream().map(student -> modelMapper.map(student, StudentDto.class)).toList();
       return list;
    }

    @Override
    public StudentDto getstudentbyId(Long id) {
        Student student=studentrepo.findById(id).orElseThrow(()-> new IllegalArgumentException("not found student with thid id"));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto createStudentDto(AddStudentDto addstudentreqdto) {
       Student newstudent=modelMapper.map(addstudentreqdto, Student.class);
        Student student=studentrepo.save(newstudent);
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if(!studentrepo.existsById(id))
        {
            throw new IllegalArgumentException("Student does not exists by id");
        }
        studentrepo.deleteById(id);
    }

    @Override
    public  StudentDto updateStudent(Long id, AddStudentDto aStudentDto) {
        Student student=studentrepo.findById(id).orElseThrow(()-> new IllegalArgumentException("not found student with thid id"));
        modelMapper.map(aStudentDto,student);
        studentrepo.save(student);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Object> updates) {
        Student student=studentrepo.findById(id).orElseThrow(()-> new IllegalArgumentException("not found student with thid id"));
        updates.forEach((field,value)->
    {
        switch (field) {
            case "name":
                student.setName((String) value);
                break;
            case "email":student.setEmail((String) value);
            break;
            default:
                throw new IllegalArgumentException("Field is not supported");
        }
    });
    Student savedStudent=studentrepo.save(student);
    return modelMapper.map(savedStudent,StudentDto.class);
    }
    
}
