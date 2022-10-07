package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.models.dto.request.ProductionStudioRequestDto;
import com.kata.cinema.base.service.dto.impl.ProductionStudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/studios")
public class AdminProductionStudioRestController {

    private final ProductionStudioService productionStudioService;

    @Autowired
    public AdminProductionStudioRestController(ProductionStudioService productionStudioService) {
        this.productionStudioService = productionStudioService;
    }

    @PostMapping
    public void saveProductionStudio(@RequestBody ProductionStudioRequestDto productionStudioRequestDto) {
        productionStudioService.saveProductionStudio(productionStudioRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductionStudio(@PathVariable("id") Long id) {
        productionStudioService.deleteProductionStudio(id);
    }

    @PutMapping("/{id}")
    public void updateProductionStudio(@PathVariable("id") Long id,
                                       @RequestBody ProductionStudioRequestDto productionStudioRequestDto) {
        productionStudioService.updateProductionStudio(id, productionStudioRequestDto);
    }
}
