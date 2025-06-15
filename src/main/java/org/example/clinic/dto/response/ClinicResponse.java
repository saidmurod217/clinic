// ClinicResponse.java
package org.example.clinic.dto.response;

import lombok.Data;
import java.time.Instant;

@Data
public class ClinicResponse {
    private Integer id;
    private String name;
    private String about;
    private String certification;
    private String address;
    private String phone;
    private String weblink;
    private Instant since;
    private Float rating;
    private String username; // Owner's username
}
