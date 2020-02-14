package com.gr.RecruMe.dataimports;

import com.gr.RecruMe.models.Skill;
import com.gr.RecruMe.repositories.SkillRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ImportSkillService {

    private SkillRepository skillRepository;

    /**
     * @param skillRepository
     */
    @Autowired
    public ImportSkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> ImportSkillFromExcel() throws IOException {

        String excelFileName = "dataForRecrume.xlsx";
        File fileName = ResourceUtils.getFile("src/main/resources/" + excelFileName);
        FileInputStream excelFile = new FileInputStream(fileName);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet dataTypeSheet = workbook.getSheetAt(2);
        Iterator<Row> row = dataTypeSheet.iterator();
        row.next();

        List<Skill> skills = new ArrayList<>();

        while (row.hasNext()) {
            Row currentRow = row.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            Cell description = cellIterator.next();
            Skill skill = new Skill(description.getStringCellValue());

            skills.add(skill);
            skillRepository.save(skill);
        }
        return skills;
    }

}

