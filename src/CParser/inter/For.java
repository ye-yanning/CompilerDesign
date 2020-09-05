package CParser.inter;
import CParser.lexer.*;
import CParser.symbols.*;
import CParser.symbols.Type;
public class For extends Stmt{
    Expr expr;
    Stmt stmt1;
    Stmt stmt2;
    Stmt stmt;
    public For(){
        expr = null;
        stmt = null;
        stmt1 = null;
        stmt2 = null;
    }
    public void init(Expr x,Stmt s,Stmt s1,Stmt s2){
        expr = x;
        stmt = s;
        stmt1 = s1;
        stmt2 = s2;
        if(expr.type != Type.Bool)
            expr.error("boolean required in if");
        //System.out.println(stmt.t);
    }
    public void gen(int b, int a){
        if(Error)
            return;
        stmt.gen(0,0);
        int sta = quadNum;
        expr.jumping(0,a);
        nextlist=expr.falselist;
        backpatch(stmt2.nextlist,quadNum);
        backpatch(expr.trueList,quadNum);
        int label = newlabels();
        emitlabels(label);
        stmt2.gen(label,b);
        stmt1.gen(0,0);
        quad[quadNum++]=new quadruple("j","-","-",Integer.toString(sta));
        emit("goto L"+b);

    }
}
