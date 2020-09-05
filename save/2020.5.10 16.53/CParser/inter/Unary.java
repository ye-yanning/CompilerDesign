package CParser.inter;
import CParser.lexer.*;
import CParser.symbols.*;
//针对单目运算符
public class Unary extends Op{
    public Expr expr;
    public Unary(Token tok, Expr x){
        super(tok,null);
        expr=x;
        type=Type.max(Type.Int,expr.type);
        if(type==null)error("type error");
    }
    public  Expr gen()
    {
        return  new Unary(op, expr.reduce());
    }
    public String transforToString()
    {
        quad[quadNum].op=op.transforToString();
        quad[quadNum].arg1=expr.transforToString();
        return op.transforToString()+" "+expr.transforToString();
    }
}
