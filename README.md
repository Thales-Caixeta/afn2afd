# Conversor AFN → AFD 🧠

Trabalho de **Linguagens Formais e Autômatos (LFA)**  
PUC-GO — Desenvolvido por *Thales Caixeta*.

---

## 📖 Descrição

Programa em **Java** que converte um **Autômato Finito Não Determinístico (AFN)** em um **Autômato Finito Determinístico (AFD)**.  
O usuário informa o alfabeto, os estados, as transições e o estado inicial, e o sistema gera a **quíntupla do AFD** resultante.

---

## ⚙️ Execução

### 🧩 Compilação
Abra o terminal (PowerShell 7 recomendado) na pasta do projeto e rode:

```powershell
javac .\src\main\java\*.java
```

### 🚀 Execução
```powershell
java -cp .\src\main\java Main
```

---

## 💡 Exemplo de entrada

```
Q (estados, vírgula):
q0,q1,q2

Σ (alfabeto, vírgula; sem ε):
a,b

q0:
q0

F (finais, vírgula; pode vazio):
q2

Quantidade de transições:
5
Transições no formato origem,simbolo,destino:
q0,ε,q1
q1,a,q1
q1,b,q1
q1,a,q2
q2,b,q2
```

---

## 📄 Resultado esperado

```
=== AFD (quíntupla) ===
Q  = [{q0,q1}, {q0,q1,q2}]
Σ  = [a, b]
q0 = {q0,q1}
F  = [{q0,q1,q2}]
δ  = abaixo

δ (transições):
({q0,q1}, a) -> {q0,q1,q2}
({q0,q1}, b) -> {q0,q1}
({q0,q1,q2}, a) -> {q0,q1,q2}
({q0,q1,q2}, b) -> {q0,q1,q2}
```

---

## 🧠 Tecnologias usadas
- **Java 17+**
- **PowerShell 7**
- Codificação **UTF-8** habilitada (para símbolos Σ e ε)

---

## ✨ Autor
**Thales Moura Machado Caixeta**  
👨‍💻 Estudante de Ciência da Computação — PUC-GO  
📍 Goiânia - GO  
🔗 [GitHub @Thales-Caixeta](https://github.com/Thales-Caixeta)
