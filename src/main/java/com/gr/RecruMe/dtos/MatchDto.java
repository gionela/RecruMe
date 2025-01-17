package com.gr.RecruMe.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object to request data input for new manual match
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {

    private int jobId;
    private int applicantId;

}
