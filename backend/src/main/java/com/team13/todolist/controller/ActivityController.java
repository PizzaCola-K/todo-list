package com.team13.todolist.controller;

import com.team13.todolist.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public ResponseEntity<?> activities() {
        return ResponseEntity.ok(responseBody(activityService.activities()));
    }

    private Map<String, Object> responseBody(Object body) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("activities", body);
        return responseBody;
    }

}
