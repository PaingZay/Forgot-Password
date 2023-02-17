package com.example.WebAppClient.Model;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Business implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotBlank(message = "Email is required")
	private String email;

    @Size(min=8, max=20, message = "Password Size must be between 8 to 20")
    private String password;

    @NotBlank(message = "Business Name is required")
    private String businessName;

    @NotBlank(message = "Branch is required")
    private String branch;

    @NotBlank(message = "Business Type is required")
    private String businessType;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "PostalCode is required")
    private String postalCode;

    @NotBlank(message = "Contact Number is required")
    private String contactNumber;

    private String openingDays;
    
    // @NotBlank(message = "Opening Time is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime openingTime;

    // @NotBlank(message = "Closing Time is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime closingTime;


    public Business (String email, String password, String businessName, String branch, String businessType, String address, 
                    String postalCode, String contactNumber, String openingDays, LocalTime openingTime, LocalTime closingTime) 
    {

        this.email = email;
        this.password = password;
        this.businessName = businessName;
        this.branch = branch;
        this.branch = businessType;
        this.address = address;
        this.postalCode = postalCode;
        this.contactNumber = contactNumber;
        this.openingDays = openingDays;
        this.openingTime = openingTime;
        this.closingTime = closingTime;

    }


    @Override
    public String toString() {
        return "Business [id=" + id + ", email=" + email + ", password=" + password + ", businessName=" + businessName
                + ", branch=" + branch + ", businessType=" + businessType + ", address=" + address + ", postalCode="
                + postalCode + ", contactNumber=" + contactNumber + ", openingDays=" + openingDays + ", openingTime="
                + openingTime + ", closingTime=" + closingTime + "]";
    }
   
}

