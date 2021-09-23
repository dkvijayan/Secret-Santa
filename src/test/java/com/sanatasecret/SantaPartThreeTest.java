package com.sanatasecret;

import com.sanatasecret.common.CustomExceptions;
import com.sanatasecret.model.Member;
import com.sanatasecret.model.Relation;
import com.sanatasecret.repository.MemberRelationRepository;
import com.sanatasecret.repository.MemberRelationRepositoryImpl;
import com.sanatasecret.service.impl.SantaPartThree;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author Vijayan on 9/20/21
 * @project santa-secret
 */

public class SantaPartThreeTest {

   /**
    * Empty List Validation
    */
   @Test(expected = CustomExceptions.class)
   public void emptyFetchPairs(){
      MemberRelationRepository memberRelationRepository = new MemberRelationRepositoryImpl();
      SantaPartThree santaPartThree = new SantaPartThree(memberRelationRepository);
      List<Member> members = new ArrayList<>();
      santaPartThree.fetchPairs(2021);
   }

   /**
    * Single Member - Validation
    */
   @Test(expected = CustomExceptions.class)
   public void singleMemberFetchPairs(){
      MemberRelationRepository memberRelationRepository = new MemberRelationRepositoryImpl();
      memberRelationRepository.addMemberRelation(new Member("1", "Father"), new Member("2", "Son"), Relation.PARENT);
      SantaPartThree santaPartThree = new SantaPartThree(memberRelationRepository);
      santaPartThree.fetchPairs(2021);
   }

   /**
    * Three Members - One Year Playing
    */
   @Test(expected = NullPointerException.class)
   public void noRelationPair(){

      MemberRelationRepository memberRelationRepository = new MemberRelationRepositoryImpl();
      memberRelationRepository.addMemberRelation(new Member("1", "Father"), null, null);
      memberRelationRepository.addMemberRelation(new Member("2", "Wife"), null, null);

      SantaPartThree santaPartThree = new SantaPartThree(memberRelationRepository);
      Map<Member, Member> memberMap = santaPartThree.fetchPairs(2021);

   }

   /**
    * Three Members - One Year Playing
    */
   @Test
   public void threeMembersOneYear(){

      MemberRelationRepository memberRelationRepository = new MemberRelationRepositoryImpl();
      memberRelationRepository.addMemberRelation(new Member("1", "Father"), new Member("2", "Wife"), Relation.SPOUSE);
      memberRelationRepository.addMemberRelation(new Member("2", "Wife"), new Member("3", "Daughter"), Relation.PARENT);

      SantaPartThree santaPartThree = new SantaPartThree(memberRelationRepository);
      Map<Member, Member> memberMap = santaPartThree.fetchPairs(2021);

      // Checking Size with family Size
      Assert.assertEquals(memberRelationRepository.allFamilyMembers().size(), memberMap.size());
      memberMap.forEach((k, v) -> {
         Assert.assertNotEquals(k.getId(),k.getSecretGiftHistory().firstEntry().getValue()); // Check For Not Self Pair Assigned
      });

   }

   /**
    * Three Members - Three Year Playing
    */
   @Test
   public void fourMembersThreeYear(){

      MemberRelationRepository memberRelationRepository = new MemberRelationRepositoryImpl();
      Member father =  new Member("1", "Father");
      Member  mother =  new Member("2", "Mother");
      Member daughter =  new Member("3", "Daughter");
      Member son =  new Member("4", "Son");

      memberRelationRepository.addMemberRelation(father, mother, Relation.SPOUSE);
      memberRelationRepository.addMemberRelation(father, son, Relation.PARENT);
      memberRelationRepository.addMemberRelation(daughter, son, Relation.SIBLING);

      SantaPartThree santaPartThree = new SantaPartThree(memberRelationRepository);

      Map<Member, Member>  memberMap = santaPartThree.fetchPairs(2020);
      // Checking Size with family Size
      Assert.assertEquals(memberRelationRepository.allFamilyMembers().size(), memberMap.size());
      memberMap.forEach((k, v) -> {
         Assert.assertNotEquals(k.getId(),k.getSecretGiftHistory().firstEntry().getValue()); // Check For Not Self Pair Assigned
      });


      memberMap = santaPartThree.fetchPairs(2021);
      // Checking Size with family Size
      Assert.assertEquals(memberRelationRepository.allFamilyMembers().size(), memberMap.size());
      memberMap.forEach((k, v) -> {
         Assert.assertNotEquals(k.getId(),k.getSecretGiftHistory().firstEntry().getValue()); // Check For Not Self Pair Assigned
      });

      memberMap = santaPartThree.fetchPairs(2022);
      // Checking Size with family Size
      Assert.assertEquals(memberRelationRepository.allFamilyMembers().size(), memberMap.size());
      memberMap.forEach((k, v) -> {
         Assert.assertNotEquals(k.getId(),k.getSecretGiftHistory().firstEntry().getValue()); // Check For Not Self Pair Assigned
      });
   }

   /**
    * Three Members - More Than Three Year Playing
    */
   @Test
   public void fourMembersAfterThreeYear(){

      MemberRelationRepository memberRelationRepository = new MemberRelationRepositoryImpl();
      Member father =  new Member("1", "Father");
      Member  mother =  new Member("2", "Mother");
      Member daughter =  new Member("3", "Daughter");
      Member son =  new Member("4", "Son");

      memberRelationRepository.addMemberRelation(father, mother, Relation.SPOUSE);
      memberRelationRepository.addMemberRelation(father, son, Relation.PARENT);
      memberRelationRepository.addMemberRelation(daughter, son, Relation.SIBLING);

      SantaPartThree santaPartThree = new SantaPartThree(memberRelationRepository);

      Map<Member, Member>  memberMap = santaPartThree.fetchPairs(2020);
      // Checking Size with family Size
      Assert.assertEquals(memberRelationRepository.allFamilyMembers().size(), memberMap.size());
      memberMap.forEach((k, v) -> {
         Assert.assertNotEquals(k.getId(),k.getSecretGiftHistory().firstEntry().getValue()); // Check For Not Self Pair Assigned
      });


      memberMap = santaPartThree.fetchPairs(2021);
      // Checking Size with family Size
      Assert.assertEquals(memberRelationRepository.allFamilyMembers().size(), memberMap.size());
      memberMap.forEach((k, v) -> {
         Assert.assertNotEquals(k.getId(),k.getSecretGiftHistory().firstEntry().getValue()); // Check For Not Self Pair Assigned
      });

      memberMap = santaPartThree.fetchPairs(2022);
      // Checking Size with family Size
      Assert.assertEquals(memberRelationRepository.allFamilyMembers().size(), memberMap.size());
      memberMap.forEach((k, v) -> {
         Assert.assertNotEquals(k.getId(),k.getSecretGiftHistory().firstEntry().getValue()); // Check For Not Self Pair Assigned
      });

      memberMap = santaPartThree.fetchPairs(2024);
      // Checking Size with family Size
      Assert.assertEquals(memberRelationRepository.allFamilyMembers().size(), memberMap.size());
      memberMap.forEach((k, v) -> {
         Assert.assertNotEquals(k.getId(),k.getSecretGiftHistory().firstEntry().getValue()); // Check For Not Self Pair Assigned
      });

   }





}
