package com.bppleman.entity;

/**
 * Created by BppleMan on 2017/11/15.
 */
public class Code {
    private Integer id;
    private Integer length;
    private IDParam idParam;
    private String codeValue;
    private String language;

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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Code code = (Code) o;

        if (id != null ? !id.equals(code.id) : code.id != null) return false;
        if (length != null ? !length.equals(code.length) : code.length != null) return false;
        if (idParam != null ? !idParam.equals(code.idParam) : code.idParam != null) return false;
        if (codeValue != null ? !codeValue.equals(code.codeValue) : code.codeValue != null) return false;
        return language != null ? language.equals(code.language) : code.language == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (idParam != null ? idParam.hashCode() : 0);
        result = 31 * result + (codeValue != null ? codeValue.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", length=" + length +
                ", idParam=" + idParam +
                ", codeValue='" + codeValue + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
