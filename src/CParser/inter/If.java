package CParser.inter;

import CParser.symbols.Type;

public class If extends Stmt{
    Expr expr;
    Stmt stmt;
    public If(Expr x,Stmt s){
        expr = x;
        stmt = s;
        if(expr.type!= Type.Bool)
            expr.error("boolean required in if");
    }
    public void gen(int b,int a){
        int label=newlabels();
        expr.jumping(0,a);
        emitlabels(label);
        stmt.gen(label,a);
    }

}
