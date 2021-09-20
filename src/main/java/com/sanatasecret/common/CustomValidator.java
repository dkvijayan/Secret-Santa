package com.sanatasecret.common;

import com.sanatasecret.model.Member;

import java.util.List;
import java.util.Objects;

/**
 * @author Vijayan on 9/17/21
 * @project santa-secret
 */
public class CustomValidator {

   // Checking family member list empty
   public static void checkEmptyList(List<Member> memberList){
      if(Objects.isNull(memberList) || memberList.isEmpty()){
         throw new CustomExceptions("Family List Empty!!!");
      }
   }

   // Checking family member list whether count <=2
   public static void checkMinMembers(List<Member> memberList){
      if(memberList.size() <= 2){
         throw new CustomExceptions("Min 3 Members For Santa Secret");
      }
   }

}
