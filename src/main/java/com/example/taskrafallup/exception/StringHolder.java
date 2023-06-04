package com.example.taskrafallup.exception;

public interface StringHolder {
    String NOT_ACCEPTED_RESPONSE = "No acceptable response";
    String USERNAME_NOT_FOUND = "username not found ";
    String CONTROLLER_BASE_URL = "/git";
    String EXTERNAL_API_BASE_URL = "https://api.github.com";
    String USER_REPOS_ENDPOINT = "/users/{name}/repos";
    String BRANCHES_ENDPOINT = "/repos/{name}/{repositoryName}/branches";
}
