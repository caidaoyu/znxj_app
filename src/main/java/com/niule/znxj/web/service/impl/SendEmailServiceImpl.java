package com.niule.znxj.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.niule.znxj.web.dao.SendemailMapper;
import com.niule.znxj.web.dao.TaskplaninfoMapper;
import com.niule.znxj.web.model.Sendemail;
import com.niule.znxj.web.model.SendemailExample;
import com.niule.znxj.web.model.Taskplaninfo;
import com.niule.znxj.web.model.TaskplaninfoExample;
import com.niule.znxj.web.service.SendEmailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by administor on 2017/6/21.
 */
@Service
public class SendEmailServiceImpl implements SendEmailService{
    @Resource
    private SendemailMapper sendemailMapper;

    @Resource
    private TaskplaninfoMapper taskplaninfoMapper;

    @Override
    public int addSendEmail(Sendemail sendemail) {
        return sendemailMapper.insert(sendemail);
    }

    @Override
    public List<Sendemail> showSendEmail(int page, int size,List ids) {
        SendemailExample sendemailExample=new SendemailExample();
        sendemailExample.setOrderByClause("s.id desc");
        SendemailExample.Criteria criteria = sendemailExample.createCriteria();

        TaskplaninfoExample taskplaninfoExample = new TaskplaninfoExample();
        TaskplaninfoExample.Criteria criteria1 = taskplaninfoExample.createCriteria();
        criteria1.andSiteidIn(ids);
        List<Taskplaninfo>  taskplaninfos = taskplaninfoMapper.selectByExample(taskplaninfoExample);

        if(taskplaninfos.size()>0){
            List idList = new ArrayList();
            for(Taskplaninfo taskplaninfo : taskplaninfos){
                idList.add(taskplaninfo.getId());
            }
            criteria.andTaskidIn(idList);
        }
        PageHelper.startPage(page,size);

        return sendemailMapper.selectByExample(sendemailExample);
    }

    @Override
    public Sendemail showSendById(Long id) {
        return sendemailMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updSendEmail(Sendemail sendemail) {
        return sendemailMapper.updateByPrimaryKeySelective(sendemail);
    }

    @Override
    public int delSendEmail(Long id) {
        return sendemailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Sendemail> selectByExample(SendemailExample example) {
        return sendemailMapper.selectByExample(example);
    }
}
