package com.sanatasecret.dto;

import java.util.*;

/**
 * @author Viju on 9/23/21
 * @project santa-secret
 */


public class MemberDTO {

   private String id;
   private String name;
   private List<String> relations;
   private NavigableMap<Integer, String> giftHistory;

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

   public List<String> getRelations() {
      return relations;
   }

   public void setRelations(List<String> relations) {
      this.relations = relations;
   }

   public NavigableMap<Integer, String> getGiftHistory() {
      return giftHistory;
   }

   public void setGiftHistory(NavigableMap<Integer, String> giftHistory) {
      this.giftHistory = giftHistory;
   }
}
