package com.businessdomain.states;

import org.springframework.stereotype.Component;

/**
 * Created by s_okhoda on 10.10.2016.
 */
@Component
public class NewState extends State {

    public NewState() {
        name = StateEn.NEW;
    }

    @Override
    public State nextState(OrderStateCycle context) {
        return setStateAndReturn(context, context.getInProgressSt());
    }

    @Override
    public State previousState(OrderStateCycle context) {
        return context.getCurState();
    }
}
