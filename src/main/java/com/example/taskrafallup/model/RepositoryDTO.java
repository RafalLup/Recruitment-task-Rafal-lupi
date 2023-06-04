package com.example.taskrafallup.model;


public record RepositoryDTO(String repositoryName,
                            String ownerLogin,
                            String branchName,
                            String sha) {


}