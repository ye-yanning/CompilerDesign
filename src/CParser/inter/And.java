package CParser.inter;
import CParser.lexer.*;

import java.util.Vector;

public class And extends Logical{
    public And(Token tok,Expr x1,Expr x2){
        super(tok,x1,x2);
    }
    public void jumping(int t,int f){
        System.out.println("ANDPrint:"+this.expr1.transforToString()+" "+op.transforToString()+" "+this.expr2.transforToString());
        System.out.println("ANDquanNum:"+quadNum);






        //int Remain = quadNum;
        int label = f!=0 ? f:newlabels();
        expr1.jumping(0,label);
        backpatch(expr1.trueList,quadNum);                           //backpatch(B1.truelist, M.instr)
        //quad[Remain].result=Integer.toString(quadNum);
        expr2.jumping(t,f);
        falselist = merge(expr1. falselist,expr2. falselist);        //B.falselist = merge(B1.falselist, B2.falselist)
        trueList=equal(expr2.trueList);                              // B.truelist = B2.truelist

        if(f==0)
            emitlabels(label);


    }

}
