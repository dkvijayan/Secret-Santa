package com.sanatasecret.dto;

import com.sanatasecret.model.Member;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Viju on 9/23/21
 * @project santa-secret
 */
@Data
@Getter @Setter
public class SantaSecretPairDTO {

   private MemberDTO member;
   private MemberDTO pairMember;

}
