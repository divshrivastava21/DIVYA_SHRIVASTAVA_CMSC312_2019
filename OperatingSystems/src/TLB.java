import java.util.*;
import java.lang.*;

public class TLB {

    private Hashtable tableTLB;
    private LinkedList <Integer> list = new LinkedList<>();

    public static void main(String[] args){
        TLB tlb = new TLB();
        for(int i = 0; i <= 16; i++){
            tlb.put(i, i);
        }
        System.out.println(tlb.get(0));
    }

    TLB(){
        this.tableTLB = new Hashtable();
        for(int i=0; i<16; i++){
            this.tableTLB.put(-i, -1);
            this.list.add(-i);
        }
    }

    public int get(int p_num){
        if(this.tableTLB.containsKey(p_num)){
            return (int) this.tableTLB.get(p_num);
        }
        else{
            return -1;
        }
    }

    public void put(int p_num, int f_num){

        Integer i = this.list.poll();
        if(i != null){
            // remove this item from hashtable
            this.tableTLB.remove(i.intValue());
        }

        this.list.add(p_num);
        this.tableTLB.put(p_num, f_num);
    }
}
