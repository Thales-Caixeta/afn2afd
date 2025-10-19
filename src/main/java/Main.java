import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("     CONVERSOR AFN  →  AFD");
        System.out.println("====================================\n");

        // Estados
        Set<String> Q = readSet("Informe o conjunto de estados (Q):");
        Set<String> Sigma = readSet("Informe o alfabeto (Σ) [sem ε]:");
        String q0 = readOne("Informe o estado inicial (q0):");
        if (!Q.contains(q0)) die("O estado inicial precisa estar em Q.");

        Set<String> F = readSet("Informe o conjunto de estados finais (F):");
        if (!Q.containsAll(F)) die("Todos os estados finais precisam estar em Q.");

        // Transições
        System.out.println("\nAgora informe as transições do AFN:");
        System.out.println("Formato: origem,simbolo,destino");
        System.out.println("Use 'ε', 'e', 'eps' ou 'epsilon' para epsilon.");
        System.out.print("Quantidade de transições: ");
        int n = readInt();

        List<Transicao> T = new ArrayList<>();
        System.out.println();
        for (int i = 1; i <= n; i++) {
            System.out.print("Transição " + i + ": ");
            String s = SC.nextLine().trim();
            String[] p = s.split(",", -1);
            if (p.length != 3) die("Formato inválido. Use origem,simbolo,destino.");
            String o = p[0].trim();
            String a = normalizeEps(p[1].trim());
            String d = p[2].trim();
            if (!Q.contains(o) || !Q.contains(d)) die("Estado inexistente em Q.");
            if (!a.equals("ε") && !Sigma.contains(a)) die("Símbolo fora de Σ.");
            T.add(new Transicao(o, a, d));
        }

        System.out.println("\n====================================");
        System.out.println(" Processando conversão AFN → AFD...");
        System.out.println("====================================\n");

        AutomatoNFA afn = new AutomatoNFA(Q, Sigma, q0, F);
        for (Transicao tr : T) afn.add(tr.origem(), tr.simbolo(), tr.destino());
        AutomatoDFA afd = Conversor.converter(afn);

        Printer.printQuintuplaDFA(afd);
        Printer.printDeltaDFA(afd);

        System.out.println("\n====================================");
        System.out.println(" Conversão finalizada com sucesso!");
        System.out.println("====================================");
    }

    static Set<String> readSet(String prompt) {
        System.out.println();
        System.out.println(prompt);
        System.out.print("> ");
        String s = SC.nextLine().trim();
        if (s.isEmpty()) return new LinkedHashSet<>();
        return Arrays.stream(s.split(","))
                .map(String::trim)
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    static String readOne(String prompt) {
        System.out.println();
        System.out.println(prompt);
        System.out.print("> ");
        String s = SC.nextLine().trim();
        if (s.isEmpty()) die("Valor vazio.");
        return s;
    }

    static int readInt() {
        while (true) {
            String s = SC.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.out.print("Número inválido. Tente novamente: ");
            }
        }
    }

    static void die(String msg) {
        System.out.println("\n[ERRO] " + msg);
        System.exit(1);
    }

    static String normalizeEps(String s) {
        String x = s.toLowerCase(Locale.ROOT);
        if (x.equals("ε") || x.equals("e") || x.equals("eps") || x.equals("epsilon")) return "ε";
        return s;
    }
}
