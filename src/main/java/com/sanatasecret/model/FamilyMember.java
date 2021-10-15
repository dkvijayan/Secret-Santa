package com.sanatasecret.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author Viju on 9/23/21
 * @project santa-secret
 */
@Document(collection = "santa_members")
public class FamilyMember {

   @Id
   private String familyId;

   private List<Member> members;

   public String getFamilyId() {
      return familyId;
   }

   public void setFamilyId(String familyId) {
      this.familyId = familyId;
   }

   public List<Member> getMembers() {
      return members;
   }

   public void setMembers(List<Member> members) {
      this.members = members;
   }
}
