package com.bppleman.delegate;

import com.bppleman.entity.Status;

public interface CodeAffairDelegate {
    void shouldUpdateProblemRatio(Status status);
    void shouldUpdateStatus(Status status);
    void shouldUpdateRank(Status status);
    void shouldUpdateUserSolve(Status status);
}
