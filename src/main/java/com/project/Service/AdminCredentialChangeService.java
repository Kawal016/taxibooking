package com.project.Service;

public interface AdminCredentialChangeService {

	public String checkCredentials(String oldusername,String oldpassword);
	public String updateCredentials(String newusername,String newpassword,String oldusername);
}
