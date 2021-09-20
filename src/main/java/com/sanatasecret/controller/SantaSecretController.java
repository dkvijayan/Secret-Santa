package com.sanatasecret.controller;

import com.sanatasecret.common.ApplicationConstant;
import com.sanatasecret.model.Member;
import com.sanatasecret.service.impl.SantaPartOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Vijayan on 9/16/21
 * @project santa-secret
 */

@RestController
@RequestMapping("/api/santa-secret")
public class SantaSecretController {

    @Autowired
    private SantaPartOne santaPartOne;

    @GetMapping("/pairs")
    public ResponseEntity<Map<Member, Member>> getPairs(){
      return new ResponseEntity<>(santaPartOne.fetchPairs(ApplicationConstant.MEMBERS), HttpStatus.OK);
    }

}
