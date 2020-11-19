package com.recruitment.challenge.dtos.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.recruitment.challenge.utilities.validators.ValuesMatch;

@ValuesMatch(
	field = "newPassword", 
	fieldMatch = "confirmationNewPassword", 
	message = "newPassword and confirmationNewPassword doesn't match!"
)
public class ChangePasswordRequestDTO {

	private String oldPassword;
	private String newPassword;
	private String confirmationNewPassword;

	@NotNull
	@NotBlank(message = "oldPassword cannot be null or empty")
	public String getOldPassword() {
		return oldPassword;
	}

	@NotNull
	@NotBlank(message = "newPassword cannot be null or empty")
	public String getNewPassword() {
		return newPassword;
	}

	@NotNull
	@NotBlank(message = "confirmationNewPassword cannot be null or empty")
	public String getConfirmationNewPassword() {
		return confirmationNewPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setConfirmationNewPassword(String confirmationNewPassword) {
		this.confirmationNewPassword = confirmationNewPassword;
	}

}