package com.businessdomain.states;

import java.io.Serializable;

public abstract class State implements Serializable{

    protected StateEn name;
    public abstract State nextState(OrderStateCycle context);
    public abstract State previousState(OrderStateCycle context);

    public State() {
    }

    public State(StateEn name) {
        this.name = name;
    }

    public State setStateAndReturn(OrderStateCycle context, State state){
        context.setCurState(state);
        return state;
    }

    public StateEn getName() {
        return name;
    }

    public void setName(StateEn name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "State{" +
                "name=" + name +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return name == state.name;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
