package com.example.Application.Repository;

import com.example.Application.Entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<CourseEntity, Long> {
}
