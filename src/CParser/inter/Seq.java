package CParser.inter;

import java.util.List;

public class Seq extends Stmt{
    Stmt stmt1,stmt2;
    public Seq(Stmt s1,Stmt s2){
        stmt1 = s1;
        stmt2 = s2;
    }
    public void gen(int b,int a){
        //System.out.println("Seq gen b:"+b+" a:"+a);

        //System.out.println("SeqNext");
        if(Error)
            return;
        if(stmt1 == Stmt.Null)
        {
            stmt2.gen(b,a);
            //System.out.println("SeqNext1");
            //ListPrint(nextlist);
            nextlist=null;          //S.nextlist = null
        }
        else if(stmt2 == Stmt.Null)
        {
            if(Error)
                return;
            stmt1.gen(b,a);

            nextlist = stmt1.nextlist;  //S.nextlist = L.nextlist
            backpatch(nextlist,quadNum);
            //System.out.println("SeqNext2");
            //ListPrint(stmt1.nextlist);
        }
        else{
            int label=newlabels();

            stmt1.gen(b,label);
            backpatch(stmt1.nextlist,quadNum);
            emitlabels(label);
            stmt2.gen(label,a);

            //System.out.println("SeqNext3");
            //ListPrint(nextlist);
        }
//System.out.println("Check!!!");
//ListPrint(nextlist);
//ListPrint(stmt1.nextlist);
//ListPrint(stmt2.nextlist);
    }
}
