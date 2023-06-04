package com.example.taskrafallup.mapper;

import com.example.taskrafallup.model.RepositoryDTO;

public class GitHubInfoMapper {


    public static RepositoryDTO toResponse(final String repositoryName,
                                           final String ownerLogin,
                                           final String branchName,
                                           final String sha
    ) {
        return new RepositoryDTO(
                repositoryName,
                ownerLogin,
                branchName,
                sha
        );
    }
}
