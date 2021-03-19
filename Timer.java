package herman;

import java.util.*;


public class Timer {
	
	Long timer;

    public void startTime(Long timer) {

        this.timer = timer;

    }

    public String endTime(){

        return String.valueOf(System.currentTimeMillis() - this.timer);//ends timer

    }

}
