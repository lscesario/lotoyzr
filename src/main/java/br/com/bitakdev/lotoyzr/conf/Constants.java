package br.com.bitakdev.lotoyzr.conf;

public class Constants {
	
	private Constants(){}

	public static final int ADMIN_HOUSES_MAX=4;
	public static final int VOCE_NAO_VALE_NADA=0;
	
	public static final int SESSION_TIME = 30;
	public static final String DOMAIN_NAME = "lotoyzr.com";
	public static final String STRING_LIST_CLAIM = "roles";
	public static final String RETURN_METHOD_OK = "everything_fine";
	public static final String RETURN_METHOD_UNEXPECTED = "unexpected_error";
	public static final String USER_NOT_ADMINISTRATOR="user_not_administrator";
	
	public static final int ROLE_HOUSE_ADMINISTRATOR=1;
	public static final int ROLE_HOUSE_USER=2;
	public static final int ROLE_LIST_ADMIN=3;
	public static final int ROLE_LIST_VIEWER=4;
}
