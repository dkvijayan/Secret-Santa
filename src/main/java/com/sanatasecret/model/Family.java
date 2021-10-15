package com.sanatasecret.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author Viju on 9/23/21
 * @project santa-secret
 */
@Document(collection = "santa_family")
public class Family {

   @Id
   private String id;

   private String familyName;

   private LocalDateTime createdDateTime;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getFamilyName() {
      return familyName;
   }

   public void setFamilyName(String familyName) {
      this.familyName = familyName;
   }

   public LocalDateTime getCreatedDateTime() {
      return createdDateTime;
   }

   public void setCreatedDateTime(LocalDateTime createdDateTime) {
      this.createdDateTime = createdDateTime;
   }
}
