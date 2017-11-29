package pizzaservice.states;

import org.springframework.stereotype.Component;

/**
 * Created by s_okhoda on 10.10.2016.
 */
@Component
public class CancelledState extends State  {

    public CancelledState() {
        name = StateEn.CANCELED;
    }

    @Override
    public State nextState(OrderStateCycle context) {
        return context.getCurState();
    }

    @Override
    public State previousState(OrderStateCycle context) {
        return context.getCurState();
    }
}
