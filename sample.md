package com.example.elevator;

import java.util.LinkedList;
import java.util.Queue;

public class Elevator {

    public enum Direction { UP, DOWN, IDLE }
    public enum DoorState { OPEN, CLOSED }

    private final int minFloor;
    private final int maxFloor;
    private int currentFloor;
    private Direction direction;
    private DoorState doorState;
    private final Queue<Integer> plannedStops;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.doorState = DoorState.CLOSED;
        this.plannedStops = new LinkedList<>();
    }

    public synchronized void selectFloor(int floor) {
        if (floor < minFloor || floor > maxFloor)
            throw new IllegalArgumentException("Floor out of range: " + floor);

        if (!plannedStops.contains(floor)) {
            plannedStops.offer(floor);
        }
        planDirection();
    }

    private void planDirection() {
        if (plannedStops.isEmpty()) {
            direction = Direction.IDLE;
        } else {
            int target = plannedStops.peek();
            if (target > currentFloor) direction = Direction.UP;
            else if (target < currentFloor) direction = Direction.DOWN;
            else direction = Direction.IDLE;
        }
    }

    public synchronized void tick() {
        if (doorState == DoorState.OPEN) {
            // simulate door closing
            doorState = DoorState.CLOSED;
            return;
        }

        if (plannedStops.isEmpty()) {
            direction = Direction.IDLE;
            return;
        }

        int target = plannedStops.peek();

        if (currentFloor < target) {
            currentFloor++;
            direction = Direction.UP;
        } else if (currentFloor > target) {
            currentFloor--;
            direction = Direction.DOWN;
        } else {
            // reached target
            doorState = DoorState.OPEN;
            plannedStops.poll();
        }

        if (plannedStops.isEmpty() && doorState == DoorState.CLOSED) {
            direction = Direction.IDLE;
        }
    }

    public synchronized int getCurrentFloor() {
        return currentFloor;
    }

    public synchronized Direction getDirection() {
        return direction;
    }

    public synchronized DoorState getDoorState() {
        return doorState;
    }

    public synchronized boolean hasPlannedStops() {
        return !plannedStops.isEmpty();
    }

    @Override
    public synchronized String toString() {
        return "Elevator{" +
                "currentFloor=" + currentFloor +
                ", direction=" + direction +
                ", doorState=" + doorState +
                ", plannedStops=" + plannedStops +
                '}';
    }
}




package com.example.elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElevatorController {

    private final List<Elevator> elevators;
    private final int minFloor;
    private final int maxFloor;

    public ElevatorController(int numberOfElevators, int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.elevators = new ArrayList<>();
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new Elevator(minFloor, maxFloor));
        }
    }

    public List<Elevator> getElevators() {
        return Collections.unmodifiableList(elevators);
    }

    /**
     * Called when a person outside presses the "Call" button at a given floor.
     * Chooses the most optimal elevator based on distance and current direction.
     */
    public synchronized void callElevator(int requestFloor) {
        if (requestFloor < minFloor || requestFloor > maxFloor)
            throw new IllegalArgumentException("Floor out of range: " + requestFloor);

        Elevator best = findBestElevator(requestFloor);
        best.selectFloor(requestFloor);
    }

    /**
     * Thread-safe tick: move all elevators one step.
     */
    public synchronized void tickAll() {
        for (Elevator e : elevators) {
            e.tick();
        }
    }

    /**
     * Determine the best elevator to serve a request.
     * Priority order:
     *  1. Elevator already moving toward the request floor in same direction.
     *  2. Closest idle elevator.
     *  3. Otherwise, the overall nearest elevator.
     */
    private Elevator findBestElevator(int requestFloor) {
        Elevator best = null;
        int bestScore = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            int distance = Math.abs(e.getCurrentFloor() - requestFloor);
            int score = distance;

            if (e.getDirection() == Elevator.Direction.IDLE) {
                score -= 1; // prefer idle slightly
            } else if (isMovingToward(e, requestFloor)) {
                score -= 2; // strong preference for same-direction
            }

            if (score < bestScore) {
                bestScore = score;
                best = e;
            }
        }
        return best;
    }

    private boolean isMovingToward(Elevator e, int target) {
        if (e.getDirection() == Elevator.Direction.UP && e.getCurrentFloor() <= target)
            return true;
        if (e.getDirection() == Elevator.Direction.DOWN && e.getCurrentFloor() >= target)
            return true;
        return false;
    }
}


