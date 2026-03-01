package com.autoflex.leandro.production.application.dto;

public class BillOfMaterialResponseDTO {

    private Long id;
    private Long rawMaterialId;
    private String rawMaterialCode;
    private String rawMaterialName;
    private Double quantityRequired;

    public BillOfMaterialResponseDTO(
            Long id,
            Long rawMaterialId,
            String rawMaterialCode,
            String rawMaterialName,
            Double quantityRequired
        ) {
            this.id = id;
            this.rawMaterialId = rawMaterialId;
            this.rawMaterialCode = rawMaterialCode;
            this.rawMaterialName = rawMaterialName;
            this.quantityRequired = quantityRequired;
        }



    public Long getId() {return id;}
    public Long getRawMaterialId() { return rawMaterialId; }
    public String getRawMaterialCode() { return rawMaterialCode; }
    public String getRawMaterialName() { return rawMaterialName; }
    public Double getQuantityRequired() { return quantityRequired; }

}
