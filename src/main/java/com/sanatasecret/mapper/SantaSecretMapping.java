package com.sanatasecret.mapper;

import com.sanatasecret.dto.MemberDTO;
import com.sanatasecret.model.Member;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @author Viju on 9/23/21
 * @project santa-secret
 */
@Configuration
public class SantaSecretMapping {

    @Bean
    public MapperFactory mapperFactory(){

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory
                .classMap(Member.class, MemberDTO.class)
                .fieldAToB("id", "id")
                //.fieldAToB("ORDER_EMAIL","email")
                .byDefault().register();

       /* mapperFactory.classMap(MemberDTO.class, Member.class)
                .customize(new CustomMapper<MemberDTO, Member>() {
            @Override
            public void mapAtoB(MemberDTO memberDTO, Member member, MappingContext context) {
                memberDTO.setId(member.getId());
               // memberDTO.setName(member.getName());
                if(Objects.nonNull(member.getSecretGiftHistory()) && !member.getSecretGiftHistory().isEmpty()){
                    memberDTO.setGiftHistory(member.getSecretGiftHistory());
                }
            }
        }).byDefault().register();*/

        return mapperFactory;
    }

    @Bean
    public MapperFacade mapperFacade() {
        return mapperFactory().getMapperFacade();
    }



}
