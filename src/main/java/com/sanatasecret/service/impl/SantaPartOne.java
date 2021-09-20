package com.sanatasecret.service.impl;

import com.sanatasecret.common.CustomValidator;
import com.sanatasecret.model.Member;
import com.sanatasecret.service.SantaSecret;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Vijayan on 9/16/21
 * @project santa-secret
 */
@Service
public class SantaPartOne implements SantaSecret {

    /**
     * Fetch Pairs for Santa Secret
     * @param memberList
     * @return
     */
    @Override
    public Map<Member, Member> fetchPairs(List<Member> memberList) {

     // Checking family member list empty
     CustomValidator.checkEmptyList(memberList);

     //Checking family member list whether count <=2
     CustomValidator.checkMinMembers(memberList);

     Map<Member, Member> pairMap = new HashMap<>();
     int randomIndex = new Random().nextInt(memberList.size() - 1) + 1; // Random Index used for Same member won't assign each time
     for(int i = 0; i < memberList.size(); i++){
         int  memberIndex = (i + randomIndex) % memberList.size(); // Pair Index boundary with thin List Size, Not exceed
         pairMap.put(memberList.get(i), memberList.get(memberIndex));
     }
     return pairMap;
    }

}
