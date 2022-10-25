import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StudentManager {

    List<Student> studentList;

    public StudentManager() {
        this.studentList = new ArrayList<>();
    }

    public void addStudent(String firstName, String lastName, String idNumber) {
        Student student = new Student(firstName, lastName, idNumber);
        validateStudent(student);
        checkIfStudentAlreadyExist(student);
        studentList.add(student);
    }

    public List<Student> getAllStudents() {
        return this.studentList;
    }

    private void checkIfStudentAlreadyExist(Student student) {
        for (Student st: studentList) {
            if(st.getFirstName().equals(student.getFirstName()) && st.getLastName().equals(student.getLastName()) ){
                throw new RuntimeException("Student Already Exists");
            }
        }

    }

    private void validateStudent(Student student) {
        student.validateFirstName();
        student.validateLastName();
        student.validateIdNumber();
    }

}
