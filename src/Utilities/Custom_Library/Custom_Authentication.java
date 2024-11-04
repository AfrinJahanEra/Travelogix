package Utilities.Custom_Library;

import java.io.*;
import java.security.NoSuchAlgorithmException;

public class Custom_Authentication{
    
    private String role;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;

    // Custom equalsIgnoreCase method
    private boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == str2) return true; // Same object or both null
        if (str1 == null || str2 == null) return false; // One is null
        if (length(str1) != length(str2)) return false; // Different lengths

        // Compare each character, ignoring case
        for (int i = 0; i < length(str1); i++) {
            char ch1 = charAt(str1, i);
            char ch2 = charAt(str2, i);
            if (ch1 >= 'A' && ch1 <= 'Z') ch1 += 'a' - 'A'; // Convert to lowercase
            if (ch2 >= 'A' && ch2 <= 'Z') ch2 += 'a' - 'A'; // Convert to lowercase
            if (ch1 != ch2) return false;
        }
        return true;
    }

    // Custom equals method

    }