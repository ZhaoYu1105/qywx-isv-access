package com.rishiqing.qywx.service.common.isv.impl;

import com.rishiqing.common.exception.HttpException;
import com.rishiqing.qywx.dao.mapper.isv.SuiteDao;
import com.rishiqing.qywx.service.common.isv.SuiteManageService;
import com.rishiqing.qywx.service.model.isv.SuiteVO;
import com.rishiqing.qywx.service.model.isv.helper.SuiteConverter;
import org.springframework.beans.factory.annotation.Autowired;

public class SuiteManageServiceImpl implements SuiteManageService {
    @Autowired
    private SuiteDao suiteDao;

    public SuiteVO getSuiteInfoByKey(String key) {
        return SuiteConverter.suiteVO2SuiteDO(suiteDao.getSuiteBySuiteKey(key));
    }
}
