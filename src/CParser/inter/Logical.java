package CParser.inter;
import CParser.lexer.*;
import CParser.symbols.*;
public class Logical extends Expr{
    public Expr expr1,expr2;
    Logical(Token tok,Expr x1,Expr x2){
        super(tok,null);
        expr1 = x1;
        expr2 = x2;
        type = check(expr1.type,expr2.type);
        if(type==null)
            error("type error");
    }
    public Type check(Type p1,Type p2){
        if(p1 == Type.Bool && p2==Type.Bool)
            return Type.Bool;
        else return null;
    }
    public Expr gen(){
        int f = newlabels();
        int a = newlabels();
        Temp temp = new Temp(type);
        this.jumping(0,f);
        emit(temp.transforToString()+" = true");
        emit("goto L"+a);
        emitlabels(f);
        emit(temp.transforToString()+" = false");
        emitlabels(a);
        return temp;
    }
    public String transforToString(){
        return expr1.transforToString()+" "+op.transforToString()+" "+expr2.transforToString();
    }
}
