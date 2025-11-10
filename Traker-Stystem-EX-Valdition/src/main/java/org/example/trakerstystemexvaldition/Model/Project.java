package org.example.trakerstystemexvaldition.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    @NotNull(message = "the ID can not be empty")
    @Size(min = 3,message = "the length of id must be more than 2")
    private  String ID;
    @NotEmpty(message = "you must enter title")
    @Size(min = 9 , message = "the length of title must be more than 8")
    private  String title;
    @NotEmpty(message = "you must enter description")
    @Size(min = 16 , message = "the length of description must be more than 15")
    private  String description ;
    @NotEmpty(message = "you must enter the state of project")
    @Pattern(regexp = "^(Not Started|In Progress|Completed)$", message = "Status must be 'Not Started', 'In Progress', or 'Completed'")
    private String status;
    @NotEmpty(message = "You must enter the company name")
    @Size(min = 7, message = "the length of company name must be greater than 6")
    private  String companyName;



}
