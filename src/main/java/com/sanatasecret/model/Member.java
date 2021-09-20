package com.sanatasecret.model;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author Vijayan on 9/16/21
 * @project santa-secret
 */
public class Member {

   private String id;

   private String name;

   private List<String> relationshipRoles; // Convert String into ENUM For Part 3

   private String familyId;

   private NavigableMap<Integer, String> secretGiftHistory; // Santa Picked History For Part 2

   public Member(String id, String name) {
      this.id = id;
      this.name = name;
      this.secretGiftHistory = new TreeMap<>();
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

   @Override
   public String toString() {
      return "FamilyMember{" +
              "id='" + id + '\'' +
              ", name='" + name + '\'' +
              '}';
   }
}
