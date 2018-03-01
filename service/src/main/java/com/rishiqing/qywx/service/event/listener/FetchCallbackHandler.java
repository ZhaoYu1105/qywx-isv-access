package com.rishiqing.qywx.service.event.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rishiqing.qywx.service.biz.corp.CorpService;
import com.rishiqing.qywx.service.biz.corp.DeptService;
import com.rishiqing.qywx.service.biz.corp.StaffService;
import com.rishiqing.qywx.service.common.corp.CorpAppManageService;
import com.rishiqing.qywx.service.common.corp.CorpTokenManageService;
import com.rishiqing.qywx.service.common.fail.CallbackFailService;
import com.rishiqing.qywx.service.common.isv.GlobalSuite;
import com.rishiqing.qywx.service.common.isv.SuiteTokenManageService;
import com.rishiqing.qywx.service.constant.CallbackChangeType;
import com.rishiqing.qywx.service.constant.CallbackFailType;
import com.rishiqing.qywx.service.constant.CallbackInfoType;
import com.rishiqing.qywx.service.event.service.AsyncService;
import com.rishiqing.qywx.service.exception.ObjectNotExistException;
import com.rishiqing.qywx.service.model.corp.*;
import com.rishiqing.qywx.service.model.isv.SuiteTokenVO;
import com.rishiqing.qywx.service.util.http.converter.Xml2BeanConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author Wallace Mao
 * Date: 2018-02-28 19:03
 */
public class FetchCallbackHandler {
    private static final Logger logger = LoggerFactory.getLogger("SERVICE_EVENT_LISTENER_LOGGER");

    @Autowired
    private SuiteTokenManageService suiteTokenManageService;
    @Autowired
    private CorpAppManageService corpAppManageService;
    @Autowired
    private CorpTokenManageService corpTokenManageService;
    @Autowired
    private CorpService corpService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private AsyncService asyncService;
    @Autowired
    private GlobalSuite suite;
    @Autowired
    private CallbackFailService callbackFailService;

    public void handleFetchCorp(String permanentCode){
        String corpId = null;
        try {
            String suiteKey = suite.getSuiteKey();
            SuiteTokenVO suiteTokenVO = suiteTokenManageService.getSuiteToken(suiteKey);

            //  首先获取corp相关信息
            CorpSuiteVO permanentCodeCorpSuite = new CorpSuiteVO();
            permanentCodeCorpSuite.setPermanentCode(permanentCode);
            CorpVO corpVO = corpService.fetchAndSaveCorpInfo(suiteTokenVO, permanentCodeCorpSuite);

            corpId = corpVO.getCorpId();
            CorpAppVO corpAppVO = corpAppManageService.getCorpAppBySuiteKeyAndCorpId(suiteKey, corpId);
            CorpTokenVO corpTokenVO = corpTokenManageService.getCorpToken(suiteKey, corpId);

            //  获取部门相关信息
            deptService.fetchAndSaveDeptInfo(corpTokenVO, null);
            //  获取员工相关信息
            staffService.fetchAndSaveStaffList(corpTokenVO, null);
            //  获取管理员相关信息
            staffService.fetchAndSaveAdminList(suiteTokenVO, corpAppVO);
            //  成功后通知同步日事清
            asyncService.sendToPushCorpAuthCallback(corpVO, CallbackInfoType.CREATE_AUTH, null);
        } catch (Exception e) {
            //  加入重试
            callbackFailService.save(corpId,
                    CallbackFailType.AUTH_CALLBACK_FAIL_SAVE_NEW_CORP,
                    CallbackInfoType.CREATE_AUTH,
                    null,
                    permanentCode);
            throw e;
        }
    }

    /**
     * 通讯录变更之创建部门
     * @param map
     */
    public void handleChangeContactCreateDept(Map map){
        CorpDeptVO deptVO = null;
        try {
            deptVO = Xml2BeanConverter.generateCorpDept(map);
            deptService.createDept(deptVO);
        } catch (Exception e) {
            String jsonSting = JSON.toJSONString(map);
            callbackFailService.save(deptVO.getCorpId(),
                    CallbackFailType.CONTACT_CALLBACK_FAIL_SAVE_COMMON,
                    CallbackInfoType.CHANGE_CONTACT,
                    CallbackChangeType.CREATE_PARTY,
                    jsonSting);
            throw e;
        }
    }

