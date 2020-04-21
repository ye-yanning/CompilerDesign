package CParser.main;

import CParser.inter.Node;
import CParser.lexer.Lexer;
import CParser.parser.Parser;

import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        Lexer lex = new Lexer();
        Parser parser = new Parser(lex);


        parser.program();
        System.out.write('\n');
        System.out.println("the End?");
        Node n=new Node();
        n.InfoPrint();

    }
}
