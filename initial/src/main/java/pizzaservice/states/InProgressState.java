package pizzaservice.states;

import org.springframework.stereotype.Component;

@Component
public class InProgressState extends State {

    public InProgressState() {
        name = StateEn.IN_PROGRESS;
    }

    @Override
    public State nextState(OrderStateCycle context) {
        return setStateAndReturn(context, context.getDoneSt());
    }

    @Override
    public State previousState(OrderStateCycle context) {
        return setStateAndReturn(context, context.getNewSt());
    }


}
