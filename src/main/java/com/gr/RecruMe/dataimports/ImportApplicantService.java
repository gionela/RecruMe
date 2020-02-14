package com.gr.RecruMe.dataimports;

import com.gr.RecruMe.models.*;
import com.gr.RecruMe.repositories.ApplicantRepository;
import com.gr.RecruMe.repositories.ApplicantSkillRepository;
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
public class ImportApplicantService {


    private ApplicantRepository applicantRepository;
    private SkillRepository skillRepository;
    private ApplicantSkillRepository applicantSkillRepository;

    /**
     * set dependencies  in class's  constructor
     *
     * @param applicantRepository
     */
    @Autowired
    public ImportApplicantService(ApplicantRepository applicantRepository,
                                  SkillRepository skillRepository,
                                  ApplicantSkillRepository applicantSkillRepository) {
        this.applicantRepository = applicantRepository;
        this.skillRepository = skillRepository;
        this.applicantSkillRepository = applicantSkillRepository;
    }

    /**
     * int getCellType() Deprecated. POI 3.15. Will return a CellType enum in the future.
     * Return the cell type. Will return CellType in version 4.0 of POI.
     * For forwards compatibility, do not hard-code cell type literals in your code.
     *
     * @return
     * @throws IOException
     */
    public List<Applicant> ImportApplicantFromExcel() throws IOException {

        String excelFileName = "dataForRecrume.xlsx";
        File fileName = ResourceUtils.getFile("src/main/resources/" + excelFileName);
//        List<Applicant> applicants = new ArrayList<>();
//        List<ApplicantSkill> applicantSkills = new ArrayList<>();
        try {
            FileInputStream excelFile = new FileInputStream(fileName);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet dataTypeSheet = workbook.getSheetAt(0);
            Iterator<Row> row = dataTypeSheet.iterator();
            row.next();

            while (row.hasNext()) {
                Row currentRow = row.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                Applicant applicant = new Applicant();
//               ApplicantSkill applicantSkill = new ApplicantSkill();
                applicant.setFirstName(cellIterator.next().getStringCellValue());
                applicant.setLastName(cellIterator.next().getStringCellValue());
                applicant.setAddress(cellIterator.next().getStringCellValue());
                applicant.setRegion(cellIterator.next().getStringCellValue());
                applicant.setEducationLevel(EducationLevel.valueOf(cellIterator.next().getStringCellValue().toUpperCase()));
                applicant.setSkillLevel(SkillLevel.valueOf(cellIterator.next().getStringCellValue().toUpperCase()));
                applicant = applicantRepository.save(applicant);

                List<ApplicantSkill> applicantSkills = new ArrayList<>();

                while (cellIterator.hasNext()) {
                    String skillCell = cellIterator.next().getStringCellValue();
//                   ApplicantSkill skill = new ApplicantSkill();
                    Skill skill = skillRepository.findFirstBySkillName(skillCell);
                    if(skill == null){
                        Skill s = new Skill();
                        s.setSkillName(skillCell);
                        skill = skillRepository.save(s);
                    }

                    ApplicantSkill applicantSkill = new ApplicantSkill();
                    applicantSkill.setApplicant(applicant);
                    applicantSkill.setSkill(skill);
                    applicantSkill = applicantSkillRepository.save(applicantSkill);
                    applicantSkills.add(applicantSkill);
                    applicant.setApplicantSkills(applicantSkills);
                    applicantRepository.save(applicant);
                }
            }

        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();        }

        return applicantRepository.findAll();
    }


}