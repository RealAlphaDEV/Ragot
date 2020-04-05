package ua.realalpha.ragot;

import ua.realalpha.ragot.item.RItemManager;

public class RagotConsumer implements RagotProvider {

    private Ragot ragot;
    public RagotConsumer(Ragot ragot) {
        this.ragot = ragot;
    }

    @Override
    public RItemManager getRItemManager() {
        return new RItemManager(ragot);
    }
}
