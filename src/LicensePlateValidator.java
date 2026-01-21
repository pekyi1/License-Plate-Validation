import java.util.Arrays;

public class LicensePlateValidator {
    //valid plate GH-ABC-123
    /*
    * inputs:
    * - GH -ABD123
    * - abc123
    * - GH ABC123
    * - GH_ABC123
    * - GH AB23
     */

    static void main() {
        System.out.println(validatePlate("AB-12.34.56"));
    }

    static String validatePlate(String plate){
        if(plate.length() < 6){
            System.out.println("Wrong character count");
            return "";
        }

        if(!plate.matches("[\\sA-Za-z0-9_-]+")){
            System.out.println("Invalid characters");
            return "";
        }

        if (!hasRequiredLettersAndDigitsCounts(plate)){
            return "";
        }

        String[] parts = plate.split("[-_\\s]");
        String fullCode = "";
        if(parts.length == 2){
            String regionCode = parts[0];
            String mainCode = parts[1];
            if(validateRegion(regionCode) && validateMain(mainCode)){
                fullCode  = regionCode + "-" + formatMain(mainCode);
            }
        }
        else{
            String mainCode = parts[0];
            if(validateMain(mainCode)){
                fullCode = formatMain(mainCode);
            }
        }
        return fullCode;
    }

    private static String formatMain(String mainCode) {
        return (mainCode.substring(0,3) + "-" + mainCode.substring(3)).toUpperCase();
    }


    static boolean hasRequiredLettersAndDigitsCounts(String plate){
        int letterCount = 0;
        int numberCount = 0;

        for (int i = 0; i < plate.length(); i++) {
            if(Character.isLetter(plate.charAt(i)))letterCount++;
            if(Character.isDigit(plate.charAt(i)))numberCount++;
            if(letterCount >= 2 && numberCount >= 2) return true;
        }
        if(letterCount < 2){
            System.out.println("Insufficient letters");
        }else {
            System.out.println("Insufficient digits");
        }
        return false;
    }

    static boolean validateRegion(String regionCode){
        boolean res = regionCode.matches("[A-Z]{2}");
        if(!res){
            System.out.println("Invalid region code");
        }
        return res;
    }

    static boolean validateMain(String mainCode){
        boolean res =  mainCode.length() == 6;
        if(!res){
            System.out.println("Wrong character count");
        }
        return res;
    }

}