package CParser.inter;
import CParser.lexer.*;
import CParser.symbols.*;
public class Temp extends Expr{
    static int count = 0;
    int number=0;
    public Temp(Type p){
        super(Word.temp,p);
        number = ++count;
    }
    public String transforToString()
    {
        return  "t"+number;
    }
}
