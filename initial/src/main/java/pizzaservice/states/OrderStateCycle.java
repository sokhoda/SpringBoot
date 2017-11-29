package pizzaservice.states;

import infrastructure.db.converters.StateConverter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Component
@Embeddable
@Access(AccessType.FIELD)
public class OrderStateCycle implements Serializable {
    private static State newSt;
    private static State inProgressSt;
    private static State cancelledSt;
    private static State doneSt;

    @Convert(converter = StateConverter.class)
    private State curState;

    public OrderStateCycle(State newSt, State inProgressSt, State cancelledSt, State doneSt) {
        OrderStateCycle.newSt = newSt;
        OrderStateCycle.inProgressSt = inProgressSt;
        OrderStateCycle.cancelledSt = cancelledSt;
        OrderStateCycle.doneSt = doneSt;
    }

    public OrderStateCycle() {
    }

    public OrderStateCycle(State curState) {
        this.curState = curState;
    }

    @PostConstruct
    public void init() {
        this.curState = newSt;
    }

    public State nextState() {
        return curState.nextState(this);
    }

    public State previousState() {
        return curState.previousState(this);
    }

    @Override
    public String toString() {
        return "OrderStateCycle{" +
                "curState=" + curState +
                ", newSt=" + newSt +
                ", inProgressSt=" + inProgressSt +
                ", cancelledSt=" + cancelledSt +
                ", doneSt=" + doneSt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderStateCycle that = (OrderStateCycle) o;

        return curState != null ? curState.equals(that.curState) : that.curState == null;
    }


    public State getCurState() {
        return curState;
    }

    public void setCurState(State curState) {
        this.curState = curState;
    }

    public State getNewSt() {
        return newSt;
    }

    public State getInProgressSt() {
        return inProgressSt;
    }

    public State getCancelledSt() {
        return cancelledSt;
    }

    public State getDoneSt() {
        return doneSt;
    }

}
