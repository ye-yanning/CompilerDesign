package CParser.symbols;
import CParser.lexer.*;
public class Array extends Type{
    public Type of; //数组的元素类型
    public int size =1; //数组元素个数
    public Array(int sz, Type p)
    {
        super("[]",Tag.INDEX,sz*p.width);
        size=sz;
        of=p;
    }
    public String transforToString()
    {
        return "["+size+"]"+of.transforToString();
    }
}
