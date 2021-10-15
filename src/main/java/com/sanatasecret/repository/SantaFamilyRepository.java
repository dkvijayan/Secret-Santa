package com.sanatasecret.repository;

import com.sanatasecret.model.Family;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Viju on 9/23/21
 * @project santa-secret
 */
@Repository
public interface SantaFamilyRepository extends MongoRepository<Family, String> {

    Optional<Family> findById(String id);

}
