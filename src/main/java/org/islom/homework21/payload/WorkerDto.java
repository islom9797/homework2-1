package org.islom.homework21.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkerDto {


    @NotNull(message = "name bo'sh bo'lmasligi kerak")
    private String name;

    @NotNull(message = "phoneNumber bo'sh bo'lmasligi kerak")
    private String phoneNumber;

    @NotNull(message = "departmentId bo'sh bo'lmasligi kerak")
    private int departmentId;

    @NotNull(message = "street bo'sh bo'lmasligi kerak")
    private String street;

    @NotNull(message = "homeNumber bo'sh bo'lmasligi kerak")
    private String homeNumber;
}
