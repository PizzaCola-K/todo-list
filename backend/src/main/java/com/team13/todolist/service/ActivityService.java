package com.team13.todolist.service;

import com.team13.todolist.model.Activity;
import com.team13.todolist.model.ActivityInfo;
import com.team13.todolist.model.User;
import com.team13.todolist.repository.ActivityRepository;
import com.team13.todolist.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public ActivityService(ActivityRepository activityRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    public List<ActivityInfo> activities() {
        Page<Activity> activities = activityRepository.findAll(PageRequest.of(0, 10,
                Sort.by("actionTime").descending()));
        List<ActivityInfo> activityInfos = new ArrayList<>();
        for (Activity activity : activities) {
            User user = userRepository.findById(activity.getUserId()).orElseThrow(RuntimeException::new);
            activityInfos.add(ActivityInfo.of(activity, user));
        }
        return activityInfos;
    }

}
