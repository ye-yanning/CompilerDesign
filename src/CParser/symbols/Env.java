package CParser.symbols;
import  java.util.*;
import CParser.lexer.*;
import CParser.inter.*;

public class Env {
    Hashtable table;
    Env prev;
    public Env(Env n)
    {
        table = new Hashtable();
        prev = n;
    }
    public void put(Token w, Id i)
    {
        table.put(w,i);
    }
    public Id get(Token w)
    {
        for(Env e = this;e!=null;e=e.prev){
            Id found = (Id)(e.table.get(w));
            if(found!=null)return found;
        }
        return null;
    }
}
