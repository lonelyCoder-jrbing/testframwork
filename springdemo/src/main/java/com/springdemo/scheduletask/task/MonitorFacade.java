package com.springdemo.scheduletask.task;

import com.sun.crypto.provider.HmacMD5KeyGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service(value = "MonitorFacade")
public class MonitorFacade {

    private List<People> peoples;

    public MonitorFacade() {
        peoples = new ArrayList<>();
        peoples.add(new Programmer());
        peoples.add(new Teacher());
    }

    public void wakeup() {
        makeAction(peoples, People.Action.WAKE_UP);
    }

    public void makeMoney() {
        makeAction(peoples, People.Action.MAKE_MONEY);
    }

    public void sleep() {
        makeAction(peoples, People.Action.GO_HOME, People.Action.SLEEP);
    }

    public void makeAction(Collection<People> peoples, People.Action... actions) {
        peoples.forEach(e -> {
            e.acion(actions);
        });
    }
}
