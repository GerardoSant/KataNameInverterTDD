import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NameInverter {

    public static String invert(String fullName) {
        List<String> nameParts = splitNameByWhiteSpaces(fullName);
        return thereIsOnlyFirstName(nameParts) ? fullName.trim() : invert(withoutHonorifics(nameParts));
    }

    private static boolean thereIsOnlyFirstName(List<String> nameParts) {
        return nameParts.size()<2;
    }

    private static List<String> withoutHonorifics(List<String> nameParts) {
        if (Honorifics.isHonorific(nameParts.get(0))){
            nameParts.remove(0);
        }
        return nameParts;
    }

    private static List<String> splitNameByWhiteSpaces(String name) {
        return new ArrayList<String>(Arrays.asList(name.trim().split(RegularExpression.anyAmountOfWhiteSpaces())));
    }

    private static String invert(List<String> nameParts) {
        String firstName=nameParts.get(0);
        String lastName = nameParts.get(1);
        return String.format("%s,%s %s", lastName,firstName,postNominals(nameParts)).trim();
    }

    private static String postNominals(List<String> nameParts) {
        if (nameParts.size()>2){
            return getPostNominals(nameParts);
        }
        return "";
    }

    private static String getPostNominals(List<String> nameParts) {
        String postnominals = "";
        for (int i=2;i<nameParts.size();i++){
            postnominals+= nameParts.get(i) + " ";
        }
        return postnominals;
    }
}

class RegularExpression{
    public static String anyAmountOfWhiteSpaces() {
        return "\\s+";
    }
}

class Honorifics{

    public static boolean isHonorific(String honorific) {
        return honorific.contains(".");
    }
}
