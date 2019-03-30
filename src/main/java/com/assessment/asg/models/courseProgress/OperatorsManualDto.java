package com.assessment.asg.models.courseProgress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperatorsManualDto {
    private String candidateNumber;
    private Long instructorId;
    private String filePath;
}
