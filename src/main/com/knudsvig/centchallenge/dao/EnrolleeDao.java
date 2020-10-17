package com.knudsvig.centchallenge.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knudsvig.centchallenge.entity.Enrollee;

@Repository
public interface EnrolleeDao extends MongoRepository<Enrollee, String> {
}
