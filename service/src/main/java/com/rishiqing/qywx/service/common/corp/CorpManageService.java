package com.rishiqing.qywx.service.common.corp;

import com.rishiqing.qywx.service.model.corp.CorpVO;

public interface CorpManageService {

    CorpVO getCorpByCorpId(String corpId);
    /**
     * 保存corp信息
     * @param corpVO
     */
    void saveOrUpdateCorp(CorpVO corpVO);

    /**
     * 只更新日事清相关的字段rsqId
     * @param corpVO
     */
    void updateRsqInfo(CorpVO corpVO);

    /**
     * 标记删除公司
     * @param corpId
     * @param authCanceled
     */
    void markRemoveCorp(String corpId, Boolean authCanceled);
}
