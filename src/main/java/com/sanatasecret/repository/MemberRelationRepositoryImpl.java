package com.sanatasecret.repository;

import com.sanatasecret.model.Member;
import com.sanatasecret.model.MemberRelations;
import com.sanatasecret.model.Relation;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author  Vijayan
 */

@Service
public class MemberRelationRepositoryImpl implements  MemberRelationRepository{

    private List<Member> familyMembers;

    public MemberRelationRepositoryImpl() {
        this.familyMembers = new ArrayList<>();
    }

    private static final Map<String, Relation> biDirectRelation;
    static {
        Map<String, Relation> aMap = new HashMap<>();
        aMap.put(Relation.PARENT.toString(), Relation.CHILD);
        aMap.put(Relation.CHILD.toString(), Relation.PARENT);
        aMap.put(Relation.SPOUSE.toString(), Relation.SPOUSE);
        aMap.put(Relation.SIBLING.toString(), Relation.SIBLING);
        aMap.put(null, null);
        aMap.put("", null);
        biDirectRelation = Collections.unmodifiableMap(aMap);
    }


    @Override
    public void addMemberRelation(Member sourceMember, Member mappingMember, Relation role) {

        if(!familyMembers.isEmpty() && familyMembers.size() > 0 & role != null){

            // Mapping Relation Role To Source Members
            if(familyMembers.contains(sourceMember)){
                familyMembers.get(familyMembers.indexOf(sourceMember))
                        .getRelationshipRoles().add(new MemberRelations(role, mappingMember));
            }else{
                sourceMember.getRelationshipRoles().add(new MemberRelations(role, mappingMember));
                familyMembers.add(sourceMember);
            }

            // Mapping Opposite Relation Role
            if(familyMembers.contains(mappingMember)){
                familyMembers.get(familyMembers.indexOf(mappingMember))
                        .getRelationshipRoles().add(new MemberRelations(biDirectRelation.get(role.toString()), sourceMember));
            }else{
                mappingMember.getRelationshipRoles().add(new MemberRelations(biDirectRelation.get(role.toString()), sourceMember));
                familyMembers.add(mappingMember);
            }

        }else{

            // Mapping Relation Role To Members
            sourceMember.getRelationshipRoles().add(new MemberRelations(role, mappingMember));
            familyMembers.add(sourceMember);

            // Mapping Opposite Relation Role
            if(role == null){
                familyMembers.add(mappingMember);
            }else{
                mappingMember.getRelationshipRoles().add(new MemberRelations(biDirectRelation.get(role.toString()), sourceMember));
                familyMembers.add(mappingMember);
            }
        }
    }

    @Override
    public List<Member> allFamilyMembers() {
        return familyMembers;
    }

    @Override
    public boolean isCheckMemberRelations(Member source, Member pairMember) {

         AtomicReference<List<Member>> member1List = new AtomicReference<>();

       // Fetch All Relations Members From The Source Members Relations
        familyMembers.forEach( famMem -> {
            if(famMem.equals(source)){
                member1List.set(famMem.getRelationshipRoles().
                        stream().map(relation -> relation.getMember()).collect(Collectors.toList()));
            }
        });

        // Make sure members already checked in this list
        Set<Member> memberSearched = new HashSet<>(member1List.get());
        memberSearched.add(source);

        // Adding Members into Queue for checking only once and clear it
        Queue<Member> member1Queue = new LinkedList<>(member1List.get());

        // Checking Bi-Direct Relations From Source Member
        while(member1Queue.size() > 0 ){
            Member member1 = member1Queue.poll();
            // Checking In List Relations Members From Source with pairMember
            if(member1.equals(pairMember)){
                return true;
            }
            member1.getRelationshipRoles().forEach(memRelation -> {
                if(!memberSearched.contains(memRelation.getMember())){
                    memberSearched.add(memRelation.getMember());
                    member1Queue.add(memRelation.getMember());
                }
            });
        }
        return false;
    }


}
