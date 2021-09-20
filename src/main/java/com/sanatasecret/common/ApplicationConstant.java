package com.sanatasecret.common;

import com.sanatasecret.model.Member;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Vijayan on 9/16/21
 * @project santa-secret
 */
public class ApplicationConstant {

   public static final List<Member> MEMBERS = Collections.unmodifiableList(
           new ArrayList<Member>() {{
              add(new Member("Father", "Father"));
              add(new Member("Mother", "Mother"));
              add(new Member("Son", "Son"));
              add(new Member("Daughter", "Daughter"));
           }});

}
