import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    static void ask(Noun noun) {
        String[][] ein = {
            {"ein","einen","einem","eines"}, // masc
            {"ein","ein","einem","eines"}, // neut
            {"eine","eine","einer","einer"}, // fem
            {"keine","keine","keinem","keiner"} // plural
        };
        String[][] der = {
            {"der","den","dem","des"},
            {"das","das","dem","des"},
            {"die","die","der","den"},
            {"die","die","der","der"}
        };
        String[] accDeu = {"bis","durch","fuer","ohne","gegen","um","entlang"};
        String[] accEng = {"up to","through","for","without","against","around","along"};
        String[] datDeu = {"aus","ausser","gegenueber","bei","mit","nach","zu","von"}; // also seit - since
        String[] datEng = {"out of","except for","across from","at","with","after","to","from"};
        String[] genDeu = {"anstatt","trotz","wegen","ausserhalb","innerhalb","oberhalb","unterhalb","diesseits","jenseits","beiderseits"}; // also während - during
        String[] genEng = {"instead of","despite","because of","outside of","inside of","above","below","on this side of","on the other side of","on both sides of"};
        boolean definite = (Math.random() < 0.5);
        boolean plural = (Math.random() < 0.5);
        int caseDeu = (int)(Math.random()*4);
        int gender = 0; // 0 = masc, 1 = neut, 2 = fem
        if (noun.gender.equals("n")) gender++;
        else if (noun.gender.equals("f")) gender = 2;
        int prep;
        String Deutsch = "";
        String English = "";
        if (caseDeu == 1) {
            prep = (int)(Math.random()*accDeu.length);
            Deutsch = accDeu[prep];
            English = accEng[prep];
        } else if (caseDeu == 2) {
            prep = (int)(Math.random()*datDeu.length);
            Deutsch = datDeu[prep];
            English = datEng[prep];
        } else if (caseDeu == 3) {
            prep = (int)(Math.random()*genDeu.length);
            Deutsch = genDeu[prep];
            English = genEng[prep];
        }
        if (caseDeu > 0) {
            Deutsch = Deutsch + " ";
            English = English + " ";
        }
        if (definite) {
            English = English + "the ";
            if (plural) Deutsch = Deutsch + der[3][caseDeu] + " ";
            else Deutsch = Deutsch + der[gender][caseDeu] + " ";
        } else {
            if (plural) Deutsch = Deutsch + ein[3][caseDeu] + " ";
            else {
                Deutsch = Deutsch + ein[gender][caseDeu] + " ";
                English = English + "a ";
            }
        }
        Deutsch = Deutsch + noun.declension[caseDeu][plural ? 1 : 0];
        if (noun.definition.equals("man") && plural) English = English + "men";
        else {
            English = English + noun.definition;
            if (plural) English = English + "s";
        }
        Scanner in = new Scanner(System.in);
        System.out.print("Translate \"" + English + "\" into german.\n>>");
        String response = in.nextLine();
        if (response.equals(Deutsch)) System.out.println("Correct!");
        else System.out.println("The correct answer was \"" + Deutsch + ".\"");
    };
	
    //ask a question about declining a random noun from the vocab list
    public static void main(String[] args) throws FileNotFoundException {
        Scanner vocabReader = new Scanner(new File("vocab.txt"));
        List<Noun> nouns = new ArrayList<Noun>();
        String line;
        String[] feed = new String[5];
        String gender = "";
        String stem = "";
        String root;
        do {
            line = vocabReader.nextLine();
        } while (!line.equals("nouns {"));
        do {
            line = vocabReader.nextLine();
            if (line.charAt(0) == '#') continue;
            if (line.equals("}")) break;
            feed = line.split(" ");
            nouns.add(new Noun(feed[0], feed[1], feed[2], feed[3], feed[4]));
		} while (vocabReader.hasNextLine() && !line.equals("}"));
        for (int i = 0; i < 10; i++) {
            ask(nouns.get((int)(Math.random()*nouns.size())));
        }
    }
}