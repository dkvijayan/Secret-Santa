package com.sanatasecret.repository;

import com.sanatasecret.model.Member;
import com.sanatasecret.model.Relation;

import java.util.List;

/**
 *
 * @author Vijayan
 *
 */

public interface MemberRelationRepository {

    void  addMemberRelation(Member source, Member destination, Relation role);

    List<Member> allFamilyMembers();

    boolean isCheckMemberRelations(Member source, Member pairMember);

}
