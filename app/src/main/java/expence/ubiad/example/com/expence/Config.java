package expence.ubiad.example.com.expence;

/**
 * Created by Xhanii on 2/7/2017.
 */
public class Config {
    //Address of our scripts of the CRUD
    //    public static final String URL_UPDATE_USER = "http://www.comsol.net.pk/gemnet/update.php";
    public static final String CREATE_Expence =   "http://www.comsol.net.pk/qhse/Expence.php";
    public static final String REGISTER_TUTOR =   "http://192.168.5.57/Expence/Register.php";
    public static final String GET_TUTOR =   "http://192.168.5.57/Expence/GetTutor.php";
//    public static final String LOGIN_URL =       "http://www.comsol.net.pk/gemnet/user_login.php";
//    public static final String URL_UPDATE_EMP =  "http://www.comsol.net.pk/gemnet/index.php";
//    public static final String Search_URL =      "http://www.comsol.net.pk/gemnet/searchuser.php?cus_id=";
//    public static final String REM_CLI =         "http://www.comsol.net.pk/gemnet/searchforremove.php?cus_id=";
//    public static final String URL_RMV_CLI =     "http://www.comsol.net.pk/gemnet/removecli.php?cus_id=";
//    public static final String URL_CONECTED_USER ="http://www.comsol.net.pk/gemnet/connecteduser.php";
//    public static final String DATA_URL =         "http://dev.comsol.net.pk/fusionnet/json.php";
//    public static final String GET_USER =         "http://dev.comsol.net.pk/fusionnet/getuser.php?user_id=";

    //waqas
    public static final String DATA_URL = "http://www.comsol.net.pk/qhse/Report.php?date=";
    public static final String KEY_NAME = "expence";
    public static final String KEY_ADDRESS = "category";
    public static final String KEY_VC = "detail";
    public static final String JSON_ARRAY = "result";;
    //Keys for email and password as defined in our $_POST['key'] in login.php
//    public static final String KEY_MAIL = "user_name";
//    public static final String KEY_PASSWORD = "user_pass";
//
//    //If server response is equal to this that means login is successful
//    public static final String LOGIN_SUCCESS = "success";
//
//    //Keys for Sharedpreferences
//    //This would be the name of our shared preferences
//    public static final String SHARED_PREF_NAME = "myloginapp";
//
//    //This would be used to store the email of current logged in user
//    public static final String EMAIL_SHARED_PREF = "user_name";
//
//    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
//    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

    public static final String KE_Expence = "expence";
    public static final String KE_CATEGORY = "category";
    public static final String KE_DETAIL = "detail";
    public static final String KE_DATE = "date";
    public static final String KE_TIME = "time";

    //Register Tutor
    public static final String KEY_FIRSTNAME = "firstname";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_FATHERNAME = "fathername";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_DOB = "dob";
    public static final String KEY_AGE = "age";
    public static final String KEY_MOBILENO = "mobileno";
    public static final String KEY_CNIC = "cnic";
    public static final String KEY_ADDRESSS = "address";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_EDUCATION = "education";
    public static final String KEY_EXPERIENCE = "experience";
    public static final String KEY_TEACHINGDETAIL = "teachingdetail";
    public static final String KEY_INTERESTTEACH = "interestteach";
    public static final String KEY_TIMINGTEACH = "timingteach";
    public static final String KEY_MAJORSUBJECT = "majorsubject";

    //Get A Tutor
    public static final String KEY_STUDENT_NAME = "st_name";
    public static final String KEY_STUDENT_PHONENO = "st_phoneno";
    public static final String KEY_STUDENT_EMAIL = "st_email";
    public static final String KEY_STUDENT_ADDRESS = "st_address";
    public static final String KEY_STUDENT_SCHOOL = "st_school";
    public static final String KEY_STUDENT_TIMING = "st_timing";
    public static final String KEY_STUDENT_CLASS = "st_class";
}
