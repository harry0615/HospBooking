package com.app.hosbooking.global;

public enum URLs
{
    BASE("http://34.209.62.7"),

    MEMBER_JOIN(BASE.getValue() + "/member/register/"),



    MEMBER_JOIN_COMPANY(BASE.getValue()+"/member/code_check/"),
    
    MEMBER_VERIFY_EMAIL(BASE.getValue() + "/member/company_mail_auth/"),
    
    MEMBER_VERIFY_EMAIL_CHECK(BASE.getValue() + "/member/email_auth_check/"),

    MEMBER_LOGIN(BASE.getValue() + "/member/login/"),
    
    MEMBER_LOGOUT(BASE.getValue() + "/member/logout/"),
    
    MEMBER_CHANGE_PASSWORD(BASE.getValue() + "/member/password_modify/"),
    
    MEMBER_ASK_SMS(BASE.getValue() + "/member/sms_auth_send/"),
    
    MEMBER_CHECK_SMS(BASE.getValue() + "/member/sms_auth_check/"),
    
    MEMBER_COMPANY(BASE.getValue() + "/member/get_teampart/"),
    
    MEMBER_FIND_PASSWORD(BASE.getValue() + "/member/getTempPassword/"),
    
    MEMBER_REMOVE(BASE.getValue() + "/member/remove_member/"),
    
    SETTING_VERSION(BASE.getValue()+"/version.php/"),
    
    AUTH_HISTORY(BASE.getValue()+"/auth/auth_history/"),
    
    AUTH_LOGIN(BASE.getValue() + "/auth/site/"),
	
	SETTING_NOTICE("http://appapi12.msaber.io/html/notice?company_code="),
    ;
    
    private String value;
    
    private URLs(String value)
    {
        this.value = value;
    }
    
    public String getValue()
    {
        return value;
    }
    
}