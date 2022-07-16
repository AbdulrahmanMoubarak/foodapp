package com.training.foodapp.controllers;

import com.itextpdf.text.DocumentException;
import com.training.foodapp.models.Recipe;
import com.training.foodapp.models.RecipeSteps;
import com.training.foodapp.services.RecipeService;
import com.training.foodapp.services.SaveToPdfService;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class FoodController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private SaveToPdfService pdfService;

    @GetMapping("/ingredients/search")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> searchIngredients(@Param(value = "query") String query) {
        return ResponseEntity.accepted().body(recipeService.searchIngredients(query));
    }

    @GetMapping("/ingredients/{iname}/search")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> searchRecipesByIngredients(@PathVariable("iname") String ing_name) {
        List<Recipe> recipes = recipeService.getRecipesByIngredientName(ing_name);
        return ResponseEntity.accepted().body(recipes);
    }

    @GetMapping("/recipes/{rid}/steps")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getRecipeSteps(@PathVariable("rid") int rid) {
        return ResponseEntity.accepted().body(List.of(recipeService.getRecipeSteps(rid)).get(0));
    }

    @GetMapping(value = "/recipes/{rid}/steps/download-pdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<byte[]> downloadRecipePdf(@PathVariable("rid") int rid) {
        RecipeSteps steps = List.of(recipeService.getRecipeSteps(rid)).get(0);
        steps.setRecipe_name(recipeService.getRecipeName(rid));
        try {
            String filePath = pdfService.saveStepsToPdf(steps);
            byte[] content = FileUtil.readAsByteArray(new File(filePath));
//            String filename = steps.getRecipe_name() + ".pdf";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
//            headers.setContentDispositionFormData(filename, filename);
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(content);
        } catch (IOException | DocumentException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new byte[0]);
        }
    }
}
