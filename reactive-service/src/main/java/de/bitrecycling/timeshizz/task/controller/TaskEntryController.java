package de.bitrecycling.timeshizz.task.controller;

import de.bitrecycling.timeshizz.task.model.TaskEntry;
import de.bitrecycling.timeshizz.task.service.TaskEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * The task controller provides the endpoints to the task resource
 *
 * created by robo
 */
@RestController
@RequestMapping("/taskentries")
public class TaskEntryController {

    @Autowired
    private TaskEntryService taskEntryService;

    @GetMapping
    public Flux<TaskEntry> all(){
        return taskEntryService.all();
    }

    @GetMapping(params = "taskId")
    public Flux<TaskEntry> allByTaskId(@RequestParam("taskId") String taskId){
        return taskEntryService.allByTaskId(taskId);
    }

    @PostMapping
    public Mono<TaskEntry> create(@RequestParam("durationMinutes") Integer durationMinutes, String taskId){
        Duration duration = Duration.ofMinutes(durationMinutes);
        TaskEntry taskEntry = TaskEntry.builder().duration(duration).taskId(taskId).build();
        return taskEntryService.insert(taskEntry);

    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") String taskEntryId){
        return taskEntryService.delete(taskEntryId);
    }
}
