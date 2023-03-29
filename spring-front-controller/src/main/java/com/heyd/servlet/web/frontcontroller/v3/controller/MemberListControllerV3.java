package com.heyd.servlet.web.frontcontroller.v3.controller;

import com.heyd.servlet.domain.member.MemberRepository;
import com.heyd.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        final var members = memberRepository.findAll();
        var mv = new ModelView("members");
        mv.getModel().put("members", members);

        return mv;
    }
}
