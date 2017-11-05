package com.rishiqing.qywx.service.demo.event;

import com.google.common.eventbus.Subscribe;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.rishiqing.qywx.dao.mapper.corp.CorpTokenDao;
import com.rishiqing.qywx.dao.model.corp.CorpTokenDO;
import com.rishiqing.qywx.service.biz.corp.DeptService;
import com.rishiqing.qywx.service.biz.corp.StaffService;
import com.rishiqing.qywx.service.common.corp.CorpTokenManageService;
import com.rishiqing.qywx.service.event.listener.EventListener;
import com.rishiqing.qywx.service.event.message.CorpSuiteMessage;
import com.rishiqing.qywx.service.exception.HttpException;
import com.rishiqing.qywx.service.model.corp.CorpTokenVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DemoListener implements EventListener {
    private static final Logger logger = LoggerFactory.getLogger("SERVICE_EVENT_LISTENER_LOGGER");
    @Autowired
    private CorpTokenDao corpTokenDao;
    @Autowired
    private CorpTokenManageService corpTokenManageService;
    @Subscribe
    public void listenFetchDeptAndStaff(CorpSuiteMessage corpSuiteMessage){
        String suiteKey = corpSuiteMessage.getSuiteKey();
        String corpId = corpSuiteMessage.getCorpId();
        System.out.println("========" + suiteKey);

        try{
            System.out.println("----listener Id: " + Thread.currentThread().getId());
            corpTokenManageService.getCorpToken(suiteKey, corpId);

            CorpTokenDO corpTokenDO = corpTokenDao.getCorpTokenBySuiteKeyAndCorpId(suiteKey, corpId);
            System.out.println(">>>>>>>" + corpTokenDO.getCorpToken());
        }catch (Exception e){
            e.printStackTrace();
        }

//        try {
//            deptService.fetchAndSaveDeptInfo(corpTokenVO, null);
//            staffService.fetchAndSaveStaffList(corpTokenVO, null);
//        } catch (HttpException | UnirestException e) {
//            logger.error("HttpException or UnirestException fetchDeptAndStaff error, trying to fetch later");
//            e.printStackTrace();
//        } catch (Exception e) {
//            logger.error("fetchDeptAndStaff error, trying");
//            e.printStackTrace();
//        }
    }
}
