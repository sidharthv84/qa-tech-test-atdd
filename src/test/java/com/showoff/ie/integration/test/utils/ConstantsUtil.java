package com.showoff.ie.integration.test.utils;

public class ConstantsUtil {

    public static final String CREATE_USER_ENDPOINT = "https://showoff-rails-react-production.herokuapp.com/api/v1/users";

    public static final String GET_USER_ID_ENDPOINT = "https://showoff-rails-react-production.herokuapp.com/api/v1/users/";

    public static final String GET_USER_ID_ENDPOINT1 = "https://showoff-rails-react-production.herokuapp.com/api/v1/users/925";

    public static final String GET_USER_ME_ENDPOINT = "https://showoff-rails-react-production.herokuapp.com/api/v1/users/me";

    public static final String PUT_USER_ENDPOINT = CREATE_USER_ENDPOINT+
            "/me?=Testing12345Integration";

    public static final String CHECK_EMAIL = "https://showoff-rails-react-production.herokuapp.com/api/v1/users/email?email=test@showoff.ie&client_id=277ef29692f9a70d511415dc60592daf4cf2c6f6552d3e1b769924b2f2e2e6fe&client_secret=d6106f26e8ff5b749a606a1fba557f44eb3dca8f48596847770beb9b643ea352";

    public static final String JSON_REQUEST_PATH = System.getProperty("user.dir") + "/src/test/resources/payload/";

    public static final String POST_METHOD = "post";

    public static final String PUT_METHOD = "put";

    public static final String CREATE_USER = "create-users";

    public static final String UPDATE_USER = "update-user";

    public static final String FIRST_NAME = "first_name";

    public static final String LAST_NAME = "first_name";

    public static final String EMAIL_ID = "email";

    public static final String GET_METHOD = "get";

}
