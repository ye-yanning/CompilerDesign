package CParser.inter;
import CParser.lexer.*;
import CParser.symbols.*;
public class Access extends Op{
    public Id array;
    public Expr index;
    public Access(Id a,Expr i ,Type p){
        super(new Word("[]",Tag.INDEX),p);
        array = a;
        index = i;
    }
    public Expr gen(){
        return new Access(array,index.reduce(),type);
    }
    public void jumping(int t,int f){
        emitjumps(reduce().transforToString(),t,f);
    }
    public String transforToString(){
        return array.transforToString() +"["+index.transforToString()+"]";
    }
}
