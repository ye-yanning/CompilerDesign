package CParser.inter;

import CParser.symbols.Type;

public class Do extends Stmt {
    Expr expr;
    Stmt stmt;
    public Do(){
        expr=null;
        stmt=null;
    }
    public void init(Stmt s,Expr x){
        expr=x;
        stmt=s;
        if(expr.type!= Type.Bool)
            expr.error("boolean required in do");
    }
    public void gen(int b,int a){
        if(Error)
            return;
        after = a;
        int backTrack = quadNum;
        quad[quadNum++]=new quadruple("j","-","-",Integer.toString(quadNum));

        int cycle = quadNum;
        int label=newlabels();
        stmt.gen(b,label);

        emitlabels(label);
        //if(quad[quadNum]==null)
        //    quad[quadNum]=new quadruple(null,null,null,Integer.toString(cycle-1));
       // else quad[quadNum].result=Integer.toString(cycle-1);
        //quad[backTrack].result = Integer.toString(quadNum-1);
        //DoBack = true;

        System.out.println("QQquanNum:"+quadNum);
        expr.jumping(b,0);
        System.out.println("aaquanNum:"+quadNum);
        backpatch(expr.trueList,backTrack);
        quad[quadNum-1].result = Integer.toString(quadNum);
        System.out.println("测试输出");
        ListPrint(expr.trueList);
        ListPrint(expr.falselist);
        ListPrint(expr.nextlist);

        ListPrint(stmt.trueList);
        ListPrint(stmt.falselist);
        ListPrint(stmt.nextlist);


       // backpatch(stmt.nextlist,quadNum);       //backpatch(S1.nextlist,M1.instr)
        //backpatch(expr.trueList,quadNum);       //backpatch(B。truelist,M2.instr)

    }
}
