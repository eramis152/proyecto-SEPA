package com.sepa.back_end.util;

public class test {


    public static String replaceSlashWithDash(String input) {
        // Utilizamos el método replace() para cambiar todas las '/' por '-'
        return input.replace('/', '-');
    }

    public static void main(String[] args) {
        String original = "2024/10/23";
        String modified = replaceSlashWithDash(original);
        System.out.println("Original: " + original);
        System.out.println("Modificado: " + modified);
    }
    
    
}
