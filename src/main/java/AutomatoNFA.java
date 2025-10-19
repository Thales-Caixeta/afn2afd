import java.util.*;

public class AutomatoNFA {
    public static final String EPS = "ε";
    private final Set<String> Q;
    private final Set<String> Sigma;
    private final String q0;
    private final Set<String> F;
    private final Map<String, Map<String, Set<String>>> delta;

    public AutomatoNFA(Set<String> Q, Set<String> Sigma, String q0, Set<String> F) {
        this.Q = new LinkedHashSet<>(Q);
        this.Sigma = new LinkedHashSet<>(Sigma);
        this.q0 = q0;
        this.F = new LinkedHashSet<>(F);
        this.delta = new LinkedHashMap<>();
        for (String q : Q) delta.put(q, new LinkedHashMap<>());
    }

    public void add(String origem, String simbolo, String destino) {
        delta.computeIfAbsent(origem, k -> new LinkedHashMap<>());
        delta.get(origem).computeIfAbsent(simbolo, k -> new LinkedHashSet<>()).add(destino);
    }

    public Set<String> estados() { return Q; }
    public Set<String> alfabeto() { return Sigma; }
    public String inicial() { return q0; }
    public Set<String> finais() { return F; }
    public Map<String, Map<String, Set<String>>> transicoes() { return delta; }

    public Set<String> eClosure(String q) {
        Set<String> c = new LinkedHashSet<>();
        Deque<String> st = new ArrayDeque<>();
        c.add(q);
        st.push(q);
        while (!st.isEmpty()) {
            String x = st.pop();
            Set<String> m = delta.getOrDefault(x, Map.of()).getOrDefault(EPS, Set.of());
            for (String y : m) if (c.add(y)) st.push(y);
        }
        return c;
    }

    public Set<String> eClosure(Set<String> S) {
        Set<String> r = new LinkedHashSet<>();
        for (String s : S) r.addAll(eClosure(s));
        return r;
    }

    public Set<String> move(Set<String> S, String a) {
        Set<String> r = new LinkedHashSet<>();
        for (String s : S) {
            Set<String> d = delta.getOrDefault(s, Map.of()).getOrDefault(a, Set.of());
            r.addAll(d);
        }
        return r;
    }
}
