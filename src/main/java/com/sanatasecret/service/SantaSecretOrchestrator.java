package com.sanatasecret.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanatasecret.common.CustomExceptions;
import com.sanatasecret.dto.MemberDTO;
import com.sanatasecret.dto.SantaSecretPairDTO;
import com.sanatasecret.model.FamilyMember;
import com.sanatasecret.model.Member;
import com.sanatasecret.service.impl.SantaPartOne;
import com.sanatasecret.service.impl.SantaPartTwo;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Viju on 9/23/21
 * @project santa-secret
 */
@Component
public class SantaSecretOrchestrator extends DefaultSantaFamily{


   @Autowired
   private SantaPartOne santaPartOne;

   @Autowired
   private SantaPartTwo santaPartTwo;

   @Autowired
   private MapperFacade mapperFacade;

   public List<SantaSecretPairDTO> getSantaSecretPairs(String familyId){
      List<SantaSecretPairDTO> secretPairDTOS = new ArrayList<>();
      List<Member> memberList = fetchMembers(familyId);
      Map<Member, Member> memberMap =  santaPartOne.fetchPairs(memberList);
      memberMap.forEach((k, v) -> {
         SantaSecretPairDTO santaSecretPairDTO = new SantaSecretPairDTO();
         santaSecretPairDTO.setMember(mapperFacade.map(k, MemberDTO.class));
         santaSecretPairDTO.setPairMember(mapperFacade.map(v, MemberDTO.class));
         secretPairDTOS.add(santaSecretPairDTO);
      });
      return secretPairDTOS;
   }

   /**
    *
    * @param familyId
    * @return
    */
   public List<MemberDTO> getSingleFamilyMembers(String familyId){
      List<Member> memberList = fetchMembers(familyId);
      return prepareMemberDTO(memberList, familyId);
   }

   private String getGiftedMember(String familyId, String memberId){
      Member member = fetchSingleMember(familyId, memberId);
      return member.getName();
   }

   /**
    * PLaying Single Year
    * @param familyId
    * @param year
    */
   public List<MemberDTO> playSantaGameByYear(String familyId, int year, boolean isSave){
      List<Member> memberList = fetchMembers(familyId);

      //Check Already Family Played Same Year
      Optional<Member> isAlreadyPlayed  = memberList.stream().filter(mem -> mem.getSecretGiftHistory().containsKey(year)).findFirst();
      if(isAlreadyPlayed.isPresent()){
         throw new CustomExceptions("Family Played Same Year, Please Clear And Play Again.");
      }

      Map<Member, Member> memberMemberMap = santaPartTwo.fetchPairs(year, memberList);

      return savePlayHistory(memberMemberMap, familyId, isSave);
   }

   /**
    * Saving Async
    */
   private List<MemberDTO> savePlayHistory(Map<Member, Member> memberMemberMap, String familyId, boolean isSave){

    /* CompletableFuture.runAsync(() -> {
        FamilyMember familyMember = fetchFamilyMember(familyId);
        familyMember.getMembers().forEach(member -> {
           NavigableMap<Integer, String> secretGiftHistory = memberMemberMap.get(member).getSecretGiftHistory();
           member.getSecretGiftHistory().putAll(secretGiftHistory);
        });
        updatePlayHistory(familyMember);
      });*/
         FamilyMember familyMember = fetchFamilyMember(familyId);
         familyMember.getMembers().forEach(member -> {
            Optional<Map.Entry<Member, Member>> filterMem = memberMemberMap.entrySet().
                    stream().filter(member1 -> member1.getKey().getId().equals(member.getId())).findFirst();
            if(filterMem.isPresent()){
               member.getSecretGiftHistory().clear();
               member.getSecretGiftHistory().putAll(filterMem.get().getKey().getSecretGiftHistory());
            }
         });
         if(isSave){
            updatePlayHistory(familyMember);
         }
         return prepareMemberDTO(familyMember.getMembers(), familyId);
   }

   /**
    *
    * @param familyId
    * @param year
    */
   public void clearPlayYear(String familyId, int year){
      FamilyMember familyMember = fetchFamilyMember(familyId);

      //Check Already Family Played Same Year
      Optional<Member> isAlreadyPlayed  = familyMember.getMembers().stream().filter(mem -> mem.getSecretGiftHistory().containsKey(year)).findFirst();
      if(isAlreadyPlayed.isPresent()){
         familyMember.getMembers().forEach(member -> {
            member.getSecretGiftHistory().remove(year);
         });
         updatePlayHistory(familyMember);
      }else{
         throw new CustomExceptions("Family Not Played Given Year.");
      }
   }

   private List<MemberDTO> prepareMemberDTO(List<Member> memberList, String familyId){
      List<MemberDTO> list = new ArrayList<>();
      memberList.forEach(member -> {
         MemberDTO memberDTO = new MemberDTO();
         memberDTO.setId(member.getId());
         memberDTO.setName(member.getName());
         NavigableMap<Integer, String> giftHistory = new TreeMap<>();
         member.getSecretGiftHistory().forEach((k,v) -> {
            giftHistory.put(k, getGiftedMember(familyId, v)+"--->"+v);
         });
         memberDTO.setGiftHistory(giftHistory);
         list.add(memberDTO);
      });
      return list;
   }


}
