package com.example.auto_catalog.infrastructure;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class StatisticsResponse {
    @NotNull
    private Long count;

    @NotNull
    private Date firstRecordCreationData;

    @NotNull
    private Date lastRecordCreationData;
}
