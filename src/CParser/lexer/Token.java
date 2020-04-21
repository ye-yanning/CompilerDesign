package CParser.lexer;
public class Token {
    public final int tag;
    public Token(int tagValue)
    {
        tag = tagValue;
    }
    public String transforToString()
    {
        return ""+ (char)tag;
    }

}
