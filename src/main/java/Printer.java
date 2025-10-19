import java.util.*;

public class Printer {
    public static void printQuintuplaDFA(AutomatoDFA dfa) {
        System.out.println("\n=== AFD (quíntupla) ===");
        System.out.println("Q  = " + dfa.estados());
        System.out.println("Σ  = " + dfa.alfabeto());
        System.out.println("q0 = " + dfa.inicial());
        System.out.println("F  = " + dfa.finais());
        System.out.println("δ  = abaixo");
    }

    public static void printDeltaDFA(AutomatoDFA dfa) {
        System.out.println("\nδ (transições):");
        Map<String, Map<String, String>> d = dfa.transicoes();
        List<String> estados = new ArrayList<>(dfa.estados());
        Collections.sort(estados);
        List<String> alfa = new ArrayList<>(dfa.alfabeto());
        Collections.sort(alfa);
        for (String q : estados) {
            Map<String, String> m = d.getOrDefault(q, Map.of());
            for (String a : alfa) {
                String t = m.get(a);
                if (t != null) System.out.println("(" + q + ", " + a + ") -> " + t);
            }
        }
    }
}
