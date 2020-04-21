package CParser.lexer;

public class Word extends Token{
    public String lexeme = "";
    public  Word(String s, int tag)
    {
        super(tag);
        lexeme=s;
    }
    public String transforToString()
    {
        return lexeme;
    }
    public static final Word
    and = new Word("&&", Tag.AND),
    or = new Word("||", Tag.OR),
    ne = new Word("!=",Tag.NE),
    equal= new Word("==", Tag.EQUAL),
    ge = new Word(">=",Tag.GE),
    le = new Word("<=", Tag.LE),
    minus = new Word("minus",Tag.MINUS),
    True = new Word("true",Tag.TRUE),
    False = new Word("false", Tag.FALSE),
    temp = new Word("t", Tag.TEMP);

}
