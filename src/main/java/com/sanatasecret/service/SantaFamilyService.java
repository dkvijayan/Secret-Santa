package com.sanatasecret.service;

import com.sanatasecret.model.Family;
import com.sanatasecret.model.FamilyMember;
import com.sanatasecret.model.Member;
import com.sanatasecret.repository.SantaFamilyMemberRepository;
import com.sanatasecret.repository.SantaFamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Viju on 9/23/21
 * @project santa-secret
 */
@Component
public class SantaFamilyService {

   @Autowired
   private SantaFamilyRepository santaFamilyRepository;

   @Autowired
   private SantaFamilyMemberRepository santaFamilyMemberRepository;

   /**
    * Method To Add Create Family
    * @param
    * @return
    */
   public Family createNewFamily(Family family){
      return santaFamilyRepository.save(family);
   }

   /**
    *
    * @param familyId
    * @param members
    * @return
    */
   public List<Member> addMembers(String familyId, List<Member> members){
      Optional<Family> family =  santaFamilyRepository.findById(familyId);
      if(Objects.nonNull(family) && family.isPresent()){
         FamilyMember member = new FamilyMember();
         member.setFamilyId(familyId);
         member.setMembers(members);
         santaFamilyMemberRepository.save(member);
      }
      return santaFamilyMemberRepository.findByFamilyId(familyId).get().getMembers();
   }

   /**
    * Return List Of Members Single Family
    * @param familyId
    * @return
    */
   public List<Member> fetchMembers(String familyId){
      return santaFamilyMemberRepository.findByFamilyId(familyId).get().getMembers();
   }

   /**
    *
    * @param familyId
    * @param memberId
    * @return
    */
   public Member fetchSingleMembers(String familyId, String memberId){
     Optional<Member> filterMember =  santaFamilyMemberRepository.findByFamilyId(familyId).get()
              .getMembers().stream().filter(member -> member.getId().equals(memberId)).findFirst();
     return filterMember.get();

   }

   /**
    *
    * @param familyId
    * @return
    */
   public FamilyMember fetchSingleFamily(String familyId){
      return santaFamilyMemberRepository.findByFamilyId(familyId).get();
   }

   /**
    *
    * @param familyMember
    */
   public void updateFamilyMemberPlay(FamilyMember familyMember){
      santaFamilyMemberRepository.save(familyMember);
   }

}
