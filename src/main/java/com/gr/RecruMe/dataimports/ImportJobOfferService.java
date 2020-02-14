package com.gr.RecruMe.dataimports;

import com.gr.RecruMe.models.Job;
import com.gr.RecruMe.models.JobSkill;
import com.gr.RecruMe.models.Skill;
import com.gr.RecruMe.models.SkillLevel;
import com.gr.RecruMe.repositories.JobRepository;
import com.gr.RecruMe.repositories.JobSkillRepository;
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
public class ImportJobOfferService {


    private JobRepository jobRepository;
    private SkillRepository skillRepository;
    private JobSkillRepository jobSkillRepository;

    /**
     * set dependencies  in class's  constructor
     *
     * @param jobRepository
     */
    @Autowired
    public ImportJobOfferService(JobRepository jobRepository, SkillRepository skillRepository, JobSkillRepository jobSkillRepository) {
        this.jobRepository = jobRepository;
        this.skillRepository = skillRepository;
        this.jobSkillRepository = jobSkillRepository;
    }

    /**
     * int getCellType() Deprecated. POI 3.15. Will return a CellType enum in the future.
     * Return the cell type. Will return CellType in version 4.0 of POI.
     * For forwards compatibility, do not hard-code cell type literals in your code.
     *
     * @throws IOException
     * @return
     */
    public List<Job> ImportJobFromExcel() throws IOException {

        String excelFileName = "dataForRecrume.xlsx";
        File fileName = ResourceUtils.getFile("src/main/resources/" + excelFileName);

        try {
            FileInputStream excelFile = new FileInputStream(fileName);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet dataTypeSheet = workbook.getSheetAt(1);
            Iterator<Row> row = dataTypeSheet.iterator();
            row.next();

            //List<Job> jobs = new ArrayList<>();

            while (row.hasNext()) {
                Row currentRow = row.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                Job jobOffer = new Job();

                jobOffer.setCompanyName(cellIterator.next().getStringCellValue());
                jobOffer.setJobTitle(cellIterator.next().getStringCellValue());
                jobOffer.setRegion(cellIterator.next().getStringCellValue());
                jobOffer.setSkillLevelRequired(SkillLevel.valueOf(cellIterator.next().getStringCellValue().toUpperCase()));
                
                jobOffer = jobRepository.save(jobOffer);

                List<JobSkill> jobSkills = new ArrayList<>();

                while (cellIterator.hasNext()) {
                    String skillCell = cellIterator.next().getStringCellValue();
                    Skill skill = skillRepository.findFirstBySkillName(skillCell);
                    if(skill == null){
                        Skill s = new Skill();
                        s.setSkillName(skillCell);
                        skill = skillRepository.save(s);
                    }

                    JobSkill jobSkill = new JobSkill();
//                    if (!skillCell.trim().isEmpty()) {
//                        continue;
//                    }
                    jobSkill.setJob(jobOffer);
                    jobSkill.setSkill(skill);
                    jobSkill = jobSkillRepository.save(jobSkill);
                    jobSkills.add(jobSkill);
                    jobOffer.setJobSkills(jobSkills);
                    jobRepository.save(jobOffer);
                }

            }

        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace(); }

        return jobRepository.findAll();
    }

}