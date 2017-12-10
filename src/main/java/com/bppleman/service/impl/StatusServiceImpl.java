package com.bppleman.service.impl;

import com.bppleman.dao.CodeDao;
import com.bppleman.dao.StatusDao;
import com.bppleman.entity.IDParam;
import com.bppleman.entity.Status;
import com.bppleman.service.StatusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by BppleMan on 2017/11/20.
 */
@Service("statusService")
public class StatusServiceImpl implements StatusService {

    @Resource
    private StatusDao statusDao;

    @Resource
    private CodeDao codeDao;

    @Override
    public List<Status> getStatus(IDParam idParam) {
        return statusDao.getStatus(idParam);
    }

    @Override
    public List<Status> getStatusByIDs(List<Integer> ids) {
        return statusDao.getStatusByIDs(ids);
    }

    @Override
    public Status getTheLastInsertStatus() {
        return statusDao.getTheLastInsertStatus();
    }

    @Override
    public boolean insertStatus(Status status) {
        if (statusDao.insertStatus(status) == 1)
            return true;
        return false;
    }

    @Override
    public boolean updateStatus(Status status) {
        if (statusDao.updateStatus(status) == 1)
            return true;
        return false;
    }
}
