package app;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Project {

    public Project(String name, int num_groups, int num_students) {
        this.name = name;
        this.num_students = num_students;
        this.num_groups = num_groups;
        students = new LinkedList<>();
        studentGroups = new LinkedList<>();
    }

    public Project() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    public String name;

    @Column
    private int num_students;

    @Column
    private int num_groups;

    @OneToMany(mappedBy="project")
    private List<Student> students;

    @OneToMany(mappedBy="project")
    public List<StudentGroup> studentGroups;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getNum_students() {
        return num_students;
    }

    public void setNum_students(int num_students) {
        this.num_students = num_students;
    }

    public int getNum_groups() {
        return num_groups;
    }

    public void setNum_groups(int num_groups) {
        this.num_groups = num_groups;
    }

    public List<StudentGroup> getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(List<StudentGroup> studentGroups) {
        this.studentGroups = studentGroups;
    }

}
