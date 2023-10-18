public class Noun {
    /*           Singular   Plural
    Nominative   _____      _____
    Accusative   _____      _____
    Dative       _____      _____
    Genetive     _____      _____
    
                 _____      _____      _____      _____
                 _____      _____
                 
    */
    String gender;
    String definition;
    //individual storage
    String[][] declension = {
        {"", ""},
        {"", ""},
        {"", ""},
        {"", ""}
    };
    static boolean hasEnding(String word, String ending) {
        if (word.length() < ending.length()) return false;
        else {
            for (int i = 1; i <= ending.length(); i++) {
                if (word.charAt(word.length()-i) != ending.charAt(ending.length()-i)) break;
                if (i == ending.length()) return true;
            }
            return false;
        }
    }
    //takes ending and a boolean to attach to singular or plural
    static String[][] nAdd(String[][] declension) {
        char[] consonants = {'b','c','d','f','g','h','j','k','l','m','n','p','r','s','t','v','w','x','y','z'};
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < consonants.length; j++)
                if (declension[0][0].charAt(declension[0][0].length()-1) == j)
                    declension[i][0] = declension[i][0] + "e";
            declension[i][0] = declension[i][0] + "n";
        }
        return declension;
    }
    public Noun(String nom_s, String gen_s, String nom_p, String gender, String definition) {
        this.definition = definition;
        this.gender = gender;
        for (int i = 0; i < 4; i++) {
            this.declension[i][0] = nom_s;
            this.declension[i][1] = nom_p;
        }
        if (nom_s.charAt(nom_s.length()-1) != 'n' && (gen_s.charAt(gen_s.length()-1) == 'n' || hasEnding(gen_s,"ns")))
            declension = nAdd(declension);
        declension[3][0] = gen_s;
        char datPEnd = declension[2][1].charAt(declension[2][1].length()-1);
        if (!(datPEnd == 'n' || datPEnd == 's'))
            declension[2][1] = declension[2][1] + "n";
        if (nom_s.equals("Herz")) declension[1][0] = "Herz";
    }
    
    public void print() {
        System.out.println("\"" + definition + "\"");
        for (int i = 0; i < declension.length; i++) {
            for (int j = 0; j < declension[0].length; j++)
                System.out.print(declension[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}