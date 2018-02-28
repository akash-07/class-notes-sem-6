import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by @kash on 2/28/2018.
 */
public class Parser {
    /*
    * Note:
    * epsilon : @
    * S' : R
    * Grammar:
    * S  -> iEtSS' | a
    * S' -> eS | epsilon
    * E -> b
    * */
    Map<Character, Integer> terminalMap;
    Map<Character, Integer> nonterminalMap;
    String[][] parsingTable =   {
            {"S->a", "", "", "S->iEtSR", "", ""},
            {"", "", "R->eS", "", "", "R->"},
            {"", "E->b", "", "", "", "", ""}};

    Parser()    {
        //initializing terminal map
        terminalMap = new HashMap<>();
        terminalMap.put('a', 0);
        terminalMap.put('b', 1);
        terminalMap.put('e', 2);
        terminalMap.put('i', 3);
        terminalMap.put('t', 4);
        terminalMap.put('$', 5);

        //initializing nonterminal map
        nonterminalMap = new HashMap<>();
        nonterminalMap.put('S', 0);
        nonterminalMap.put('R', 1);
        nonterminalMap.put('E', 2);
    }

    boolean isTerminal(Character c)    {
        if(c <= 91 && c >= 65)
            return true;
        else return false;
    }

    //list of tokens is terminated by a '$'
    boolean parse(List<Character> tokens)    {
        //initialization
        int index = 0;
        List<Character> stack = new ArrayList<>();
        stack.add('$');
        stack.add('S');
        Character a = tokens.get(0);
        Character X = stack.get(stack.size() - 1);
        printHeaders();
        while(X != '$') {
            printStuff(tokens, index, stack);
            if (a=='@'){
                System.out.println("Parsing Error");
                return false;
            }
            if(X == a)  {
                index++;
                a = tokens.get(index);
                stack.remove(stack.size() - 1);
            }
            else if (isTerminal(a))  {
                System.out.println("PARSING ERROR!");
                return false;
            }
            else if(parsingTable[nonterminalMap.get(X)][terminalMap.get(a)].equals(""))    {
                System.out.println("PARSING ERROR!");
                return false;
            }
            else    {
                String prod = parsingTable[nonterminalMap.get(X)][terminalMap.get(a)];
                //System.out.println("\nAction: " + prod);
                System.out.print(prod);
                String rhs = prod.substring(3);
                int length = rhs.length();
                stack.remove(stack.size() - 1);
                for(int i = length - 1; i >= 0; i--)    {
                    stack.add(rhs.charAt(i));
                }
            }
            X = stack.get(stack.size() - 1);
            System.out.println("\n___________________________________________________________________");
        }
        if(tokens.get(index) == '$')
            return true;
        System.out.println("PARSING ERROR!");
        return false;
    }

    void printStuff(List<Character> tokens, int index, List<Character> stack)   {
       //System.out.println("\nMatched: ");
       for(int i = 0; i < index ; i++)  {
           System.out.print(tokens.get(i));
       }
       System.out.print("\t\t\t\t");
       //System.out.println("\nStack: ");
       for(int i = stack.size() - 1; i >= 0; i--)    {
           System.out.print(stack.get(i));
       }
       System.out.print("\t\t\t\t");
       //System.out.println("\nInput: ");
       for(int i = index; i < tokens.size(); i++)   {
           System.out.print(tokens.get(i));
       }
       System.out.print("\t\t\t\t");
    }

    void printHeaders() {
        System.out.println("Matched\t\t\tStack\t\t\tInput\t\t\tAction");
        System.out.println("___________________________________________________________________");
        System.out.println("-------------------------------------------------------------------");
    }

    public static void main(String[] args) throws IOException {
        String fileName = "./src/inp.cpp";
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        lexer mylex = new lexer();
        List<Character> tokens = mylex.getTokens(data);
        /*
        tokens.add('i');
        tokens.add('b');
        tokens.add('t');
        tokens.add('i');
        tokens.add('b');
        tokens.add('t');
        tokens.add('a');
        tokens.add('$');
        */
        Parser parser = new Parser();
        parser.parse(tokens);

    }
}
