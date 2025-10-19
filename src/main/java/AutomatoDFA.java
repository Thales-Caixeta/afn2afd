import java.util.*;

public class AutomatoDFA {
    private final Set<String> Q;
    private final Set<String> Sigma;
    private final String q0;
    private final Set<String> F;
    private final Map<String, Map<String, String>> delta;

    public AutomatoDFA(Set<String> Q, Set<String> Sigma, String q0, Set<String> F, Map<String, Map<String, String>> delta) {
        this.Q = Q;
        this.Sigma = Sigma;
        this.q0 = q0;
        this.F = F;
        this.delta = delta;
    }

    public Set<String> estados() { return Q; }
    public Set<String> alfabeto() { return Sigma; }
    public String inicial() { return q0; }
    public Set<String> finais() { return F; }
    public Map<String, Map<String, String>> transicoes() { return delta; }
}
