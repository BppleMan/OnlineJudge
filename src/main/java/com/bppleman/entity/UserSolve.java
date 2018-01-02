package com.bppleman.entity;

public class UserSolve {
    private Integer id;
    private String solveState;
    private IDParam idParam;

    public static final String SOLVED = "solved";
    public static final String UN_SOLVED = "un_solved";
    public static final String UN_SUBMIT = "un_submit";

    public UserSolve(IDParam idParam) {
        this.idParam = idParam;
        this.solveState = UN_SUBMIT;
    }

    public UserSolve() {
        this(new IDParam());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public IDParam getIdParam() {
        return idParam;
    }

    public void setIdParam(IDParam idParam) {
        this.idParam = idParam;
    }

    public String getSolveState() {
        return solveState;
    }

    public void setSolveState(String solveState) {
        this.solveState = solveState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSolve userSolve = (UserSolve) o;

        if (id != null ? !id.equals(userSolve.id) : userSolve.id != null) return false;
        if (solveState != null ? !solveState.equals(userSolve.solveState) : userSolve.solveState != null) return false;
        return idParam != null ? idParam.equals(userSolve.idParam) : userSolve.idParam == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (solveState != null ? solveState.hashCode() : 0);
        result = 31 * result + (idParam != null ? idParam.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserSolve{" +
                "id=" + id +
                ", solveState='" + solveState + '\'' +
                ", idParam=" + idParam +
                '}';
    }
}
