package com.sanatasecret.service;

import com.sanatasecret.model.Member;
import java.util.List;
import java.util.Map;

/**
 * @author Vijayan on 9/16/21
 * @project santa-secret
 */
public interface SantaSecret {

    Map<Member, Member> fetchPairs(List<Member> members);

}
