import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by @kash on 2/28/2018.
 */
public class lexer {

    Map<String, Character> token_map = new HashMap<>();
    lexer(){


        token_map.put("if", 'i');
        token_map.put("then", 't');
        token_map.put("else", 'e');
        token_map.put("a", 'a');
        token_map.put("if", 'i');
        token_map.put("true",'b');
        token_map.put("false",'b');

    }
    public List<Character> getTokens(String inp){
        List<Character> tokens = new ArrayList<>();
        String[] split_inp = inp.split("\\W+");
        for(String tok:split_inp){
            if(token_map.get(tok) != null) {
                tokens.add(token_map.get(tok));
                System.out.println(tokens);
            }
            else{
                tokens.add('@');
            }
        }
        tokens.add('$');
        return tokens;
    }
}
