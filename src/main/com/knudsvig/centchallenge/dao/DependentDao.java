package com.knudsvig.centchallenge.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.knudsvig.centchallenge.entity.Dependent;

@Repository
public interface DependentDao extends MongoRepository<Dependent, String> {
}
