package CParser.inter;
import CParser.symbols.*;

import java.util.Vector;

public class Else extends Stmt {
    Expr expr;
    Stmt stmt1,stmt2;
    public Else(Expr x,Stmt s1,Stmt s2){
        expr = x;
        stmt1 = s1;
        stmt2 = s2;
        if(expr.type!=Type.Bool)
            expr.error("boolean required in if");

    }
    public void gen(int b,int a){
        if(Error)
            return;
        System.out.println("elseGen:"+expr.transforToString());


        //Vector<Integer> temp = merge
        int label1 = newlabels();
        int label2 = newlabels();
        expr.jumping(0,label2);
        System.out.println("elsePrint");


        emitlabels(label1);
        backpatch(expr.trueList,quadNum);       //backpatch(B.truelist, M1.instr)
        stmt1.gen(label1,a);



        ListPrint(expr.trueList);
        ListPrint(expr.falselist);


        Vector<Integer> Nnextlist = makelist(quadNum);              //N.nextlist = makelist(nextinstr)
        if(quad[quadNum]==null)
            quad[quadNum++]=new quadruple("jeee","-","-",null);
        backpatch(expr.falselist,quadNum);      //backpatch(B.falselist,M2.instr)

        System.out.println("N.nextlist:");
        ListPrint(Nnextlist);
        Vector<Integer> templist = merge(Nnextlist,stmt1.nextlist); //temp = merge(S1.nextlist,N.nextlist)
        System.out.println("Ntemplist:");
        ListPrint(templist);

        emit("goto L"+a);
        emitlabels(label2);
        stmt2.gen(label2,a);
        nextlist = merge(templist,stmt2.nextlist);      //S.nextlist = merge(temp,S.nextlist)
    }
}
