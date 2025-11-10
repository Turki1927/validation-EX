package org.example.trakerstystemexvaldition.Controller;


import jakarta.validation.Valid;
import org.example.trakerstystemexvaldition.Api.ApiResponse;
import org.example.trakerstystemexvaldition.Model.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    ArrayList<Project>projects= new ArrayList<>();

    @GetMapping("/get")
public ResponseEntity<?> getProject(){
    if(projects.isEmpty()){

        return ResponseEntity.status(400).body(new ApiResponse("There is no project right now"));
    }
    return ResponseEntity.status(200).body(projects);
}





@PostMapping("/add")
public ResponseEntity<?> addProject(@RequestBody @Valid Project project , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        for(Project project1 : projects){
            if(project1.getID().equalsIgnoreCase(project.getID())){
                return  ResponseEntity.status(400).body(new ApiResponse("there are project with same id"));
            }
        }



        projects.add(project);
        return  ResponseEntity.status(200).body(new ApiResponse("project is added"));
}

@PutMapping("/update/{index}")
public  ResponseEntity<?> updateProject(@PathVariable int index, @RequestBody @Valid Project project, Errors errors){
       if (errors.hasErrors()){
           String message = errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(400).body(message);
       }
        projects.set(index,project);
       return  ResponseEntity.status(200).body(new ApiResponse("project is updated"));
}

@DeleteMapping("/delete/{index}")
public ResponseEntity<?> deleteProject(@PathVariable int index){
        projects.remove(index);
        return ResponseEntity.status(200).body( new ApiResponse("Project has been deleted"));
}


    @PutMapping("/change-status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable String id) {
        for (Project p : projects) {
            if (p.getID().equalsIgnoreCase( id)) {
                switch (p.getStatus()) {
                    case "Started":
                        p.setStatus("In Progress");
                        break;
                    case "In Progress":
                        p.setStatus("Completed");
                        break;
                    case "Completed":
                        return ResponseEntity.status(400).body(new ApiResponse("Project is already completed"));
                    default:
                        return ResponseEntity.status(400).body(new ApiResponse("Invalid status"));
                }
                return ResponseEntity.status(200).body(p);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Project not found"));

    }
@GetMapping("/get-by-title/{title}")
public  ResponseEntity<?> searchProject(@PathVariable String title){
        for (Project p : projects){
            if (p.getTitle().equalsIgnoreCase(title)){
                return ResponseEntity.status(200).body(new ApiResponse("Project found "+p));
            }
        }
return  ResponseEntity.status(400).body(new ApiResponse("Project not found"));

}



@GetMapping("/all-project-for-company/{companyName}")

public  ResponseEntity<?> allProjectForOneCompany (@PathVariable String companyName ){
    ArrayList <String > projectOneCompany = new ArrayList<>();
    for(Project p : projects){
        if (p.getCompanyName().equalsIgnoreCase(companyName)){
            projectOneCompany.add(p.getTitle());

        }
    }
if(projectOneCompany.isEmpty()){
    return ResponseEntity.status(400).body(new ApiResponse("there is no project found for "+companyName));
}
    return ResponseEntity.status(200).body(new ApiResponse("Projects for: "+companyName +" is: "+projectOneCompany));
}

















}// end class
