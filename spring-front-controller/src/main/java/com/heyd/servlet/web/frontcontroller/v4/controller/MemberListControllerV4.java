package com.heyd.servlet.web.frontcontroller.v4.controller;

import com.heyd.servlet.domain.member.Member;
import com.heyd.servlet.domain.member.MemberRepository;
import com.heyd.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();

        model.put("members", members);
        return "members";
    }
}
