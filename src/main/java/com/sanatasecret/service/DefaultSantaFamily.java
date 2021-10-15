package com.sanatasecret.service;

import com.sanatasecret.model.Family;
import com.sanatasecret.model.FamilyMember;
import com.sanatasecret.model.Member;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Viju on 9/23/21
 * @project santa-secret
 */
abstract class DefaultSantaFamily {

    @Autowired
    private SantaFamilyService santaFamilyService;

    /**
     *
     * @param family
     * @return
     */
    public Family createNewFamily(Family family){
        family.setId(java.util.UUID.randomUUID().toString());
        family.setCreatedDateTime(LocalDateTime.now());
        return santaFamilyService.createNewFamily(family);
    }

    /**
     * @param list
     * @return
     */
    public List<Member> addMembers(List<Member> list){
        List<Member> members = new ArrayList<>();
        list.forEach(mem -> {
            members.add(new Member(java.util.UUID.randomUUID().toString(), mem.getName()));
        });
        return santaFamilyService.addMembers(list.get(0).getFamilyId(), members);
    }

    /**
     * Member list
     * @param familyId
     * @return
     */
    public List<Member> fetchMembers(String familyId){
        return santaFamilyService.fetchMembers(familyId);
    }

    /**
     * Single Member
     * @param familyId
     * @return
     */
    public Member fetchSingleMember(String familyId, String memberId){
        return santaFamilyService.fetchSingleMembers(familyId, memberId);
    }

    /**
     * Single Family
     * @param familyId
     * @return
     */
    public FamilyMember fetchFamilyMember(String familyId) {
        return santaFamilyService.fetchSingleFamily(familyId);
    }

    /**
     * Update
     * @param familyMember
     */
    public void updatePlayHistory(FamilyMember familyMember){
        santaFamilyService.updateFamilyMemberPlay(familyMember);
    }
}
