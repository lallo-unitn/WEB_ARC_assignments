package it.unitn.disi.web.ass1_reverse.RG;

public class Reverse {
    public static void main(String[] argc) {
        StringBuilder sb = new StringBuilder();
        for (String s : argc) {
            sb.append(" " + s);
        }
        sb.reverse();
        System.out.println(sb);
    }
}
