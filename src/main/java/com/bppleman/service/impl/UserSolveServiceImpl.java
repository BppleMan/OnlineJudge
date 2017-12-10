package com.bppleman.service.impl;

import com.bppleman.dao.UserSolveDao;
import com.bppleman.entity.IDParam;
import com.bppleman.entity.UserSolve;
import com.bppleman.service.UserSolveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userSolveService")
public class UserSolveServiceImpl implements UserSolveService {

    @Resource
    private UserSolveDao userSolveDao;

    @Override
    public List<UserSolve> getUserSolve(IDParam idParam) {
        return userSolveDao.getUserSolve(idParam);
    }

    @Override
    public boolean insertUserSolve(UserSolve userSolve) {
        if (userSolveDao.insertUserSolve(userSolve) == 1)
            return true;
        return false;
    }

    @Override
    public boolean updateUserSolve(UserSolve userSolve) {
        if (userSolveDao.updateUserSolve(userSolve) == 1)
            return true;
        return false;
    }

    @Override
    public boolean deleteUserSolve(List<IDParam> idParams) {
        if (userSolveDao.deleteUserSolve(idParams) > 0)
            return true;
        return false;
    }

    @Override
    public Map<Integer, String> getUserSolveMap(IDParam idParam) {
        List<UserSolve> userSolves = getUserSolve(idParam);
        Map<Integer, String> result = new HashMap<>();
        for (UserSolve us: userSolves) {
            result.put(us.getIdParam().getProblemId(), us.getSolveState());
        }
        return result;
    }
}
