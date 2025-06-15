// DoctorResponse.java
package org.example.clinic.dto.response;

import lombok.Data;

@Data
public class DoctorResponse {
    private Integer id;
    private String name;
    private String surname;
    private String bio;
    private String certificates;
    private Float rating;
    private String username; // from linked user
}
