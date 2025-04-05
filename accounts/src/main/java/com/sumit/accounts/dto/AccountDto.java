package com.sumit.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts", description = "Schema to hold Account information")
public class AccountDto {

    @NotEmpty(message = "AccountNumber can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
    @Schema(description = "Account Number of Sumit Mittal Bank", example = "3454433243")
    private Long accountNumber;

    @NotEmpty(message = "AccountType can not be a null or empty")
    @Schema(description = "Account type of Sumit Mittal Bank", example = "Savings")
    private String accountType;

    @NotEmpty(message = "BranchAddress can not be a null or empty")
    @Schema(description = "Sumit Mittal Bank branch address", example = "123 NewYork")
    private String branchAddress;


}