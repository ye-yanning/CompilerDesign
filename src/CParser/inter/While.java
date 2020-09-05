package CParser.inter;

import CParser.symbols.Type;

public class While extends Stmt{
    Expr expr;
    Stmt stmt;
    public While(){
        expr=null;
        stmt=null;
    }
    public void init(Expr x,Stmt s){
        expr=x;
        stmt=s;
        if(expr.type!= Type.Bool)
            expr.error("boolean required in while");
    }
    public void gen(int b,int a){
        if(Error)
            return;
        after=a;
        int sta = quadNum;
        expr.jumping(0,a);


        backpatch(stmt.nextlist,quadNum);       //backpatch(S1.nextlist,M1.instr)
        backpatch(expr.trueList,quadNum);       //backpatch(B。truelist,M2.instr)
        nextlist=expr.falselist;                //S.nextlist = B.falselist
        System.out.println("战术回填");
        ListPrint(nextlist);
        int label = newlabels();
        emitlabels(label);
        stmt.gen(label,b);
        System.out.println("WHILE特殊：");
        ListPrint(nextlist);
        quad[quadNum++]=new quadruple("j","-","-",Integer.toString(sta));

        emit("goto L"+b);

    }
}
