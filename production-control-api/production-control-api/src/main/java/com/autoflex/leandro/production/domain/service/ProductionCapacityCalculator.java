package com.autoflex.leandro.production.domain.service;

import com.autoflex.leandro.production.domain.entity.Product;
import com.autoflex.leandro.production.domain.entity.ProductRawMaterial;
import com.autoflex.leandro.production.domain.entity.ProductionCapacityResult;
import com.autoflex.leandro.production.domain.entity.RawMaterial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionCapacityCalculator {

    public ProductionCapacityResult calculate(
            Product product,
            List<ProductRawMaterial> materials
    ) {

        int minCapacity = Integer.MAX_VALUE;
        RawMaterial limiting = null;

        for (ProductRawMaterial prm : materials) {

            int capacity =
                    (int) (prm.getRawMaterial().getQuantityInStock()
                            / prm.getQuantityRequired());

            if (capacity < minCapacity) {
                minCapacity = capacity;
                limiting = prm.getRawMaterial();
            }
        }

        return new ProductionCapacityResult(
                minCapacity,
                limiting
        );
    }
}