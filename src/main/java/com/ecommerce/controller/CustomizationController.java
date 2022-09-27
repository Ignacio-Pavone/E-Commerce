package com.ecommerce.controller;
import com.ecommerce.model.BaseCustomization;
import com.ecommerce.service.CustomizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/customizations")
@RestController
public class CustomizationController {
    @Autowired
    private CustomizationService customizationService;

    @PostMapping
    public ResponseEntity<BaseCustomization> createCustomization(@RequestBody BaseCustomization bCustomizationController) {
        return ResponseEntity.ok(customizationService.createCustomization(bCustomizationController));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(customizationService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomization(@PathVariable Long id) {
        customizationService.deleteCustomization(id);
        return ResponseEntity.ok("Customization deleted");
    }
}
