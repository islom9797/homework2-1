package org.islom.homework21.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DepartmentDto {
    @NotNull(message = "companyId bo'sh bo'lmasligi kerak")
    private int companyId;

    @NotNull(message = "name bo'sh bo'lmasligi kerak")
    private String name;
}
