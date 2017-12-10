package com.bppleman.entity;

public class UserSolve {
    private int id;
    private IDParam idParam;
    private String solveState;

    public static final String SOLVED = "solved";
    public static final String UN_SOLVED = "un_solved";
    public static final String UN_SUBMIT = "un_submit";

    public UserSolve() {
        this(new IDParam(null, null, null, null));
    }

    public UserSolve(IDParam idParam) {
        this.idParam = idParam;
        solveState = UN_SUBMIT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public String toString() {
        return "UserSolve{" +
                "id=" + id +
                ", idParam=" + idParam +
                ", solveState='" + solveState + "\'" +
                "}\n";
    }
}
