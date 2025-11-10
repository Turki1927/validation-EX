package org.example.eventsystemvalidtionex.Controller;

import jakarta.validation.Valid;
import org.example.eventsystemvalidtionex.Api.ApiResponse;
import org.example.eventsystemvalidtionex.Model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    ArrayList<Event> events= new ArrayList<>();
    @GetMapping("/get")
    public ResponseEntity<?> getEvent(){
        if(events.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there is no Event!"));
        }
        return ResponseEntity.status(200).body(events);
    }

@PostMapping("/add")
    public  ResponseEntity<?> addEvent(@RequestBody @Valid Event event , Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        for (Event event1 : events){
            if(event1.getID().equalsIgnoreCase(event.getID())){
                return  ResponseEntity.status(400).body(new ApiResponse("Event with this ID already exists"));
            }
        }

        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("Event is added"));

    }
    @PutMapping("/update/{index}")
    public  ResponseEntity<?> updateEvent(@PathVariable int index, @RequestBody @Valid Event event , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.set(index,event);
        return ResponseEntity.status(200).body(new ApiResponse("the event has been updated"));
    }
    @DeleteMapping("/delete{index}")
public  ResponseEntity<?> deleteEvent(@PathVariable int index){
    events.remove(index);
    return ResponseEntity.status(200).body(new ApiResponse("The Event is deleted"));
}
@PutMapping("/change-capacity/{index}/{capacity}")
public ResponseEntity<?>changeCapacity(@PathVariable int index, @PathVariable int capacity) {

    if (capacity > 26){

        Event event = events.get(index);
        event.setCapacity(capacity);
        return ResponseEntity.status(200).body(new ApiResponse("Capacity of Event: "+event.getDescription() +" is updated to be:"+ event.getCapacity()));

    }

        return  ResponseEntity.status(400).body(new ApiResponse("the capacity  must greater than 25"));
}


@GetMapping("/search-eve/{id}")
    public ResponseEntity<?> searchEvent(@PathVariable String id){
        for(Event e: events){
            if(e.getID().equalsIgnoreCase(id)){
                return ResponseEntity.status(200).body(e);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not Found"));
    }









}//end class
