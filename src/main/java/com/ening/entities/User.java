package com.ening.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.ening.providers.Constants;

/**
 * @author Jeanyannick
 *
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	protected String userFirstName;
	protected String userLastName;
	protected String userBirthdayDate;
	protected String userGender;
	protected String userPhoneNumber;
	protected String userEmail;
	protected String userTown;
	protected String userCity;
	protected Double userHeight;
	protected Double userweight;
	protected String userName;
	protected String userPassword;
	protected String userType;
	protected MultipartFile[] cni;
	protected boolean enable;
	@Column(nullable = true)
	protected int userConnectionNumber;
	@Column(name = "confirmation_token")
	protected String confirmationToken;

	@Column(name = "reset_token")
	protected String resetToken;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	protected List<Role> roles = new ArrayList<Role>();

	protected String createdAt;

	protected String updatedAt;

	public User() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirsName) {
		this.userFirstName = userFirsName.toUpperCase();
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName.toUpperCase();
	}

	public String getUserSex() {
		return userGender;
	}

	public void setUserSex(String userSex) {
		this.userGender = userSex;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {

		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void addRole(Role role) {

		if (!roles.contains(role)) {

			roles.add(role);
			role.addUser(this);
		}
	}

	public void removeRole(Role role) {

		roles.remove(role);
		role.removeUser(this);
	}

	public void setRoles(List<Role> newRoles) {

		for (Role role : roles) {

			if (!newRoles.contains(role)) {

				this.removeRole(role);
			}
		}

		for (Role role : newRoles) {

			role.addUser(this);
		}

		this.roles = newRoles;
	}

	public List<Role> getRoles() {

		return roles;
	}

	public String getFullName() {

		return userLastName + " " + userFirstName;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public void setCreatedAt(final String string) {

		createdAt = string;
	}

	public String getCreatedAt() {

		return createdAt;
	}

	public void setUpdatedAt(final String date) {

		updatedAt = date;
	}

	public String getUpdatedAt() {

		return updatedAt;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getUserConnectionNumber() {
		return userConnectionNumber;
	}

	public void setUserConnectionNumber(int userConnectionNumber) {
		this.userConnectionNumber = userConnectionNumber;
	}

	public String getUserBirthdayDate() {
		return userBirthdayDate;
	}

	public void setUserBirthdayDate(String userBirthdayDate) {
		this.userBirthdayDate = userBirthdayDate;
	}

	public Double getUserHeight() {
		return userHeight;
	}

	public void setUserHeight(Double userHeight) {
		this.userHeight = userHeight;
	}

	public Double getUserweight() {
		return userweight;
	}

	public void setUserweight(Double userweight) {
		this.userweight = userweight;
	}

	public String getUserTown() {
		return userTown;
	}

	public void setUserTown() {
		if (Constants.doualaCities.contains(this.userCity))
			this.userTown = "DOUALA";
		else if (Constants.yaoundeCities.contains(this.userCity))
			this.userTown = "YAOUNDE";
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	
	

	
	public MultipartFile[] getCni() {
		return cni;
	}

	public void setCni(MultipartFile[] cni) {
		this.cni = cni;
	}

	public void setUserTown(String userTown) {
		this.userTown = userTown;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", userBirthdayDate=" + userBirthdayDate + ", userGender=" + userGender + ", userPhoneNumber="
				+ userPhoneNumber + ", userEmail=" + userEmail + ", userTown=" + userTown + ", userCity=" + userCity
				+ ", userHeight=" + userHeight + ", userweight=" + userweight + ", userName=" + userName
				+ ", userPassword=" + userPassword + ", userType=" + userType + ", enable=" + enable
				+ ", userConnectionNumber=" + userConnectionNumber + ", confirmationToken=" + confirmationToken
				+ ", resetToken=" + resetToken + ", roles=" + roles + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

}
