package com.niule.znxj.web.model.taskcontent;

import com.niule.znxj.core.util.json.JsonUtil;
import com.niule.znxj.web.model.Areainfo;
import com.niule.znxj.web.model.Equipmentinfo;
import com.niule.znxj.web.model.TaskReportContent;
import com.niule.znxj.web.model.TaskReportRes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by administor on 2017/4/21.
 */
public class TaskArea {
    private Areainfo area;
    private List<TaskEquipment> equipments;

    public Areainfo getArea() {
        return area;
    }

    public void setArea(Areainfo area) {
        this.area = area;
    }

    public List<TaskEquipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<TaskEquipment> equipments) {
        this.equipments = equipments;
    }

    public static void main(String[] args) {
    String json="[{\"areaname\":\"收发球区\",\"areaskip\":0,\"audio\":[],\"checkname\":\"003\",\"checktype\":\"枚举项\",\"enumitem\":\"良好\",\"equipmentskip\":0,\"equipname\":\"密度计泵\",\"errcontent\":\"\",\"img\":[],\"lowerwarning\":\"\",\"normalmax\":\"\",\"normalmin\":\"\",\"numvalue\":\"\",\"operationtime\":1526633740064,\"recordname\":\"\",\"reportstate\":\"\",\"unitname\":\"良好,一般\",\"upperwarning\":\"\",\"video\":[]},{\"areaname\":\"收发球区\",\"areaskip\":0,\"audio\":[],\"checkname\":\"DIFFUSER顶部温度 TIA1C\",\"checktype\":\"记录项\",\"equipmentskip\":0,\"equipname\":\"密度计泵\",\"errcontent\":\"\",\"img\":[],\"lowerwarning\":\"\",\"normalmax\":\"\",\"normalmin\":\"\",\"numvalue\":\"14.0\",\"operationtime\":1526633746332,\"recordname\":\"温度\",\"reportstate\":\"\",\"unitname\":\"℃\",\"upperwarning\":\"\",\"video\":[]},{\"areaname\":\"收发球区\",\"areaskip\":0,\"checkname\":\"1111\",\"checktype\":\"状态项\",\"equipmentskip\":0,\"equipname\":\"污油罐\",\"errcontent\":\"123\",\"img\":[\"/images/2018/5/18/a6d5e501-0d70-4fed-a0aa-6a69b6c741c0_IMG_20180518_163240.jpg\",\"/images/2018/5/18/670b167c-13c1-4826-b8d5-b7c775b5583a_IMG_20180518_161243.jpg\",\"/images/2018/5/18/1513f236-f701-402f-a5a2-cef1763591e0_IMG_20180518_161237.jpg\",\"/images/2018/5/18/41c07fbb-7b17-4f4d-9bab-7570f3c2e768_IMG_20180518_161235.jpg\"],\"numvalue\":\"\",\"operationtime\":1526633975326,\"recordname\":\"\",\"reportstate\":\"1\",\"unitname\":\"\"},{\"areaname\":\"收发球区\",\"areaskip\":0,\"audio\":[],\"checkname\":\"666\",\"checktype\":\"枚举项\",\"enumitem\":\"关\",\"equipmentskip\":0,\"equipname\":\"污油罐\",\"errcontent\":\"\",\"img\":[],\"lowerwarning\":\"\",\"normalmax\":\"\",\"normalmin\":\"\",\"numvalue\":\"\",\"operationtime\":1526633740082,\"recordname\":\"\",\"reportstate\":\"\",\"unitname\":\"开,关\",\"upperwarning\":\"\",\"video\":[]}]";
        String content = "{" + "\"res\":" + json + "}";
        TaskReportRes taskReportRes= JsonUtil.toObject(content, TaskReportRes.class);
        List<TaskReportContent> list=taskReportRes.getRes();
        System.out.printf(""+list.size());
    }

    }

