package com.gr.RecruMe.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {

    private int jobId;
    private int applicantId;
    private int matchStatus;
}
