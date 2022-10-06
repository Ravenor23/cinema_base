package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.models.dto.request.ProductionStudioRequestDto;
import com.kata.cinema.base.service.dto.impl.ProductionStudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminProductionStudioRestController {

    private final ProductionStudioService productionStudioService;

    @Autowired
    public AdminProductionStudioRestController(ProductionStudioService productionStudioService) {
        this.productionStudioService = productionStudioService;
    }

    @PostMapping("/api/admin/studios")
    public void saveProductionStudio(@RequestBody ProductionStudioRequestDto productionStudioRequestDto) {
        productionStudioService.saveProductionStudio(productionStudioRequestDto);
    }

    @DeleteMapping("/api/admin/studios/{id}")
    public void deleteProductionStudio(@PathVariable("id") Long id) {
        productionStudioService.deleteProductionStudio(id);
    }

    @PutMapping("/api/admin/studios/{id}")
    public void updateProductionStudio(@PathVariable("id") Long id,
                                       @RequestBody ProductionStudioRequestDto productionStudioRequestDto) {
        productionStudioService.updateProductionStudio(id, productionStudioRequestDto);
    }
}
