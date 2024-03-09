package com.rest.jersey.REST;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("students")
public class StudentController {
	private final StudentRepository studRepo = new StudentRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) // depends on Accept = application/JSON or /XML in header
	public List<Student> getStudent() {
		return studRepo.getStudents();
	}
	
	@POST
	@Path("student")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) // depends on Content-Type = application/JSON or /XML in header
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Student createStudent(Student student) {
		System.out.println("in controller");
		studRepo.create(student);
		
		return student;
	}
	
	@GET
	@Path("student/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Student getStudentById(@PathParam("id") int id) {
	    return studRepo.get(id);
	}
	
	@PUT
	@Path("student/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Student updateStudent(@PathParam("id") int id, Student updatedStudent) {
		return studRepo.update(id, updatedStudent);
	}
	
	@DELETE
	@Path("student/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Student deleteStudent(@PathParam("id") int id) {
		return studRepo.delete(id);
	}
}
/*
 * Методы автоматически принимают параметры из тела запроса, если они
 * соответствуют типу данных, указанному в сигнатуре метода и если используется
 * аннотация @Consumes для указания типа контента, который метод может
 * принимать.
 */