    /**
     * 通讯录变更之更新部门
     * @param map
     */
    public void handleChangeContactUpdateDept(Map map) {
        CorpDeptVO deptVO = null;
        try {
            deptVO = Xml2BeanConverter.generateCorpDept(map);
            deptService.updateDept(deptVO);
        } catch (Exception e) {
            String jsonSting = JSON.toJSONString(map);
            callbackFailService.save(deptVO.getCorpId(),
                    CallbackFailType.CONTACT_CALLBACK_FAIL_SAVE_COMMON,
                    CallbackInfoType.CHANGE_CONTACT,
                    CallbackChangeType.UPDATE_PARTY,
                    jsonSting);
            throw e;
        }
    }

    /**
     * 通讯录变更之删除部门
     * @param map
     */
    public void handleChangeContactDeleteDept(Map map) {
        CorpDeptVO deptVO = null;
        try {
            deptVO = Xml2BeanConverter.generateCorpDept(map);
            CorpDeptVO dbDeptVO = deptService.getDept(deptVO);
            map.put("rsqId", dbDeptVO.getRsqId());
            deptService.deleteDept(dbDeptVO);
        } catch (Exception e) {
            String jsonSting = JSON.toJSONString(map);
            callbackFailService.save(deptVO.getCorpId(),
                    CallbackFailType.CONTACT_CALLBACK_FAIL_SAVE_COMMON,
                    CallbackInfoType.CHANGE_CONTACT,
                    CallbackChangeType.DELETE_PARTY,
                    jsonSting);
            throw e;
        }
    }

    /**
     * 通讯录变更之添加成员
     * @param map
     */
    public void handleChangeContactCreateUser(Map map){
        CorpStaffVO staffVO = null;
        try {
            staffVO = Xml2BeanConverter.generateCorpStaff(map);
            staffService.createStaff(staffVO);
        } catch (Exception e) {
            String jsonSting = JSON.toJSONString(map);
            callbackFailService.save(staffVO.getCorpId(),
                    CallbackFailType.CONTACT_CALLBACK_FAIL_SAVE_COMMON,
                    CallbackInfoType.CHANGE_CONTACT,
                    CallbackChangeType.CREATE_USER,
                    jsonSting);
            throw e;
        }
    }

    /**
     * 通讯录变更之更新成员
     * @param map
     */
    public void handleChangeContactUpdateUser(Map map) {
        CorpStaffVO staffVO = null;
        try {
            staffVO = Xml2BeanConverter.generateCorpStaff(map);
            staffService.updateStaff(staffVO);
        } catch (ObjectNotExistException e) {
            String jsonSting = JSON.toJSONString(map);
            callbackFailService.save(staffVO.getCorpId(),
                    CallbackFailType.CONTACT_CALLBACK_FAIL_SAVE_COMMON,
                    CallbackInfoType.CHANGE_CONTACT,
                    CallbackChangeType.UPDATE_USER,
                    jsonSting);
            throw e;
        }
    }

    /**
     * 通讯录变更之删除成员
     * @param map
     */
    public void handleChangeContactDeleteUser(Map map) {
        CorpStaffVO staffVO = null;
        try {
            staffVO = Xml2BeanConverter.generateCorpStaff(map);
            CorpStaffVO dbStaffVO = staffService.getStaff(staffVO);
            map.put("rsqUserId", dbStaffVO.getRsqUserId());
            staffService.deleteStaff(dbStaffVO);
        } catch (ObjectNotExistException e) {
            String jsonSting = JSON.toJSONString(map);
            callbackFailService.save(staffVO.getCorpId(),
                    CallbackFailType.CONTACT_CALLBACK_FAIL_SAVE_COMMON,
                    CallbackInfoType.CHANGE_CONTACT,
                    CallbackChangeType.DELETE_USER,
                    jsonSting);
            throw e;
        }
    }
}
