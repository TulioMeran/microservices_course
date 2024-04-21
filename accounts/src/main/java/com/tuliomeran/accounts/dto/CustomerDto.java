package com.tuliomeran.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    @Schema(
            description = "Name of the customer",
            example = "Eazy Bytes"
    )
    private String name;
    @NotEmpty(message = "Email can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    @Schema(
            description = "Email of the customer",
            example = "eazy@gmail.com"
    )
    private String email;
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Mobile Number of the customer",
            example = "1829446298"
    )
    private String mobileNumber;
    @Schema(
            description = "Account of the customer"
    )
    private AccountsDto accountsDto;
}
