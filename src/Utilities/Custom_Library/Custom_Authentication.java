package Utilities.Custom_Library;

import java.io.*;
import java.security.NoSuchAlgorithmException;

public class Custom_Authentication {

    private String role;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;

    
    private boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == str2) return true; 
        if (str1 == null || str2 == null) return false; 
        if (custom_length(str1) != custom_length(str2)) return false; 

       
        for (int i = 0; i < custom_length(str1); i++) {
            char ch1 = charAt(str1, i);
            char ch2 = charAt(str2, i);
            if (ch1 >= 'A' && ch1 <= 'Z') ch1 += 'a' - 'A'; 
            if (ch2 >= 'A' && ch2 <= 'Z') ch2 += 'a' - 'A'; 
            if (ch1 != ch2) return false;
        }
        return true;
    }



    
    private boolean equals(String str1, String str2) {
        if (str1 == str2) return true; 
        if (str1 == null || str2 == null) return false; 
        if (custom_length(str1) != custom_length(str2)) return false; 

        
        for (int i = 0; i < custom_length(str1); i++) {
            if (charAt(str1, i) != charAt(str2, i)) return false;
        }
        return true;
    }
    

    
    @Override
    public String toString() {
        return "Role: " + role + ", Name: " + name + ", Phone Number: " + phoneNumber + ", Email: " + email;
    }

    
    public String readPassword(String prompt) {
        StringBuilder password = new StringBuilder();
        System.out.print(prompt);
        try {
            while (true) {
                int input = System.in.read();
                if (input == '\n' || input == '\r')
                    break;
                password.append((char) input);
                System.out.print("*"); 
            }
        } catch (IOException e) {
            System.out.println("Error reading password");
        }
        return password.toString();
    }

    
    public static class CustomHash {
        private final int[] hashValues = { 0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476 };

        public CustomHash getInstance() {
            return new CustomHash();
        }

        public int[] digest(byte[] input) {
            int[] result = hashValues.clone();
            for (int i = 0; i < input.length; i++) {
                result[i % result.length] ^= input[i];
            }
            return result;
        }
    }

    
    public String encryptPassword(String password) throws NoSuchAlgorithmException {
        CustomHash customHash = new CustomHash().getInstance();
        byte[] passwordBytes = getBytes(password);
        int[] hashedValues = customHash.digest(passwordBytes);

        StringBuilder hexString = new StringBuilder();
        for (int value : hashedValues) {
            String hex = Integer.toHexString(value);
            hexString.append(hex);
        }

        return hexString.toString();
    }

    
    private byte[] getBytes(String input) {
        byte[] bytes = new byte[custom_length(input)];
        for (int i = 0; i < custom_length(input); i++) {
            bytes[i] = (byte) charAt(input, i); // Convert char to byte
        }
        return bytes;
    }

    
    public char charAt(String str, int index) {
        if (index < 0 || index >= custom_length(str)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + custom_length(str));
        }

        char[] charArray = custom_toCharArray(str); 
        return charArray[index]; 
    }

    
    public int custom_length(String str) {
        int count = 0;
        try {
            while (true) {
                charAt(str, count); 
                count++;
            }
        } catch (IndexOutOfBoundsException e) {
            
        }
        return count;
    }

    
    public char[] custom_toCharArray(String str) {
        int len = custom_length(str);
        char[] charArray = new char[len];

        for (int i = 0; i < len; i++) {
            charArray[i] = charAt(str, i); 
        }
        return charArray;
    }
}