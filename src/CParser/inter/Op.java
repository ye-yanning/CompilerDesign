package CParser.inter;
import CParser.lexer.*;
import CParser.symbols.*;
public class Op extends Expr {
    public Op(Token tok, Type p){
        super(tok,p);
    }
    public Expr reduce(){
        Expr x = gen();
        Temp t = new Temp(type);
        emit(t.transforToString()+" = "+x.transforToString());
        return t;
    }
}
