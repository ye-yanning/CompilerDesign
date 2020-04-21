package CParser.lexer;
//Num类，继承Token类，在此基础上定义了一个转换value为String的函数
public class Num extends Token{
    public final int value;
    public Num(int v)
    {
        super(Tag.NUM);
        value=v;
    }
    public String transforToString(){
        return ""+value;
    }
}
