package com.sanatasecret.model;

/**
 * @author Vijayan
 */

public class MemberRelations {

    private Relation role;

    private Member member;

    public MemberRelations(Relation relation, Member member) {
        this.role = relation;
        this.member = member;
    }

    public Relation getRole() {
        return role;
    }

    public void setRole(Relation role) {
        this.role = role;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member1) {
        this.member = member1;
    }
}
