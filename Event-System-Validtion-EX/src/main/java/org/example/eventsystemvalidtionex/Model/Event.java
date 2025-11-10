package org.example.eventsystemvalidtionex.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {
    @NotNull(message = "you must enter the ID")
    @Size(min = 3,message = "the length of id must be more than 2")
    private  String ID;
    @NotEmpty(message = "you must enter description")
    @Size(min = 16 , message = "the length of description must be more than 15")
    private String description;

    @NotNull(message = "Capacity can not be null")
    @Positive
    @Min(value = 26, message = "Capacity must be more than 25")
    private  int capacity;

    @JsonFormat(pattern="yyyy-MM-dd")
    @PastOrPresent(message = "Start date must be in the past or future")
    private LocalDate startDate ;

    @JsonFormat(pattern="yyyy-MM-dd")
    @FutureOrPresent(message = "End date must be in the present or future")
    private LocalDate endDate;




}
