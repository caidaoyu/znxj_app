package com.niule.znxj.core.util;

import com.niule.znxj.web.dao.ReportcontentMapper;
import com.niule.znxj.web.model.Reportcontent;
import com.niule.znxj.web.model.ReportcontentExample;

import java.util.List;

/**
 * Created by admin on 2018/5/31.
 */
public class ReportContentUtils {

    public static List<Reportcontent> getReportConentList(ReportcontentMapper reportcontentMapper, Long id){
        ReportcontentExample reportcontentExample = new ReportcontentExample();
        reportcontentExample.createCriteria().andReportidEqualTo(id);
        return reportcontentMapper.selectByExample(reportcontentExample);
    }
}
