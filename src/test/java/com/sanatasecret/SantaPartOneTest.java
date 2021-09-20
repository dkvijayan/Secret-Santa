package com.sanatasecret;

import com.sanatasecret.common.CustomExceptions;
import com.sanatasecret.model.Member;
import com.sanatasecret.service.impl.SantaPartOne;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author Vijayan on 9/16/21
 * @project santa-secret
 */

public class SantaPartOneTest {

   /**
    * Empty List Validation
    */
   @Test(expected = CustomExceptions.class)
   public void emptyFetchPairs(){
      SantaPartOne santaPartOne = new SantaPartOne();
      List<Member> members = new ArrayList<>();
      santaPartOne.fetchPairs(members);
   }

   /**
    * Single Member - Validation
    */
   @Test(expected = CustomExceptions.class)
   public void singleMemberFetchPairs(){
      SantaPartOne santaPartOne = new SantaPartOne();
      List<Member> members = new ArrayList<>();
      members.add(new Member("1", "Father"));
      santaPartOne.fetchPairs(members);
   }

   /**
    * Limited 4 Members
    */
   @Test
   public void fourMemberFetchPairs(){
      SantaPartOne santaPartOne = new SantaPartOne();
      List<Member> members = new ArrayList<>();
      members.add(new Member(UUID.randomUUID().toString(), "Father"));
      members.add(new Member(UUID.randomUUID().toString(), "Mother"));
      members.add(new Member(UUID.randomUUID().toString(), "Son"));
      members.add(new Member(UUID.randomUUID().toString(), "Daughter"));
      Map<Member, Member> pairs = santaPartOne.fetchPairs(members);
      pairs.forEach((k, v) -> {
         Assert.assertNotEquals(k.getId(),v.getId()); // Check For Not Self Pair Assigned
      });
   }

   /**
    * Multiple Members
    */
   @Test
   public void multipleMemberFetchPairs(){
      SantaPartOne santaPartOne = new SantaPartOne();
      List<Member> members = new ArrayList<>();
      for(int i=1 ; i<=100 ; i++){
         members.add(new Member(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
      }
      Map<Member, Member> pairs = santaPartOne.fetchPairs(members);
      pairs.forEach((k, v) -> {
         Assert.assertNotEquals(k.getId(),v.getId()); // Check For Not Self Pair Assigned
      });

      Assert.assertEquals(members.size(), pairs.size()); // Check Pair Size equal to input members

   }

}
