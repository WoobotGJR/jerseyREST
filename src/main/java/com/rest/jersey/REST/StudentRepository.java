package com.rest.jersey.REST;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class StudentRepository {
	List<Student> students = new ArrayList<>();

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

	public StudentRepository() {
		try (EntityManager em = emf.createEntityManager()) {
			TypedQuery<Student> query = em.createQuery("select s from Student s", Student.class);
			students = query.getResultList();
		}
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public Student get(int ID) {
		Student st = null;
		try (EntityManager em = emf.createEntityManager()) {
			TypedQuery<Student> query = em.createQuery("select s from Student s where s.studID = :id", Student.class);
			query.setParameter("id", ID);
			st = query.getSingleResult();
		} catch (Exception err) {
			System.out.println(err);
		}
		
		return st;
	}

	public void create(Student student) {
		try (EntityManager em = emf.createEntityManager()) {
			em.getTransaction().begin();
			em.persist(student); // in body with this method you shouldnt set studID, cuz its AUTO_INCREMENT
			em.getTransaction().commit();
		}
	}
	
	public Student update(int ID, Student updatedStudent) {
		Student st = null;
		try (EntityManager em = emf.createEntityManager()) {
			em.getTransaction().begin();
	        st = em.find(Student.class, ID); // Находим студента по ID
	        if (st != null) {
	            // Обновляем данные студента
	            st.setAvgGrade(updatedStudent.getAvgGrade());
	            st.setStudName(updatedStudent.getStudName());
	            // Сохраняем изменения в базе данных
	            em.merge(st);
	            em.getTransaction().commit();
	        }
		} catch (Exception err) {
			System.out.println(err);
		}
		
		return st;
	}
	
	public Student delete(int ID) {
		Student st = null;
		
		try (EntityManager em = emf.createEntityManager()) {
			em.getTransaction().begin();
	        st = em.find(Student.class, ID); // Находим студента по ID
	        if (st != null) {
	            // Удаляем данные студента
	            em.remove(st);
	            em.getTransaction().commit();
	        }
		} catch (Exception err) {
			System.out.println(err);
		}
		
		return st;
	}

}
