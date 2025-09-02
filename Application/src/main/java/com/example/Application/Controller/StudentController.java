import java.awt.List;
import java.util.HashSet;
import java.util.SequencedCollection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Application.Entity.CourseEntity;
import com.example.Application.Entity.StudentEntity;
import com.example.Application.Repository.CourseRepo;
import com.example.Application.Repository.StudentRepo;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @PostMapping
    public StudentEntity createStudent(@RequestBody StudentEntity student) {
        Set<CourseEntity> attachedCourses = new HashSet<>();
        for (CourseEntity course : student.getCourses()) {
            if (course.getId() != null) {
                courseRepo.findById(course.getId()).ifPresent(attachedCourses::add);
            } else {
                attachedCourses.add(courseRepo.save(course));
            }
        }
        student.setCourses(attachedCourses);
        return studentRepo.save(student);
    }

    @GetMapping
    public SequencedCollection<StudentEntity> getAllStudents() {
        return studentRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Long id) {
        return studentRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
