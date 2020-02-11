INSERT INTO [dbo].[applicant]([address],[dob],[education_level],[first_name],[last_name],[region])VALUES('address1', '1975-02-01','1','Bob','Marley', 'Athens')
INSERT INTO [dbo].[applicant]([address],[dob],[education_level],[first_name],[last_name],[region])VALUES('address2', '1990-03-10','3','AAA','Marley', 'Mykonos')
INSERT INTO [dbo].[applicant]([address],[dob],[education_level],[first_name],[last_name],[region])VALUES('address3', '1989-02-22','1','BBB','Marley', 'Athens')
INSERT INTO [dbo].[applicant]([address],[dob],[education_level],[first_name],[last_name],[region])VALUES('address4', '2001-02-01','2','Da','Marley', 'Athens')

INSERT INTO [dbo].[job]([company_name],[education_level_required],[job_title],[posted_date],[region])VALUES('Accenture','1', 'Java Developer', '2020-01-21','Athens')
INSERT INTO [dbo].[job]([company_name],[education_level_required],[job_title],[posted_date],[region])VALUES('Company2','3', 'Job title 2', '2019-11-21','Piraeus')
INSERT INTO [dbo].[job]([company_name],[education_level_required],[job_title],[posted_date],[region])VALUES('Company3','1', 'Job title 3', '2020-01-21','Kifisia')

INSERT INTO [dbo].[skill]([skill_name])VALUES( 'Java')
INSERT INTO [dbo].[skill]([skill_name])VALUES( 'C#')
INSERT INTO [dbo].[skill]([skill_name])VALUES( 'ReACT')
INSERT INTO [dbo].[skill]([skill_name])VALUES( 'Python')
INSERT INTO [dbo].[skill]([skill_name])VALUES( 'Angular')
--
INSERT INTO [dbo].[match]([date_finalized],[match_status],[applicant_id],[job_id])VALUES('2020-01-01',1, 1,3)
INSERT INTO [dbo].[match]([date_finalized],[match_status],[applicant_id],[job_id])VALUES('2020-02-10',1, 3,1)
INSERT INTO [dbo].[match]([date_finalized],[match_status],[applicant_id],[job_id])VALUES('2020-01-28',3, 1,2)
INSERT INTO [dbo].[match]([date_finalized],[match_status],[applicant_id],[job_id])VALUES('2020-01-28',1, 1,2)
--
INSERT INTO [dbo].[applicant_skill]([applicant_id],[skill_id])VALUES(1,1)
INSERT INTO [dbo].[applicant_skill]([applicant_id],[skill_id])VALUES(1,3)
INSERT INTO [dbo].[applicant_skill]([applicant_id],[skill_id])VALUES(1,4)
INSERT INTO [dbo].[applicant_skill]([applicant_id],[skill_id])VALUES(4,2)
--
INSERT INTO [dbo].[job_skill]([job_id],[skill_id])VALUES(1,1)
INSERT INTO [dbo].[job_skill]([job_id],[skill_id])VALUES(1,3)
INSERT INTO [dbo].[job_skill]([job_id],[skill_id])VALUES(2,2)
INSERT INTO [dbo].[job_skill]([job_id],[skill_id])VALUES(3,2)

--
--
