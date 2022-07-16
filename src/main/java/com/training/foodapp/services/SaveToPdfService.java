package com.training.foodapp.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.training.foodapp.models.RecipeSteps;
import com.training.foodapp.models.StepModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import static com.training.foodapp.services.util.Constants.PDF_DIR;

@Service
public class SaveToPdfService {

    @Autowired
    private CleanUpService cleanUpService;

    public String saveStepsToPdf(RecipeSteps steps) throws IOException, DocumentException {
        String filePath = generatePdfName(steps.getRecipe_name());
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, BaseColor.GREEN);
        Chunk title = new Chunk(steps.getRecipe_name(), font);
        document.add(title);
        document.add(new Paragraph());
        document.add(new Paragraph());
        for (StepModel step: steps.getSteps()) {
            font = FontFactory.getFont(FontFactory.TIMES_ITALIC, 14, BaseColor.RED);
            Chunk stepChunk = new Chunk("Step "+step.getNumber(), font);
            document.add(stepChunk);
            document.add(new Paragraph());
            font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
            Chunk stepDetailChunk = new Chunk(step.getStep(), font);
            document.add(stepDetailChunk);
            document.add(new Paragraph());
        }
        document.close();

        if (!CleanUpService.schedulerStarted) {
            cleanUpService.scheduleCleanPdfDir();
        }

        return filePath;
    }

    private String generatePdfName(String recipe_name){
        return PDF_DIR+recipe_name+ "-"+ Calendar.getInstance().getTimeInMillis()+".pdf";
    }
}
