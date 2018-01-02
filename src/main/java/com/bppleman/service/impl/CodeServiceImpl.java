package com.bppleman.service.impl;

import com.bppleman.dao.CodeDao;
import com.bppleman.entity.Code;
import com.bppleman.entity.IDParam;
import com.bppleman.service.CodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("codeService")
public class CodeServiceImpl implements CodeService{

    @Resource
    private CodeDao codeDao = null;

    @Override
    public List<Code> getCode(IDParam idParam) {
        return codeDao.getCode(idParam);
    }

    @Override
    public Code getCodeById(Integer id) {
        return codeDao.getCodeByID(id);
    }

    @Override
    public boolean insertCode(Code code) {
        boolean result = true;
        if (codeDao.insertCode(code) != 1)
            result = false;
        return result;
    }
}
