package pizzaservice.states;

import org.springframework.stereotype.Component;

/**
 * Created by s_okhoda on 10.10.2016.
 */
@Component
public class DoneState extends State {

    public DoneState() {
        name = StateEn.DONE;
    }

    @Override
    public State nextState(OrderStateCycle context) {
        return context.getCurState();
    }

    @Override
    public State previousState(OrderStateCycle context) {
        return setStateAndReturn(context, context.getInProgressSt());
    }
}
