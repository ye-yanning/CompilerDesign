package CParser.inter;
import CParser.lexer.*;
import CParser.symbols.Type;

public class Id extends Expr{
    public int offset;
    public Id(Word id, Type p, int b)
    {
        super(id,p);
        offset=b;
    }
}
