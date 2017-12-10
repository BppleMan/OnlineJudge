package com.bppleman.entity;

/**
 * Created by BppleMan on 2017/11/15.
 */
public class Code {
    private int id;
    private int length;
    private IDParam idParam;
    private String codeValue;
    private String language;

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

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", length=" + length +
                ", idParam=" + idParam +
                ", codeValue='" + codeValue + "\'" +
                ", language='" + language + "\'" +
                "}\n";
    }
}
