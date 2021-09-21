package com.sanatasecret;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanatasecret.common.CustomExceptions;
import com.sanatasecret.model.Member;
import com.sanatasecret.service.impl.SantaPartTwo;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author Vijayan on 9/20/21
 * @project santa-secret
 */

public class SantaPartTwoTest {

   /**
    * Empty List Validation
    */
   @Test(expected = CustomExceptions.class)
   public void emptyFetchPairs(){
      SantaPartTwo santaPartTwo = new SantaPartTwo();
      List<Member> members = new ArrayList<>();
      santaPartTwo.fetchPairs(2021, members);
   }

   /**
    * Single Member - Validation
    */
   @Test(expected = CustomExceptions.class)
   public void singleMemberFetchPairs(){
      SantaPartTwo santaPartTwo = new SantaPartTwo();
      List<Member> members = new ArrayList<>();
      members.add(new Member("1", "Father"));
      santaPartTwo.fetchPairs(2021, members);
   }

   @Test
   public void threeMembersOneYears(){
      List<Member> members = new ArrayList<>();
      members.add(new Member("1", "Mom"));
      members.add(new Member("2", "Dad"));
      members.add(new Member("3", "Brother"));

      SantaPartTwo santaPartTwo = new SantaPartTwo();
      int currentYear = 2016;
      Map<Member, Member> familyPair = santaPartTwo.fetchPairs(currentYear, members);

      familyPair.forEach((k, v) -> {
         Assert.assertNotEquals(k.getId(),k.getSecretGiftHistory().firstEntry().getValue()); // Check For Not Self Pair Assigned
      });
   }


   @Test
   public void threeMembersTwoYears(){
      List<Member> members = new ArrayList<>();
      members.add(new Member("1", "Mom"));
      members.add(new Member("2", "Dad"));
      members.add(new Member("3", "Brother"));

      SantaPartTwo santaPartTwo = new SantaPartTwo();
      int currentYear = 2016;
      Map<Member, Member> familyPair = santaPartTwo.fetchPairs(currentYear, members);

      familyPair.forEach((k, v) -> {
         Assert.assertNotEquals(k.getId(),k.getSecretGiftHistory().firstEntry().getValue()); // Check For Not Self Pair Assigned
      });


      currentYear = 2017;
      familyPair = santaPartTwo.fetchPairs(currentYear, members);
      Optional<Map.Entry<Member, Member>> matchPair =  familyPair.entrySet()
              .stream().filter(mem -> mem.getKey().equals(mem.getValue().getId())).findFirst();
      Assert.assertFalse(matchPair.isPresent());

   }

   @Test
   public void fourMembersThreeYears(){
      List<Member> members = new ArrayList<>();
      members.add(new Member("1", "Mom"));
      members.add(new Member("2", "Dad"));
      members.add(new Member("3", "Brother"));
      members.add(new Member("4", "Daughter"));

      SantaPartTwo santaPartTwo = new SantaPartTwo();
      int currentYear = 2016;
      Map<Member, Member> familyPair = santaPartTwo.fetchPairs(currentYear, members);

      familyPair.forEach((k, v) -> {
         Assert.assertNotEquals(k.getId(),k.getSecretGiftHistory().firstEntry().getValue()); // Check For Not Self Pair Assigned
      });

      currentYear = 2017;
      familyPair = santaPartTwo.fetchPairs(currentYear, members);
      Optional<Map.Entry<Member, Member>> matchPair =  familyPair.entrySet()
              .stream().filter(mem -> mem.getKey().equals(mem.getValue().getId())).findFirst();
      Assert.assertFalse(matchPair.isPresent());

      currentYear = 2018;
      familyPair = santaPartTwo.fetchPairs(currentYear, members);
      Optional<Map.Entry<Member, Member>> matchPair2018 =  familyPair.entrySet()
              .stream().filter(mem -> mem.getKey().equals(mem.getValue().getId())).findFirst();
      Assert.assertFalse(matchPair.isPresent());

      try {
         System.out.println("------familyMembers----->" + new ObjectMapper().writeValueAsString(familyPair));
      } catch (JsonProcessingException e) {
         e.printStackTrace();
      }

   }


