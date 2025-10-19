import java.util.*;

public class Conversor {
    public static AutomatoDFA converter(AutomatoNFA nfa) {
        Set<String> Sigma = new LinkedHashSet<>(nfa.alfabeto());
        Set<String> startSet = nfa.eClosure(Set.of(nfa.inicial()));
        String startName = nome(startSet);

        Set<String> Qd = new LinkedHashSet<>();
        Set<String> Fd = new LinkedHashSet<>();
        Map<String, Map<String, String>> deltad = new LinkedHashMap<>();
        Map<String, Set<String>> nomeParaConjunto = new LinkedHashMap<>();

        Deque<Set<String>> fila = new ArrayDeque<>();
        fila.add(startSet);
        Qd.add(startName);
        nomeParaConjunto.put(startName, startSet);
        if (!Collections.disjoint(startSet, nfa.finais())) Fd.add(startName);

        while (!fila.isEmpty()) {
            Set<String> T = fila.removeFirst();
            String Tname = nome(T);
            deltad.putIfAbsent(Tname, new LinkedHashMap<>());

            for (String a : Sigma) {
                Set<String> U = nfa.eClosure(nfa.move(T, a));
                if (U.isEmpty()) continue;
                String Uname = nome(U);
                deltad.get(Tname).put(a, Uname);
                if (!nomeParaConjunto.containsKey(Uname)) {
                    nomeParaConjunto.put(Uname, U);
                    Qd.add(Uname);
                    fila.add(U);
                    if (!Collections.disjoint(U, nfa.finais())) Fd.add(Uname);
                }
            }
        }
        return new AutomatoDFA(Qd, Sigma, startName, Fd, deltad);
    }

    static String nome(Set<String> S) {
        if (S == null || S.isEmpty()) return "{}";
        List<String> l = new ArrayList<>(S);
        Collections.sort(l);
        return "{" + String.join(",", l) + "}";
    }
}
