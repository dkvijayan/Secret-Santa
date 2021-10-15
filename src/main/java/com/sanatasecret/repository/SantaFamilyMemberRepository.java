package com.sanatasecret.repository;

import com.sanatasecret.model.FamilyMember;
import com.sanatasecret.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Viju on 9/23/21
 * @project santa-secret
 */
@Repository
public interface SantaFamilyMemberRepository extends MongoRepository<FamilyMember, String> {

    Optional<FamilyMember> findByFamilyId(String familyId);

}
