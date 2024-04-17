import java.io.*;
import java.util.*;

public class NFA implements Serializable {
    private Map<Integer, Map<Character, Set<Integer>>> transitions;
    private Set<Integer> finalStates;
    private int startState;

    public NFA() {
        transitions = new HashMap<>();
        finalStates = new HashSet<>();
    }

    public void addState(int state) {
        transitions.put(state, new HashMap<>());
    }

    public void addTransition(int fromState, char input, int toState) {
        transitions.get(fromState).computeIfAbsent(input, k -> new HashSet<>()).add(toState);
    }

    public void setStartState(int state) {
        startState = state;
    }

    public void addFinalState(int state) {
        finalStates.add(state);
    }

    public boolean accepts(String word) {
        Set<Integer> currentStates = closure(Collections.singleton(startState));
        for (char symbol : word.toCharArray()) {
            currentStates = move(currentStates, symbol);
            currentStates = closure(currentStates);
        }
        return !Collections.disjoint(currentStates, finalStates);
    }

    private Set<Integer> closure(Set<Integer> states) {
        Set<Integer> epsilonClosure = new HashSet<>(states);
        Queue<Integer> queue = new LinkedList<>(states);

        while (!queue.isEmpty()) {
            int state = queue.poll();
            if (transitions.containsKey(state) && transitions.get(state).containsKey('ε')) {
                for (int nextState : transitions.get(state).get('ε')) {
                    if (epsilonClosure.add(nextState)) {
                        queue.offer(nextState);
                    }
                }
            }
        }

        return epsilonClosure;
    }

    private Set<Integer> move(Set<Integer> states, char symbol) {
        Set<Integer> nextStates = new HashSet<>();
        for (int state : states) {
            if (transitions.containsKey(state) && transitions.get(state).containsKey(symbol)) {
                nextStates.addAll(transitions.get(state).get(symbol));
            }
        }
        return nextStates;
    }
}
