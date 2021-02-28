package app;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class StudentGroup {
    public StudentGroup(String name, int num_students) {
        this.name = name;
        this.num_students = num_students;
        students = new LinkedList<>();
    }

    public StudentGroup() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    public String name;

    @Column
    private int num_students;

    @OneToMany(mappedBy="studentGroup")
    private List<Student> students;

    @ManyToOne
    private Project project;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getNum_students() {
        return num_students;
    }

    public void setNum_students(int num_students) {
        this.num_students = num_students;
    }
}
