package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@Controller
public class TestController {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private StudentGroupRepository studentGroupRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "index.html";
    }

    @GetMapping("/students/remove")
    public @ResponseBody Integer removeStudent(@RequestParam Integer id) {
        int groupId = studentRepository.getOne(id).getStudentGroup().getId();
        studentRepository.deleteById(id);
        studentGroupRepository.deleteById(groupId);
        return id;
    }

    @PostMapping("/students/add")
    public @ResponseBody Student addStudent(@RequestBody Student student, @RequestParam Integer id) {
        student.setProject(projectRepository.getOne(id));
        StudentGroup studentGroup = new StudentGroup("-", 0);
        studentGroupRepository.save(studentGroup);
        student.setStudentGroup(studentGroup);
        projectRepository.getOne(id).getStudents().add(student);
        studentRepository.save(student);
        return student;
    }

    @PostMapping("/createProject")
    public @ResponseBody Project createProject(@RequestBody Project project) {
        Project newProject = new Project(project.getName(), project.getNum_groups(), project.getNum_students());
        projectRepository.save(newProject);
        for(int i=0; i<project.getNum_groups(); i++){
            StudentGroup studentGroup = new StudentGroup("Group #" + (i+1), project.getNum_students());
            studentGroup.setProject(newProject);
            for(int j=0; j<project.getNum_students(); j++){
                Student student = new Student(null);
                student.setStudentGroup(studentGroup);
                studentGroup.getStudents().add(student);
                studentGroupRepository.save(studentGroup);
                studentRepository.save(student);
            }
            newProject.getStudentGroups().add(studentGroup);
            studentGroupRepository.save(studentGroup);
        }
        projectRepository.save(newProject);
        return project;
    }

    @GetMapping("/view")
    public String view(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "projects.html";
    }

    @GetMapping("/viewProject/{id}")
    public String viewProject(Model model, @PathVariable int id) {
        model.addAttribute("project", projectRepository.getOne(id));
        return "viewProject.html";
    }

}