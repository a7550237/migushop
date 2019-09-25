package com.glj.migu.user.web;

import com.glj.migu.user.bean.Member;
import com.glj.migu.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author guolongjie
 * @since 2019/9/24
 */
@Controller
@RequestMapping(value = "/index")
public class MemberController {

    @Autowired
    MemberService memberService;

    @ResponseBody
    @RequestMapping(value = "/getAllMember",method = RequestMethod.GET)
    public List<Member> getAllMember(){
        return memberService.getAllMember();
    }



}
