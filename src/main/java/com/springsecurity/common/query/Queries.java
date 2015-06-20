package com.springsecurity.common.query;

public interface Queries {
	public interface UserQueries{
		String ADD_USER="INSERT INTO USER(ID,USERNAME,PASSWORD,STATUS,LOGINNAME,EMAIL) VALUES (?,?,?,?,?,?)";
		String MAX_USER_ID="SELECT MAX(ID) FROM USER";
		String GET_USER_DETAILS="SELECT * FROM USER WHERE LOGINNAME = ?";
	}
	
	
	public interface RoleQueries{
		String GET_ROLES="SELECT * FROM ROLE";
	}
	
	public interface RolesAndUsersQueries{
		String ASSIGN_ROLE="INSERT INTO USERSANDROLES(USER_ID,ROLE_ID) VALUES(?,?)";
	}
}
