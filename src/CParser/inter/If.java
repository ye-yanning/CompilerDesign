package CParser.inter;

import CParser.symbols.Type;

import java.util.List;

public class If extends Stmt{
    Expr expr;
    Stmt stmt;
    public If(Expr x,Stmt s){
        expr = x;
        stmt = s;
        //System.out.println("iftestAa:"+expr.transforToString());
        if(expr.type!= Type.Bool)
            expr.error("boolean required in if");
    }
    public void gen(int b,int a){
        if(Error)
            return;
        //System.out.println("iftest:"+expr.transforToString());
            //backpatch(B.truelist, M.instr)

        int label=newlabels();
        expr.jumping(0,a);

        ListPrint(expr.trueList);

        backpatch(expr.trueList,quadNum);                   //backpatch(B.truelist,M.instr)
        emitlabels(label);
        stmt.gen(label,a);
        nextlist = merge(expr.falselist,stmt.nextlist);     //S.nextlist = merge(B.falselist, S1.nextlist)
        System.out.println("IfNext");
        ListPrint(expr.falselist);
        ListPrint(stmt.nextlist);
        ListPrint(nextlist);
        //ListPrint(nextlist);

    }

}

