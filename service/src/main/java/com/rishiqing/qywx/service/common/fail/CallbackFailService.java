package com.rishiqing.qywx.service.common.fail;

import com.rishiqing.qywx.dao.model.fail.FailAuthCallbackDO;
import com.rishiqing.qywx.dao.model.fail.FailContactCallbackDO;
import com.rishiqing.qywx.service.constant.CallbackChangeType;
import com.rishiqing.qywx.service.constant.CallbackFailType;
import com.rishiqing.qywx.service.constant.CallbackInfoType;

import java.util.List;

/**
 * @author Wallace Mao
 * Date: 2018-02-28 16:17
 */
public interface CallbackFailService {
    void save(String corpId, CallbackFailType failType, CallbackInfoType infoType, CallbackChangeType changeType, String content);

    void deleteFailAuthCallback(Long id);

    void deleteFailContactCallback(Long id);

    List<FailAuthCallbackDO> listFailAuthCallback();

    List<FailContactCallbackDO> listFailContactCallback();
}
