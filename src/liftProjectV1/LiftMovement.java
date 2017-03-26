/*

Purpose  	: This file keep track of floor location, and instruct the controller for lift movement based on requested floor and current floor location. Handles PANIC button request, control movement of lift, disable other OPEN request, if any.

@Author	   	: SpecificallyRandom Team
@Created On	: 18-Feb-17

*/

package liftProjectV1;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class LiftMovement {

    private static final int SIZE = 15;
    private static final int OPEN_DR = 0;
    private static final int MOVE_UP = 1;
    private static final int MOVE_DN = 2;
    private static final int IDLE_ST = 3;
    private static final int SPCL_CS = 4;
    private static final int DIR_UP = 0;
    private static final int DIR_DN = 1;
    private static int PANIC_FLAG = 0;
    private static int SPCL_FLAG = 0;

    public static Queue<Integer> upQueue = new PriorityQueue<>(SIZE);
    public static Queue<Integer> downQueue = new PriorityQueue<>(SIZE, Collections.reverseOrder());

    static int currentFloor, previousFloor, requestFloor, dir;

    public LiftMovement() {
        currentFloor = 1;
        previousFloor = 1;
        requestFloor = 1;
        dir = DIR_UP;
    }

    public static void setDir() {
        if (currentFloor > previousFloor) {
            dir = DIR_UP;
        } else if (currentFloor < previousFloor) {
            dir = DIR_DN;
        }
    }

    public static void updateQueues() {
        if (requestFloor > currentFloor) {
            if (!upQueue.contains(requestFloor)) {
                upQueue.add(requestFloor);
            }
        } else {
            if (!downQueue.contains(requestFloor)) {
                downQueue.add(requestFloor);
            }
        }
    }

    public static int checkQueue(int d) {      // TBD : Set dir in 'if else'
        if (d == DIR_UP) {
            if (upQueue.isEmpty()) {
                if (downQueue.isEmpty()) {
                    return IDLE_ST;
                } else {
                    return MOVE_DN;
                }
            } else {
                return MOVE_UP;
            }
        } else { // dir = DN
            if (downQueue.isEmpty()) {
                if (upQueue.isEmpty()) {
                    return IDLE_ST;
                } else {
                    return MOVE_UP;
                }
            } else {
                return MOVE_DN;
            }
        }
    }

    public static int liftAction() {
        if (currentFloor == requestFloor &&
                currentFloor != previousFloor) {    // Required at time of direction change
            if (upQueue.contains(currentFloor)) {
                upQueue.remove();
            } else if (downQueue.contains(currentFloor)) {
                downQueue.remove();
            } else {
                return IDLE_ST;
            }
            return OPEN_DR;
        } else {
            return checkQueue(dir);
        }
    }

    // Call before opening door and after closing door
    public static int setCurrentFloor(int floor) {
        if(SPCL_FLAG == 1) {
            return SPCL_CS;
        }
        setDir();
        previousFloor = currentFloor;
        currentFloor = floor;

        if (PANIC_FLAG == 1 && currentFloor == 1) {
            PANIC_FLAG = 0;
        }

        if (dir == DIR_UP) {
            if (!upQueue.isEmpty()) {
                requestFloor = upQueue.remove();
                upQueue.add(requestFloor);
            }
        } else {
            if (!downQueue.isEmpty()) {
                requestFloor = downQueue.remove();
                downQueue.add(requestFloor);
            }
        }

        return liftAction();
    }

    public static int setRequestFloor(int floor) {    // Pass (-1 * floor) for panic at 'floor' from OUTSIDE
        if (floor * -1 == currentFloor) {
            SPCL_FLAG = 1;
            return SPCL_CS;
        }

        if (floor != currentFloor && floor * -1 != currentFloor) {
            if (floor > 0 && PANIC_FLAG == 0) {
                requestFloor = floor;
                updateQueues();
            } else if (floor < 0) {    // Panic implementation
                PANIC_FLAG = 1; // Stop taking other requests
                requestFloor = floor * -1;

                while (!upQueue.isEmpty()) {
                    upQueue.remove();
                }
                while (!downQueue.isEmpty()) {
                    downQueue.remove();
                }

                updateQueues();
                dir = DIR_UP;
                if (requestFloor != 1) {
                    downQueue.add(1);   // Bring down lift to ground floor(1) (default)
                }
            }
            return liftAction();
        }
        return 10;
    }
}