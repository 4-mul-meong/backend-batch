package com.mulmeong.batchserver.contest.infrastructure.repository;

import com.mulmeong.batchserver.contest.entity.contestRead.ContestPostRead;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestReadRepository extends MongoRepository<ContestPostRead, String> {
    List<ContestPostRead> findAllByContestId(Long id);
}
