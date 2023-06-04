package com.example.taskrafallup.controller;


import com.example.taskrafallup.exception.StringHolder;
import com.example.taskrafallup.model.RepositoryDTO;
import com.example.taskrafallup.service.GitHubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class GitHubController {


    private final GitHubService service;

    public GitHubController(final GitHubService service) {
        this.service = service;
    }

    @GetMapping(StringHolder.CONTROLLER_BASE_URL)
    public List<RepositoryDTO> getInfo(@RequestParam("name") final String name) {
        return service.getRepositoryInfo(name);
    }
}
