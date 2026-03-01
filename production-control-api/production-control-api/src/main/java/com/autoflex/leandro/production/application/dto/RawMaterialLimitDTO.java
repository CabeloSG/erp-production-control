package com.autoflex.leandro.production.application.dto;

public class RawMaterialLimitDTO {

    private Long id;
    private String code;
    private String name;

    public RawMaterialLimitDTO(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
