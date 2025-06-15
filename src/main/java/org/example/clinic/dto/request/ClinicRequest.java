// ClinicRequest.java
package org.example.clinic.dto.request;

import lombok.Data;
import java.time.Instant;

@Data
public class ClinicRequest {
    private String name;
    private String about;
    private String certification;
    private String address;
    private String phone;
    private String weblink;
    private Instant since;
}
