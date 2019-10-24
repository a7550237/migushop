package com.glj.migu.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.glj.migu.bean.Member;
import com.glj.migu.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author guolongjie
 * @since 2019/9/25
 */
@Controller
@RequestMapping(value = "/index")
public class MemberController {
    @Reference
    MemberService memberService;

    @ResponseBody
    @RequestMapping("/getAllMember")
    public List<Member> getAllMember(){
        return memberService.getAllMember();
    }


}
