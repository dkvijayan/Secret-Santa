package com.sanatasecret.controller;

import com.sanatasecret.dto.MemberDTO;
import com.sanatasecret.dto.SantaSecretPairDTO;
import com.sanatasecret.model.Family;
import com.sanatasecret.model.Member;
import com.sanatasecret.service.SantaSecretOrchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author Vijayan on 9/16/21
 * @project santa-secret
 */

@RestController
@RequestMapping("/api/santa-secret")
public class SantaSecretController {

    @Autowired
    private SantaSecretOrchestrator santaSecretOrchestrator;

    /**
     *
     * @param family
     * @return
     */
    @PostMapping("/createFamily")
    public ResponseEntity<Family> createNewFamily(@RequestBody Family family){
        return new ResponseEntity<>(santaSecretOrchestrator.createNewFamily(family), HttpStatus.CREATED);
    }

    /**
     *
     * @param family
     * @return
     */
    @PostMapping("/createMembers")
    public ResponseEntity<List<Member>> addMembers(@RequestBody List<Member> family){
        return new ResponseEntity<>(santaSecretOrchestrator.addMembers(family), HttpStatus.CREATED);
    }

    /**
     * Fetch List Of Members
     * @param familyId
     * @return
     */
    @GetMapping("/members")
    public ResponseEntity<List<MemberDTO>> fetchMembers(@RequestParam(value = "familyId") String familyId){
        return new ResponseEntity<>(santaSecretOrchestrator.getSingleFamilyMembers(familyId), HttpStatus.OK);
    }

    /**
     * Single Member
     * @param memberId
     * @return
     */
    @GetMapping("/member")
    public ResponseEntity<Member> fetchMember(@RequestParam(value = "memberId") String memberId){
        return null;
    }

    /**
     * Get Pairs Based on Family Member Id
     * @return
     */
    @GetMapping("/pairs")
    public ResponseEntity<List<SantaSecretPairDTO>> getPairs(@RequestParam(value = "familyId") String familyId){
          return new ResponseEntity<>(santaSecretOrchestrator.getSantaSecretPairs(familyId), HttpStatus.OK);
    }

    /**
     * Playing Each Year
     * @param familyId
     * @return
     */
    @GetMapping("/play-game")
    public ResponseEntity<List<MemberDTO>> playSantaGameByYear(@RequestParam(value = "familyId") String familyId,
                                                                        @RequestParam(value = "year") Integer year,
                                                                        @RequestParam(value = "isSave" , defaultValue = "false", required = false) boolean isSave){

        return new ResponseEntity<>(santaSecretOrchestrator.playSantaGameByYear(familyId, year, isSave), HttpStatus.OK);
    }

    /**
     * Family Clear Played Year
     * @param familyId
     * @param year
     * @return
     */
    @PutMapping("/clear-year")
    public ResponseEntity<List<SantaSecretPairDTO>> clearPlaySantaGameByYear(@RequestParam(value = "familyId") String familyId,
                                                                        @RequestParam(value = "year") Integer year){
        santaSecretOrchestrator.clearPlayYear(familyId, year);
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
    }



}
