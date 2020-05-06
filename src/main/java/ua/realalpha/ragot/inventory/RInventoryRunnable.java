package ua.realalpha.ragot.inventory;

import java.util.UUID;

public final class RInventoryRunnable {

    private final Runnable runnable;
    private final int delay;
    private final UUID uuid;

    public RInventoryRunnable(Runnable runnable, int delay) {
        this.runnable = runnable;
        this.delay = delay;
        this.uuid = UUID.randomUUID();
    }


    public UUID getUuid() { return uuid; }
    public int getDelay() { return delay; }
    public Runnable getRunnable() {
        return runnable;
    }
}
