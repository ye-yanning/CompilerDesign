package CParser.inter;
import CParser.lexer.*;

import java.util.Vector;

public class Or extends Logical{
    public Or(Token tok,Expr x1,Expr x2){
        super(tok,x1,x2);
    }
    public void jumping(int t,int f)
    {
        System.out.println("ORquanNum:"+quadNum);
        backpatch(expr1.falselist,quadNum);                //backpatch(B1.falselist, M.instr)
        trueList = merge(expr1.trueList,expr2.trueList);  //B.truelist = merge(B1.truelist,B2.truelist)
        falselist = equal(expr2.falselist);               //B.falselist = B2.falselist

        int Remain = quadNum;
        //System.out.println("OR t:\"+t+\" f:\"+f+\" quadNum:"+quadNum);
        int label = t!=0 ?t :newlabels();
        expr1.jumping(label,0);
        expr2.jumping(t,f);
        if(t==0)
            emitlabels(label);
        quad[Remain].result=Integer.toString(quadNum);
        //System.out.println("ORAFTER t:"+t+" f:"+f+" quadNum:"+quadNum);
    }
}
