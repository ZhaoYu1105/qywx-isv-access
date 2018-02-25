package com.rishiqing.qywx.service.common.corp.impl;

import com.rishiqing.qywx.dao.mapper.corp.CorpDao;
import com.rishiqing.qywx.dao.mapper.corp.RsqInfoDao;
import com.rishiqing.qywx.service.common.corp.CorpManageService;
import com.rishiqing.qywx.service.model.corp.CorpVO;
import com.rishiqing.qywx.service.model.corp.helper.CorpConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CorpManageServiceImpl implements CorpManageService {
    private static final Logger logger = LoggerFactory.getLogger("CORP_MANAGE_LOGGER");
    @Autowired
    private CorpDao corpDao;
    @Autowired
    private RsqInfoDao rsqInfoDao;

    @Override
    public CorpVO getCorpByCorpId(String corpId) {
        return CorpConverter.corpDO2CorpVO(
                corpDao.getCorpByCorpId(corpId)
        );
    }

    @Override
    public void saveOrUpdateCorp(CorpVO corpVO) {
        corpDao.saveOrUpdateCorp(
                CorpConverter.corpVO2CorpDO(corpVO)
        );
    }

    @Override
    public void updateRsqInfo(CorpVO corpVO) {
        rsqInfoDao.updateCorpRsqInfo(
                CorpConverter.corpVO2CorpDO(corpVO)
        );
    }

    @Override
    public void markRemoveCorp(String corpId, Boolean authCanceled) {
        corpDao.updateCorpSetAuthCanceled(corpId, authCanceled);
    }
}
