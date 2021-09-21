package com.sanatasecret.service.impl;

import com.sanatasecret.common.CustomExceptions;
import com.sanatasecret.common.CustomValidator;
import com.sanatasecret.model.Member;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Vijayan
 */

@Component
public class SantaPartTwo {

    /**
     * Get Pairs Based On Years
     * @return
     */
    public Map<Member, Member> fetchPairs(int currentYear, List<Member> memberList){

        // Checking family member list empty
        CustomValidator.checkEmptyList(memberList);

        //Checking family member list whether count <=2
        CustomValidator.checkMinMembers(memberList);

        Map<Member, Member> familyPair = new HashMap<>(memberList.size());
        int shiftIndex = new Random().nextInt(memberList.size() - 1) + 1;
        for (int i = 0; i < memberList.size(); i++) {

            int pairMemberIndex = (i + shiftIndex) % memberList.size();

            Member member = memberList.get(i);

            removeOldHistory(member, currentYear);

            // Check whether Already History Has same Member
            if(member.getSecretGiftHistory().isEmpty()){
                member.getSecretGiftHistory().put(currentYear, memberList.get(pairMemberIndex).getId());
                familyPair.put(member, memberList.get(pairMemberIndex));
            }else{
                  findAndAddNewPair(pairMemberIndex, memberList, member, currentYear, familyPair);
            }
        }
        return familyPair;
    }

    /**
     * Checking Whether Member Has Old History
     * @param member
     * @param currentYear
     */
    private static void removeOldHistory(Member  member, int currentYear){

        if(member.getSecretGiftHistory().isEmpty()){
            return ;
        }

        if(currentYear - member.getSecretGiftHistory().firstEntry().getKey() > 3 ){
            member.getSecretGiftHistory().remove(member.getSecretGiftHistory().firstKey());
        }
    }

    /**
     * Find New Pair Member Not Present In Gift History
     * @param pairMemberIndex
     * @param members
     * @param member
     * @param currentYear
     * @param familyPair
     */
    private static void findAndAddNewPair(int pairMemberIndex, List<Member> members, Member member, int currentYear, Map<Member, Member> familyPair){

        Member newPairedMember = members.get(pairMemberIndex);

        if(member.getSecretGiftHistory().containsValue(newPairedMember.getId())){
            Member getNewPair = findNewMember(pairMemberIndex, members, member, 0);
            member.getSecretGiftHistory().put(currentYear, getNewPair.getId());
            familyPair.put(member, getNewPair);
        }else{
            member.getSecretGiftHistory().put(currentYear, newPairedMember.getId());
            familyPair.put(member, newPairedMember);
        }
    }

    /**
     * If Pair Existed Then Have To Find New Pair
     * @param pairMemberIndex
     * @param members
     * @param member
     * @param increment
     * @return
     */
    private static Member findNewMember(int pairMemberIndex, List<Member> members, Member member,  int increment){
        if(increment < members.size()){
            Integer nextPair = (pairMemberIndex + increment) % members.size();
            Member newPairCandidate = members.get(nextPair);

            if(newPairCandidate.getId().equals(member.getId())){
                return findNewMember(pairMemberIndex, members, member, increment+1);
            }

            if(member.getSecretGiftHistory().containsValue(newPairCandidate.getId())){
                return findNewMember(pairMemberIndex, members, member, increment+1);
            }
            return newPairCandidate;
        }else{
            throw new CustomExceptions("No Pair Found....");
        }

    }




}
