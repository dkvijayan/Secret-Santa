package com.sanatasecret.model;

import java.util.*;

/**
 * @author Vijayan on 9/16/21
 * @project santa-secret
 */
public class Member {

   private String id;

   private String name;

   private List<MemberRelations> relationshipRoles; // Convert String into ENUM For Part 3

   private String familyId;

   private NavigableMap<Integer, String> secretGiftHistory; // Santa Picked History For Part 2

   public Member(String id, String name) {
      this.id = id;
      this.name = name;
      this.secretGiftHistory = new TreeMap<>();
      this.relationshipRoles = new LinkedList<>();
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public NavigableMap<Integer, String> getSecretGiftHistory() {
      return secretGiftHistory;
   }

   public void setSecretGiftHistory(NavigableMap<Integer, String> secretGiftHistory) {
      this.secretGiftHistory = secretGiftHistory;
   }

   public List<MemberRelations> getRelationshipRoles() {
      return relationshipRoles;
   }

   public void setRelationshipRoles(List<MemberRelations> relationshipRoles) {
      this.relationshipRoles = relationshipRoles;
   }

   public String getFamilyId() {
      return familyId;
   }

   public void setFamilyId(String familyId) {
      this.familyId = familyId;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Member member = (Member) o;
      return id.equals(member.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   @Override
   public String toString() {
      return "Member{" +
              "id='" + id + '\'' +
              ", name='" + name + '\'' +
              ", relationshipRoles=" + relationshipRoles +
              ", secretGiftHistory=" + secretGiftHistory +
              '}';
   }
}
