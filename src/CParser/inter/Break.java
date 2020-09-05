package CParser.inter;

public class Break extends  Stmt{
    Stmt stmt;
    public Break(){
        if(Stmt.Enclosing == Stmt.Null)
            error(("unclosed break"));
        stmt=Stmt.Enclosing;
    }
    public void gen(int b,int a){
        if(Error)
            return;
        if(quad[quadNum]==null)
            quad[quadNum] = new quadruple();

        falselist = makelist(quadNum);
        trueList = makelist(quadNum+1);
        System.out.println("break表输出");
        ListPrint(nextlist);
        ListPrint(trueList);
        ListPrint(falselist);
        quad[quadNum].op="j";
        quad[quadNum].arg1="-";
        stmt.nextlist.add(quadNum);
        quad[quadNum++].arg2="-";
        //quad[quadNum++].result=Integer.toString(66666666);

        emit("goto L"+stmt.after);
        System.out.println("BREAKquan:"+quadNum);


    }
}
