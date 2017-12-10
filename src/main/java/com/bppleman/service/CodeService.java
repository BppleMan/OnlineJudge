package com.bppleman.service;

import com.bppleman.entity.Code;
import com.bppleman.entity.IDParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CodeService {
    List<Code> getCode(IDParam idParam);
    Code getCodeByID(int id);
    boolean insertCode(Code code);
}
