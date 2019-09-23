package com.niule.znxj.web.service;

import com.niule.znxj.web.model.Sendemail;
import com.niule.znxj.web.model.SendemailExample;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by administor on 2017/6/21.
 */
public interface SendEmailService {
    int addSendEmail(Sendemail sendemail);
    List<Sendemail> showSendEmail(int page, int size,List ids);
    Sendemail showSendById(Long id);
    int updSendEmail(Sendemail sendemail);
    int delSendEmail(Long id);
    List<Sendemail> selectByExample(SendemailExample example);
}
