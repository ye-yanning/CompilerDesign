package CParser.lexer;
//Real类，用于处理浮点数
public class Real extends Token{
    public final  float value;
    public  Real(float v)
    {
        super(Tag.REAL);
        value=v;
    }
    public String transforToString()
    {
        return ""+value;
    }
}