   @Test(expected = CustomExceptions.class)
   public void fourMembersFourYears(){
      List<Member> members = new ArrayList<>();
      members.add(new Member("1", "Mom"));
      members.add(new Member("2", "Dad"));
      members.add(new Member("3", "Brother"));
      members.add(new Member("4", "Daughter"));

      SantaPartTwo santaPartTwo = new SantaPartTwo();
      int currentYear = 2016;
      Map<Member, Member> familyPair = santaPartTwo.fetchPairs(currentYear, members);

      familyPair.forEach((k, v) -> {
         Assert.assertNotEquals(k.getId(),k.getSecretGiftHistory().firstEntry().getValue()); // Check For Not Self Pair Assigned
      });

      currentYear = 2017;
      familyPair = santaPartTwo.fetchPairs(currentYear, members);
      Optional<Map.Entry<Member, Member>> matchPair =  familyPair.entrySet()
              .stream().filter(mem -> mem.getKey().equals(mem.getValue().getId())).findFirst();
      Assert.assertFalse(matchPair.isPresent());

      currentYear = 2018;
      familyPair = santaPartTwo.fetchPairs(currentYear, members);
      Optional<Map.Entry<Member, Member>> matchPair2018 =  familyPair.entrySet()
              .stream().filter(mem -> mem.getKey().equals(mem.getValue().getId())).findFirst();
      Assert.assertFalse(matchPair.isPresent());


      // Below year will not executed , No Pair after 3 years
      currentYear = 2019;
      familyPair = santaPartTwo.fetchPairs(currentYear, members);
      Optional<Map.Entry<Member, Member>> matchPair2019 =  familyPair.entrySet()
              .stream().filter(mem -> mem.getKey().equals(mem.getValue().getId())).findFirst();
      Assert.assertFalse(matchPair.isPresent());

   }

   @Test
   public void fourMembersAfterThreeYears(){
      List<Member> members = new ArrayList<>();
      members.add(new Member("1", "Mom"));
      members.add(new Member("2", "Dad"));
      members.add(new Member("3", "Brother"));
      members.add(new Member("4", "Daughter"));

      SantaPartTwo santaPartTwo = new SantaPartTwo();
      int currentYear = 2016;
      Map<Member, Member> familyPair = santaPartTwo.fetchPairs(currentYear, members);

      familyPair.forEach((k, v) -> {
         Assert.assertNotEquals(k.getId(),k.getSecretGiftHistory().firstEntry().getValue()); // Check For Not Self Pair Assigned
      });

      currentYear = 2017;
      familyPair = santaPartTwo.fetchPairs(currentYear, members);
      Optional<Map.Entry<Member, Member>> matchPair =  familyPair.entrySet()
              .stream().filter(mem -> mem.getKey().equals(mem.getValue().getId())).findFirst();
      Assert.assertFalse(matchPair.isPresent());

      currentYear = 2018;
      familyPair = santaPartTwo.fetchPairs(currentYear, members);
      Optional<Map.Entry<Member, Member>> matchPair2018 =  familyPair.entrySet()
              .stream().filter(mem -> mem.getKey().equals(mem.getValue().getId())).findFirst();
      Assert.assertFalse(matchPair.isPresent());


      // After 3 Years First Year Secret Removed From History
      currentYear = 2020;
      familyPair = santaPartTwo.fetchPairs(currentYear, members);
      Optional<Map.Entry<Member, Member>> matchPair2019 =  familyPair.entrySet()
              .stream().filter(mem -> mem.getKey().equals(mem.getValue().getId())).findFirst();
      Assert.assertFalse(matchPair.isPresent());

   }


}
