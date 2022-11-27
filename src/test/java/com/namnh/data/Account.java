package com.namnh.data;

public class Account {
    public class Username {
        public static final String standard = "standard_user";
        public static final String locked = "locked_out_user";
        public static final String problem = "problem_user";
        public static final String performance = "performance_glitch_user";
    }

    public class Password {
        public static final String for_all = "secret_sauce";
    }

    public class Message {
        public static final String locked_msg = "Epic sadface: Sorry, this user has been locked out.";
        public static final String empty_msg = "Epic sadface: Username is required";
        public static final String password_empty_msg = "Epic sadface: Password is required";
        public static final String not_access_msg = "Epic sadface: You can only access '/inventory.html' when you are logged in.";
    }
}