package com.example.elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElevatorController {

    private final List<Elevator> elevators;
    private final int minFloor;
    private final int maxFloor;

    public ElevatorController(int numberOfElevators, int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.elevators = new ArrayList<>();
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new Elevator(minFloor, maxFloor));
        }
    }

    public List<Elevator> getElevators() {
        return Collections.unmodifiableList(elevators);
    }

    /**
     * Called when a person outside presses the "Call" button at a given floor.
     * Chooses the most optimal elevator based on distance and current direction.
     */
    public synchronized void callElevator(int requestFloor) {
        if (requestFloor < minFloor || requestFloor > maxFloor)
            throw new IllegalArgumentException("Floor out of range: " + requestFloor);

        Elevator best = findBestElevator(requestFloor);
        best.selectFloor(requestFloor);
    }

    /**
     * Thread-safe tick: move all elevators one step.
     */
    public synchronized void tickAll() {
        for (Elevator e : elevators) {
            e.tick();
        }
    }

    /**
     * Determine the best elevator to serve a request.
     * Priority order:
     *  1. Elevator already moving toward the request floor in same direction.
     *  2. Closest idle elevator.
     *  3. Otherwise, the overall nearest elevator.
     */
    private Elevator findBestElevator(int requestFloor) {
        Elevator best = null;
        int bestScore = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            int distance = Math.abs(e.getCurrentFloor() - requestFloor);
            int score = distance;

            if (e.getDirection() == Elevator.Direction.IDLE) {
                score -= 1; // prefer idle slightly
            } else if (isMovingToward(e, requestFloor)) {
                score -= 2; // strong preference for same-direction
            }

            if (score < bestScore) {
                bestScore = score;
                best = e;
            }
        }
        return best;
    }

    private boolean isMovingToward(Elevator e, int target) {
        if (e.getDirection() == Elevator.Direction.UP && e.getCurrentFloor() <= target)
            return true;
        if (e.getDirection() == Elevator.Direction.DOWN && e.getCurrentFloor() >= target)
            return true;
        return false;
    }
}


package com.example.elevator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorControllerTest {

    private ElevatorController controller;

    @BeforeEach
    void setUp() {
        controller = new ElevatorController(2, 0, 10);
    }

    @Test
    void testControllerInitializesTwoElevators() {
        assertEquals(2, controller.getElevators().size());
        controller.getElevators().forEach(e ->
                assertEquals(Elevator.Direction.IDLE, e.getDirection()));
    }

    @Test
    void testCallElevatorAssignsClosestIdleElevator() {
        Elevator e0 = controller.getElevators().get(0);
        Elevator e1 = controller.getElevators().get(1);

        // Move elevator 1 to floor 5 manually
        for (int i = 0; i < 5; i++) e1.selectFloor(i + 1);
        while (e1.hasPlannedStops()) e1.tick();

        // Call from floor 2 — should assign elevator 0 (closer)
        controller.callElevator(2);

        assertTrue(e0.hasPlannedStops());
        assertFalse(e1.hasPlannedStops());
    }

    @Test
    void testCallElevatorToMovingSameDirection() {
        Elevator e0 = controller.getElevators().get(0);
        Elevator e1 = controller.getElevators().get(1);

        e0.selectFloor(5);
        e0.tick(); // now at floor 1 moving up

        controller.callElevator(3); // should assign to e0
        assertTrue(e0.hasPlannedStops());
        assertEquals(Elevator.Direction.UP, e0.getDirection());
    }

    @Test
    void testTickAllMovesAllElevators() {
        controller.getElevators().get(0).selectFloor(3);
        controller.getElevators().get(1).selectFloor(2);

        controller.tickAll();

        assertEquals(1, controller.getElevators().get(0).getCurrentFloor());
        assertEquals(1, controller.getElevators().get(1).getCurrentFloor());
    }

    @Test
    void testMultipleCallsQueueUpProperly() {
        controller.callElevator(2);
        controller.callElevator(4);

        Elevator e0 = controller.getElevators().get(0);
        assertTrue(e0.hasPlannedStops());

        for (int i = 0; i < 6; i++) controller.tickAll();

        assertEquals(4, e0.getCurrentFloor());
        assertEquals(Elevator.DoorState.OPEN, e0.getDoorState());
    }
}
