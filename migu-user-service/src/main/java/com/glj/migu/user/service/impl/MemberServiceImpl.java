package com.glj.migu.user.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.glj.migu.bean.Member;
import com.glj.migu.user.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;


import com.glj.migu.service.MemberService;
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
