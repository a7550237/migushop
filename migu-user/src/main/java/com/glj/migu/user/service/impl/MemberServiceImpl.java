package com.glj.migu.user.service.impl;

import com.glj.migu.user.bean.Member;
import com.glj.migu.user.dao.MemberDao;
import com.glj.migu.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guolongjie
 * @since 2019/9/24
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberDao;

    @Override
    public List<Member> getAllMember() {
        return memberDao.selectAll();
    }
}
